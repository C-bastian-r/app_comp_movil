package com.example.app_comp_movil.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_comp_movil.Adapters.CardAdapter;
import com.example.app_comp_movil.Adapters.CardsUserAdapter;
import com.example.app_comp_movil.Model.DAOs.CardDAO;
import com.example.app_comp_movil.Model.Entities.CardEntity;
import com.example.app_comp_movil.Model.Entities.UserEntity;
import com.example.app_comp_movil.R;
import com.example.app_comp_movil.Utils.Navigation.IntentNavigator;
import com.example.app_comp_movil.Utils.Sesions.UserSesionManager;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class UserCardsActivity extends AppCompatActivity implements CardsUserAdapter.OnCardClickListener{

    private RecyclerView recyclerView;
    private CardDAO cardDAO;
    private UserEntity user;
    private IntentNavigator navigator;

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        cardDAO = new CardDAO(this, findViewById(android.R.id.content));
        user = UserSesionManager.getInstance().getUserEntity();
        navigator = new IntentNavigator(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_cards);
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

        List<CardEntity> cards = cardDAO.cardsFromUser(user.getUserId());
        renderCards(cards);
    }

    private void renderCards(List<CardEntity> cards){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardsUserAdapter adapter = new CardsUserAdapter(cards, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDeleteButtonClick(CardEntity card) {
        cardDAO.deleteCard(card.getCardId());

        List<CardEntity> updatedCards = cardDAO.cardsFromUser(user.getUserId());
        renderCards(updatedCards);
    }

    @Override
    public void onImageClick(CardEntity card) {
        navigator.setIntenteNavCard(UnitInfoActivity.class, card);
    }
}