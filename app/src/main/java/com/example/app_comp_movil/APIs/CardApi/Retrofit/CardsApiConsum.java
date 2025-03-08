package com.example.app_comp_movil.APIs.CardApi.Retrofit;

import android.util.Log;

import com.example.app_comp_movil.APIs.CardApi.ApiService;
import com.example.app_comp_movil.Model.Entities.CardEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CardsApiConsum {

    private static final String URL_API =  "https://www.freetogame.com/api/";

    public interface OnCardsFetchedListener {
        void onCardsFetched(List<CardEntity> cards);
        void onFailure(String error);
    }

    public void fetchData(OnCardsFetchedListener listener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<CardEntity>> call = apiService.getCards();
        call.enqueue(new Callback<List<CardEntity>>() {
            @Override
            public void onResponse(Call<List<CardEntity>> call, Response<List<CardEntity>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<CardEntity> allCards = response.body();
                    List<CardEntity> filterCards = allCards.subList(0, Math.min(10, allCards.size()));

                    listener.onCardsFetched(filterCards);
                }else{
                    listener.onFailure("Error en la respuesta del API");
                }
            }

            @Override
            public void onFailure(Call<List<CardEntity>> call, Throwable t) {
                Log.i("Problemas en el consumo de api", t.getMessage().toString());
            }
        });
    }

}
