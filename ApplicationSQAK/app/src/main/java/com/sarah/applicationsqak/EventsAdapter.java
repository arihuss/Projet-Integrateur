package com.sarah.applicationsqak;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sarah.applicationsqak.vues.Evenement;

import java.util.List;

public class EventsAdapter {
    private Context contexte;
    private int viewRessourceID;
    private List<Evenement> evenements;

    public PizzaAdapter(@NonNull Context context, int resource, @NonNull List<Pizza> objects) {
        super(context, resource, objects);
        contexte = context;
        viewRessourceID = resource;
        pizzas = objects;
    }

    @Override
    public int getCount() {
        return pizzas.size();
    }

    // Méthode pour afficher les items de la ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {      // Si la vue n'existe pas, on doit la créer
            LayoutInflater layoutInflater = (LayoutInflater) contexte.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRessourceID, parent, false);
        }

        // Récupère la pizza actuelle
        Pizza pizza = pizzas.get(position);

        if(pizza != null) {
            TextView txtPizza = view.findViewById(R.id.txtPizza);
            TextView txtPrix = view.findViewById(R.id.txtPrix);
            ImageView imgPizza = view.findViewById(R.id.imgPizza);

            // Compléter l'affichage de la pizza
            txtPizza.setText(pizza.getNom());
            txtPrix.setText(String.format("%.2f$", pizza.getPrix()));


            // Compléter pour changer la couleur du texte & image selon la disponibilité
            if(pizza.isDisponible() == true) {
                // Image de pizza
                imgPizza.setImageResource(R.drawable.icon_pizza);

                // Text en noir
                txtPizza.setTextColor(Color.BLACK);
                txtPrix.setTextColor(Color.BLACK);
            }
            else {
                // Image 'sold out'
                imgPizza.setImageResource(R.drawable.sold_out_sign);

                // Texte en gris
                txtPizza.setTextColor(Color.GRAY);
                txtPrix.setTextColor(Color.GRAY);
            }
        }

        return view;
    }
}
