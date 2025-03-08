package com.example.app_comp_movil.APIs.CardApi;

import com.example.app_comp_movil.Model.Entities.CardEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("games")
    Call<List<CardEntity>> getCards();
}
