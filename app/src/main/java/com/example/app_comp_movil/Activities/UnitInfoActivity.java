package com.example.app_comp_movil.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import com.example.app_comp_movil.Model.DAOs.CardDAO;
import com.example.app_comp_movil.Model.Entities.UserEntity;
import com.example.app_comp_movil.R;
import com.example.app_comp_movil.Utils.Navigation.IntentNavigator;
import com.example.app_comp_movil.Utils.Sesions.UserSesionManager;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class UnitInfoActivity extends AppCompatActivity{
    //dentro de este activity se tomaran datos del API (imagen, titulo, descripción) para
    //presentarlos en un formato estilo blog

    private UserEntity user;
    private CardDAO cardDAO;
    private IntentNavigator navigator;
    private ImageView ivcardImage;
    private TextView tvcardTitle;
    private TextView tvcardInfo;

    private void init(){
        cardDAO = new CardDAO(this, findViewById(android.R.id.content));
        user = UserSesionManager.getInstance().getUserEntity();
        navigator = new IntentNavigator(this);

        ivcardImage = findViewById(R.id.cardImg);
        tvcardTitle = findViewById(R.id.cardTitle);
        tvcardInfo = findViewById(R.id.cardInfo);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit_info);
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
        buildUnitInfoCard();
    }

    private void buildUnitInfoCard(){
        Intent intent = getIntent();
        String title = intent.getStringExtra("card_title");
        String image = intent.getStringExtra("card_image");
        String description = intent.getStringExtra("card_description");

        tvcardTitle.setText(title);
        tvcardInfo.setText(description);

        Glide.with(this).load(image).into(ivcardImage);
    }

}