package com.example.app_comp_movil.Model.Entities;

import com.google.gson.annotations.SerializedName;

public class CardEntity {

    @SerializedName("id")
    private int cardId;

    @SerializedName("title")
    private String cardTitle;

    @SerializedName("thumbnail")
    private String cardImage;

    @SerializedName("short_description")
    private String cardShortDesc;

    //region constructores
    public CardEntity() {
    }

    public CardEntity(int cardId, String cardTitle, String cardImage, String cardShortDesc) {
        this.cardId = cardId;
        this.cardTitle = cardTitle;
        this.cardImage = cardImage;
        this.cardShortDesc = cardShortDesc;
    }
    //endregion

    //region getters y setters
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public String getCardShortDesc() {
        return cardShortDesc;
    }

    public void setCardShortDesc(String cardShortDesc) {
        this.cardShortDesc = cardShortDesc;
    }

    //endregion
}


