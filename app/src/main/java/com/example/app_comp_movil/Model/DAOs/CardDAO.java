package com.example.app_comp_movil.Model.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.example.app_comp_movil.Model.DB.ManagerDataBase;
import com.example.app_comp_movil.Model.Entities.CardEntity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class CardDAO {
    private ManagerDataBase dataBase;
    private Context context;
    private View view;
    private CardEntity cardEntity;

    //region constructores
    public CardDAO() {
    }

    public CardDAO(Context context, View view) {
        this.context = context;
        this.view = view;
        this.dataBase = new ManagerDataBase(this.context);
    }

    //endregion

    public void insertCard(CardEntity card, int userId){
        try {
            SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
            if(sqLiteDatabase != null){
                ContentValues values = new ContentValues();
                values.put("card_id", card.getCardId());
                values.put("card_title", card.getCardTitle());
                values.put("card_img", card.getCardImage());
                values.put("card_short_desc", card.getCardShortDesc());
                values.put("user_id", userId);

                long response = sqLiteDatabase.insert("cards", null, values);
                Snackbar.make(context, view, "Has guardado una tarjeta!"+response, 2).show();

            }else {
                Snackbar.make(context, view, "algo salio mal...", 2).show();
            }
        } catch (Exception e) {
            Log.i("error en la gestión de la db", " :"+e.getMessage().toString());
            Snackbar.make(context, view,"Error en la conexión con la base de datos", 2).show();
            throw new RuntimeException(e);
        }
    }

    public List<CardEntity> cardsFromUser(int userId){
        List<CardEntity> cards = new ArrayList<>();
        try{
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
            String query = "SELECT card_id, card_title, card_img, card_short_desc FROM cards WHERE user_id = ?";
            Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(userId)});

            if (cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String image = cursor.getString(2);
                    String descr = cursor.getString(3);

                    CardEntity card = new CardEntity(id, title, image, descr);
                    cards.add(card);
                }while(cursor.moveToNext());

                cursor.close();
                sqLiteDatabase.close();
            }
        }catch (Exception e){
            Log.i("Error en cardsFromUser", e.getMessage());
        }
        return cards;
    }

    public void deleteCard(int cardId) {
        try {
            SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
            if (sqLiteDatabase != null) {
                int rowsDeleted = sqLiteDatabase.delete("cards", "card_id = ?", new String[]{String.valueOf(cardId)});
                if (rowsDeleted > 0) {
                    Snackbar.make(view, "Item eliminado de tu lista", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(view, "Error al eliminar la tarjeta", Snackbar.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error en deleteCard", e.getMessage());
            Snackbar.make(view, "Error en la base de datos", Snackbar.LENGTH_SHORT).show();
        }
    }
}


