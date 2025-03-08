package com.example.app_comp_movil.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;



import com.example.app_comp_movil.Model.Entities.CardEntity;
import com.example.app_comp_movil.R;
import java.util.List;

//Hereda del RecyclerView y se encargara de inflarlo con el elemento item_card.xml
//en nuestro Layout


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<CardEntity> cards;
    private OnCardClickListener listener;

    public CardAdapter(List<CardEntity> cards, OnCardClickListener listener) {
        this.cards = cards;
        this.listener = listener;
    }

    //Se encarga de generar la inflacion del layout con nuestro elemento item_card
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    //Genera y asigna posición a los items
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardEntity card = cards.get(position);
        holder.bind(card, listener);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private ImageView cardImageView;
        private ImageButton associateButton;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.card_title);
            cardImageView = itemView.findViewById(R.id.card_image);
            associateButton = itemView.findViewById(R.id.associate_button);
        }

        public void bind(CardEntity card, OnCardClickListener listener) {
            titleTextView.setText(card.getCardTitle());
            Glide.with(itemView.getContext()).load(card.getCardImage()).into(cardImageView);

            cardImageView.setOnClickListener(v -> listener.onImageClick(card));
            associateButton.setOnClickListener(v -> listener.onAssociateButtonClick(card));
        }
    }

    public interface OnCardClickListener {
        void onAssociateButtonClick(CardEntity card);
        void onImageClick(CardEntity card);
    }
}
