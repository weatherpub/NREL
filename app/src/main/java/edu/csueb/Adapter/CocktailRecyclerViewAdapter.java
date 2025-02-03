package edu.csueb.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.csueb.Model.CocktailModel;
import edu.csueb.Pattern.Singleton.CocktailViewModel;
import edu.csueb.R;

public class CocktailRecyclerViewAdapter extends RecyclerView.Adapter<CocktailRecyclerViewAdapter.ViewHolder> {
    ArrayList<CocktailModel> model;

    CocktailRecyclerViewAdapter.Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public CocktailRecyclerViewAdapter() {
        CocktailViewModel cocktailViewModel = CocktailViewModel.getInstance();
        model = cocktailViewModel.getData();
    }

    @NonNull
    @Override
    public CocktailRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cocktail_card_view, parent, false);
        return new CocktailRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        View itemView = holder.itemView;
        CocktailModel item = model.get(position);

        holder.id.setText(String.format("%s", item.getIdDrink()));
        holder.drink.setText(String.format("%s", item.getStrDrink()));
        holder.alternative.setText(String.format("%s", item.getStrDrinkAlternate()));
        holder.tags.setText(String.format("%s", item.getStrTags()));
        holder.video.setText(String.format("%s", item.getStrVideo()));
        holder.category.setText(String.format("%s", item.getStrCategory()));

        itemView.setOnClickListener(new View.OnClickListener() {
            /**
             * @param view
             */
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private CardView cardView;
        public TextView id;
        public TextView drink;
        public TextView alternative;
        public TextView tags;
        public TextView video;
        public TextView category;

        // Original Code
        public ViewHolder(@NonNull View view) {
            /**
             *
             * @param view
             */
            // public ViewHolder(@NonNull CardView view) {
            super(view);
            id = view.findViewById(R.id.tv_id);
            drink = view.findViewById(R.id.tv_drink);
            alternative = view.findViewById(R.id.tv_drink_alternative);
            tags = view.findViewById(R.id.tv_tags);
            video = view.findViewById(R.id.tv_video);
            category = view.findViewById(R.id.tv_category);
        }
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}