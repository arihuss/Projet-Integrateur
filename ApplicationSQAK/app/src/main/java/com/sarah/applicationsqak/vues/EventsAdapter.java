package com.sarah.applicationsqak.vues;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Dao.EvenementDao;
import com.sarah.applicationsqak.modele.Evenement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventsAdapter extends ArrayAdapter<Evenement> {
    private Context contexte;
    private int viewRessourceID;
    private final EvenementDao dao;

    public EventsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        contexte = context;
        viewRessourceID = resource;
        this.dao = new EvenementDao(context);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    // Méthode pour afficher les items de la ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("ADAPTER", "getView() de EventsAdapter appelé pour position: " + position);

        View view = convertView;
        if(view == null) {      // Si la vue n'existe pas, on doit la créer
            LayoutInflater layoutInflater = (LayoutInflater) contexte.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRessourceID, parent, false);
        }

        // Récupère l'événement actuel
        Evenement event = getItem(position);

        if(event != null) {
            // Liaison avec la vue d'un item (principale_events_list_item)
            TextView txtOrganisateur = view.findViewById(R.id.tvOrgEvents);
            TextView txtNom = view.findViewById(R.id.tvNameEvents);
            ImageView imgProfileOrg = view.findViewById(R.id.imgProfileEvents);
            ImageView imgEvent = view.findViewById(R.id.imgAffEvents);
            TextView txtDate = view.findViewById(R.id.tvDateEvents);
            TextView txtEtat = view.findViewById(R.id.tvEtatEvents);


            // Affichage du nom de l'event
            txtNom.setText(event.getNomEvent());

            // Affichage de la date, changer le format
            txtDate.setText(event.getDateDebut());

            // Affichage du nom de l'organisateur
            String nomOrganisateur = dao.getNomOrganisateurParId(event.getId_organisateur());
            txtOrganisateur.setText(nomOrganisateur);

            // Affichage des images selon l'url
            Glide.with(contexte)
                    .load(event.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imgEvent);

            // Image de l'organisateur
            String imgOrgUrl = dao.getImageOrganisateurParId(event.getId_organisateur());
            Glide.with(contexte)
                    .load(imgOrgUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imgProfileOrg);

            // Affichage de l'image du profil organisateur -- TEMPORAIRE
            //imgProfileOrg.setImageResource(R.drawable.placeholder);
            //imgEvent.setImageResource(R.drawable.placeholder);

            // Affichage de 'COMPLET' s'il n'y a plus de place
            if (event.getCompletBenevole() == 1 && event.getCompletVisiteur() == 1) {
                txtEtat.setVisibility(View.VISIBLE);
                txtEtat.setText("COMPLET");
            } else {
                txtEtat.setVisibility(View.GONE);
            }
        }


        return view;
    }




}
