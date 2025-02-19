package com.example.app_comp_movil.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_comp_movil.MainActivity;
import com.example.app_comp_movil.Model.DAOs.UserDAO;
import com.example.app_comp_movil.R;
import com.example.app_comp_movil.Utils.Navigation.IntentNavigator;
import com.example.app_comp_movil.Utils.Security.SecurityModule;

public class LoginActivity extends AppCompatActivity {

    private SecurityModule securityModule;
    private IntentNavigator navigator;
    private Button btnLogin;
    private Button btnCreateUser;

    private void init(){
        securityModule = new SecurityModule();
        navigator = new IntentNavigator(this);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateUser = findViewById(R.id.btnCreateUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loggin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        btnLogin.setOnClickListener(this::logIn);
        btnCreateUser.setOnClickListener(this::navigateToSigIn);
    }

    private void logIn(View view){
        UserDAO userDAO = new UserDAO(this, view);
        EditText etLogEmail = findViewById(R.id.etLogEmail);
        EditText etLogPassword = findViewById(R.id.etLogPassword);

        String email = etLogEmail.getText().toString();
        String password = etLogPassword.getText().toString();

        if(securityModule.isValidEmail(email)){
            if (userDAO.getLogin(email, password)){
                Toast.makeText(this, "Sesión iniciada con exito", Toast.LENGTH_LONG).show();
                navigator.setIntentNav(MainActivity.class);
                finish();
            }else{
                Toast.makeText(this, "Datos brindados no validos", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Formato de correo no válido", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToSigIn(View view){
        navigator.setIntentNav(SigInActivity.class);
    }

}