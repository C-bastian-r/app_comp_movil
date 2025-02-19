package com.example.app_comp_movil;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_comp_movil.Activities.UnitInfoActivity;
import com.example.app_comp_movil.Activities.UserCardsActivity;
import com.example.app_comp_movil.Utils.Navigation.IntentNavigator;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    //dentro de este activity se recuperaran datos del api
    //y se generaran items construidos a partir de los datos recuperados
    //el usuario podra seleccionar agregarlos items de su preferencia y seran
    //almacenados en una db
    //serán renderizados dentro del activity UserCards

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private IntentNavigator navigator;

    public void init(){
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        navigator = new IntentNavigator(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        toolbar.setNavigationIcon(R.drawable.ic_action_user);
        toolbar.setNavigationOnClickListener(v -> {
            navigator.setIntentNav(UserCardsActivity.class);
        });

        init();
        iv1.setOnClickListener(v -> {
            navigator.setIntentNav(UnitInfoActivity.class);
        });
        iv2.setOnClickListener(v -> {
            navigator.setIntentNav(UnitInfoActivity.class);
        });
        iv3.setOnClickListener(v -> {
            navigator.setIntentNav(UnitInfoActivity.class);
        });
    }
}