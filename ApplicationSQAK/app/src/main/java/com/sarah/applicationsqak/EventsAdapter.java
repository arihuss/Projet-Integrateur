package com.sarah.applicationsqak;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sarah.applicationsqak.vues.Evenement;

import java.util.List;

public class EventsAdapter extends ArrayAdapter<Evenement> {
    private Context contexte;
    private int viewRessourceID;
    private List<Evenement> evenements;

    public EventsAdapter(@NonNull Context context, int resource, @NonNull List<Evenement> objects) {
        super(context, resource, objects);
        contexte = context;
        viewRessourceID = resource;
        evenements = objects;
    }

    @Override
    public int getCount() {
        return evenements.size();
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
        Evenement event = evenements.get(position);

        if(event != null) {
            // Liaison avec la vue d'un item (principale_events_list_item)
            TextView txtOrganisateur = view.findViewById(R.id.tvOrgEvents);
            TextView txtNom = view.findViewById(R.id.tvNameEvents);
            ImageView imgProfileOrg = view.findViewById(R.id.imgProfileEvents);
            ImageView imgEvent = view.findViewById(R.id.imgAffEvents);
            TextView txtDate = view.findViewById(R.id.tvDateEvents);
            TextView txtEtat = view.findViewById(R.id.tvEtatEvents);

            // Compléter l'affichage de la pizza
            txtOrganisateur.setText(event.getOrganisateur());
            txtNom.setText(event.getNom());
            txtDate.setText(event.getDate());
            txtEtat.setText(event.getEtat());
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
