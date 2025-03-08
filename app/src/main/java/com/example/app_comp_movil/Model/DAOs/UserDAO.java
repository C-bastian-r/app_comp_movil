package com.example.app_comp_movil.Model.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import com.example.app_comp_movil.Model.DB.ManagerDataBase;
import com.example.app_comp_movil.Model.Entities.UserEntity;
import com.example.app_comp_movil.Utils.Security.SecurityModule;
import com.example.app_comp_movil.Utils.Sesions.UserSesionManager;
import com.google.android.material.snackbar.Snackbar;

public class UserDAO {
    private ManagerDataBase dataBase;
    private Context context;
    private View view;
    private SecurityModule securityModule;
    private UserEntity userEntity;

    //constructor, pide contexto y view
    public UserDAO(Context context, View view) {
        this.context = context;
        this.view = view;
        this.dataBase = new ManagerDataBase(this.context);
        this.securityModule = new SecurityModule();
    }

    //region Encapsulasión de metodos

    //función de incersión dentro de la bd
    private void insertUser(UserEntity user){
        try {
            SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
            if(sqLiteDatabase != null){
                ContentValues values = new ContentValues();
                values.put("user_name", user.getUserName());
                values.put("user_email", user.getEmail());
                values.put("user_password", securityModule.hashPassword(user.getPassword()));

                long response = sqLiteDatabase.insert("users", null, values);
                Snackbar.make(context, view, "registro de usuario exitoso!"+response, 2).show();

            }else {
                Snackbar.make(context, view, "registro de usuario fallido...", 2).show();
            }
        } catch (Exception e) {
            Log.i("error en la gestión de la db", " :"+e.getMessage().toString());
            Snackbar.make(context, view,"Error en la conexión con la base de datos", 2).show();
            throw new RuntimeException(e);
        }
    }

    //Solicita usuario y contraseña
    //Realiza consulta de contraseña donde el usuario = email
    //Si encuentra compara password con contraseña de bd asociada a email
    //devuelve true si coincide, false si no
    private boolean loginUser(String email, String password){
        try{
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
            String query = "SELECT user_id, user_password, user_name FROM users WHERE user_email = ?";
            Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{email});
            if (cursor.moveToFirst()){
                int userId = cursor.getInt(0);
                String storedPassword = cursor.getString(1);
                Log.i("dato password: ", storedPassword);
                String userName = cursor.getString(2);
                cursor.close();

                if(securityModule.verifyPassword(password, storedPassword)){
                    UserEntity user = new UserEntity(userId, userName, email, password);
                    UserSesionManager.getInstance().setUserEntity(user);
                    return true;
                }
            }else{
                Log.i("error en verificacion: ", "problemas con el verifyPassword");
                cursor.close();
            }
        }catch (Exception e){
            Snackbar.make(view, "Las credenciales no son validas", 2 ).show();
        }
        return false;
    }

    //endregion

    //metodos de acceso

    public void getInsertUser(UserEntity user){
        insertUser(user);
    }
    public boolean getLogin(String email, String password){
        return loginUser(email, password);
    }
}

