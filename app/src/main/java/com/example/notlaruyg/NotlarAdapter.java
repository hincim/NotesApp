package com.example.notlaruyg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Notlar> notlarList;

    public NotlarAdapter(Context mContext, List<Notlar> notlarList) {
        this.mContext = mContext;
        this.notlarList = notlarList;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_tasarim,parent,false);
        return new CardTasarimTutucu(tasarim);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Notlar notlar = notlarList.get(position);

        holder.textViewDers.setText(notlar.getDers_adi());
        holder.textViewNot1.setText(String.valueOf(notlar.getNot1()));
        holder.textViewNot2.setText(String.valueOf(notlar.getNot2()));
        holder.not_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,DetayActivity.class);
                intent.putExtra("nesne",notlar);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notlarList.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView textViewDers, textViewNot1, textViewNot2;
        private CardView not_card;
        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewDers = itemView.findViewById(R.id.textViewDers);
            textViewNot1 = itemView.findViewById(R.id.textViewNot1);
            textViewNot2 = itemView.findViewById(R.id.textViewNot2);
            not_card = itemView.findViewById(R.id.not_card);
        }
    }
}
