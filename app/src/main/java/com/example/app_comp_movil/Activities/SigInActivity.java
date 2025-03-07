package com.example.app_comp_movil.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_comp_movil.Model.DAOs.UserDAO;
import com.example.app_comp_movil.Model.Entities.UserEntity;
import com.example.app_comp_movil.R;
import com.example.app_comp_movil.Utils.Navigation.IntentNavigator;
import com.example.app_comp_movil.Utils.Security.SecurityModule;
import com.google.android.material.appbar.MaterialToolbar;

public class SigInActivity extends AppCompatActivity {

    private SecurityModule securityModule;
    private IntentNavigator navigator;

    private EditText etSiginUserName;
    private EditText etSiginEmail;
    private EditText etSiginPassword;
    private Button btnSiginContinue;

    private void init(){
        securityModule = new SecurityModule();
        navigator = new IntentNavigator(this);

        etSiginUserName = findViewById(R.id.etSiginUserName);
        etSiginEmail = findViewById(R.id.etSiginEmail);
        etSiginPassword = findViewById(R.id.etSiginPassword);
        btnSiginContinue = findViewById(R.id.btnSiginContinue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sig_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        init();
        btnSiginContinue.setOnClickListener(this::register);
    }

    private void register(View view){
        try {
            String userName = etSiginUserName.getText().toString();
            String email = etSiginEmail.getText().toString();
            String password = etSiginPassword.getText().toString();
            if(securityModule.isValidEmail(email)){
                UserDAO userDAO = new UserDAO(this, view);
                UserEntity user = new UserEntity(userName, email, password);
                userDAO.getInsertUser(user);
                Toast.makeText(this, "usuario "+userName+" registrado con exito!", Toast.LENGTH_LONG).show();
                navigator.setIntentNav(LoginActivity.class);
            }else {
                Toast.makeText(this, "el formato del correo no es valido", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Log.i("Error en el register del sigin", "codigo de error: "+e.getMessage());
            Toast.makeText(this, "Error en el registro del sigin: "+e, Toast.LENGTH_LONG).show();
        }
    }
}