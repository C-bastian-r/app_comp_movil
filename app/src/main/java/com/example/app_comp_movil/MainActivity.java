package com.example.app_comp_movil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_comp_movil.APIs.CardApi.Retrofit.CardsApiConsum;
import com.example.app_comp_movil.Activities.UnitInfoActivity;
import com.example.app_comp_movil.Activities.UserCardsActivity;
import com.example.app_comp_movil.Adapters.CardAdapter;
import com.example.app_comp_movil.Model.DAOs.CardDAO;
import com.example.app_comp_movil.Model.Entities.CardEntity;
import com.example.app_comp_movil.Model.Entities.UserEntity;
import com.example.app_comp_movil.Utils.Navigation.IntentNavigator;
import com.example.app_comp_movil.Utils.Sesions.UserSesionManager;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CardAdapter.OnCardClickListener{

//activiad main:
    //Consumo de datos de api para tarjetas
    //renderizacion de lista principal de tarjetas
    //agregación de tarjetas a cuenta usuario por sesion
    //navegación a actividad de información de elemento tarjeta

    private IntentNavigator navigator;

    private CardDAO cardDAO;
    private UserEntity user;
    private CardsApiConsum api;

    private void init(){
        cardDAO = new CardDAO(this, findViewById(android.R.id.content));
        user = UserSesionManager.getInstance().getUserEntity();
        api = new CardsApiConsum();
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
        fetchCardsFromApi();
    }

    private void renderCards(List<CardEntity> cards){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardAdapter adapter = new CardAdapter(cards, this);
        recyclerView.setAdapter(adapter);
    }

    private void fetchCardsFromApi(){
        api.fetchData(new CardsApiConsum.OnCardsFetchedListener(){

            @Override
            public void onCardsFetched(List<CardEntity> cards) {
                renderCards(cards);
            }

            @Override
            public void onFailure(String error) {
                Log.e("API Error", error);
                Toast.makeText(MainActivity.this, "Error al obtener las tarjetas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAssociateButtonClick(CardEntity card) {
        try {
            cardDAO.insertCard(card, user.getUserId());
            Toast.makeText(this, "Tarjeta "+ card.getCardTitle() +" agregada a tu cuenta", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.i("Error en boton asociacion", e.getMessage());
        }

    }

    @Override
    public void onImageClick(CardEntity card) {
        navigator.setIntenteNavCard(UnitInfoActivity.class, card);
    }
}