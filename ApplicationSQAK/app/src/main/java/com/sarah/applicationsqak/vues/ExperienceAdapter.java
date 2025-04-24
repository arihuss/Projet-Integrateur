package com.sarah.applicationsqak.vues;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Evenement;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder> {

    private List<Evenement> evenements;

    public ExperienceAdapter(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Utiliser parent.getContext() ET NE PAS PASSER false pour attacher manuellement
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_benevolat_item, parent, false);
        return new ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        Evenement evenement = evenements.get(position);

        // Affichage titre
        holder.txtTitre.setText(evenement.getNomEvent());

        // Lieu
        holder.txtLieu.setText("Lieu : " + evenement.getLieu());

        // Nom organisateur
        holder.txtOrganisation.setText("Organisation : " + "à charger");

        //Image
        if (evenement.getImageUrl() != null && !evenement.getImageUrl().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(evenement.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.ImgEvent);
        }

        // Badge date (format rapide)
        String dateDebut = evenement.getDateDebut();
        if (dateDebut != null && !dateDebut.isEmpty()) {
            try {

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
                Date date = format.parse(dateDebut);


                SimpleDateFormat jourFormat = new SimpleDateFormat("dd", Locale.FRENCH);
                SimpleDateFormat moisFormat = new SimpleDateFormat("MMMM", Locale.FRENCH);
                SimpleDateFormat anneeFormat = new SimpleDateFormat("yyyy", Locale.FRENCH);

                String jour = jourFormat.format(date);
                String mois = moisFormat.format(date);
                String annee = anneeFormat.format(date);


                String dateFormatee = jour + "\n" + mois + "\n" + annee;
                holder.txtDate.setText(dateFormatee);

            } catch (ParseException e) {
                e.printStackTrace();
                holder.txtDate.setText("Date invalide");
            }
        }

        // aller sur la page de l'événement cliqué
        holder.itemView.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(v.getContext(), EvenementActivity.class);
            intent.putExtra("ID_EVENEMENT", evenement.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return evenements.size();
    }

    public static class ExperienceViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtTitre, txtLieu, txtOrganisation;
        ImageView ImgEvent;

        public ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDateExp);
            txtTitre = itemView.findViewById(R.id.txtNomEventUser);
            txtLieu = itemView.findViewById(R.id.txtLieuUser);
            txtOrganisation = itemView.findViewById(R.id.txtOrganisationUser);
            ImgEvent = itemView.findViewById(R.id.imgEventUser);


        }
    }
}

