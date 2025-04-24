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

        // Badge date (format rapide)
        String dateDebut = evenement.getDateDebut();
        if (dateDebut != null && dateDebut.length() >= 6) {
            holder.txtDate.setText(dateDebut.substring(0, 6).replace(" ", "\n"));
        }
    }

    @Override
    public int getItemCount() {
        return evenements.size();
    }

    public static class ExperienceViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtTitre, txtLieu, txtOrganisation;

        public ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDateExp);
            txtTitre = itemView.findViewById(R.id.txtNomEventUser);
            txtLieu = itemView.findViewById(R.id.txtLieuUser);
            txtOrganisation = itemView.findViewById(R.id.txtOrganisationUser);

        }
    }
}

