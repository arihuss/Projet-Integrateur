package com.sarah.applicationsqak.vues;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Evenement;
import com.sarah.applicationsqak.modele.Inscription;
import com.sarah.applicationsqak.modele.Organisateur;
import com.sarah.applicationsqak.viewmodel.OrganisateurViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InscriptionAdapter extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<InscriptionAffichage> items = new ArrayList<>();
    private OnDesinscriptionClickListener desinscriptionClickListener;
    private final OrganisateurViewModel organisateurViewModel;

    public interface OnDesinscriptionClickListener {
        void onDesinscriptionClick(Inscription inscription);
    }

    public static class InscriptionAffichage {
        public final Evenement evenement;
        public final Inscription inscription;

        public InscriptionAffichage(Evenement evenement, Inscription inscription) {
            this.evenement = evenement;
            this.inscription = inscription;
        }
    }

    public InscriptionAdapter(Context context, int layoutResource, OrganisateurViewModel organisateurViewModel) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.organisateurViewModel = organisateurViewModel;
    }

    public void setOnDesinscriptionClickListener(OnDesinscriptionClickListener listener) {
        this.desinscriptionClickListener = listener;
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<InscriptionAffichage> nouvellesInscriptions) {
        items.addAll(nouvellesInscriptions);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).inscription.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InscriptionAffichage ia = items.get(position);
        Inscription inscription = ia.inscription;
        Evenement evenement = ia.evenement;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_inscriptions_user, parent, false);
        }

        // Liaison des vues
        TextView tvDate = convertView.findViewById(R.id.tvDateInscr);
        TextView tvNom = convertView.findViewById(R.id.tvNomEventInscr);
        TextView tvLieu = convertView.findViewById(R.id.tvLieuEventInscr);
        TextView tvOrg = convertView.findViewById(R.id.tvNomOrgInscr);
        TextView tvStatut = convertView.findViewById(R.id.tvStatutInscription);
        ImageView img = convertView.findViewById(R.id.imgEventInscr);
        ImageButton btnX = convertView.findViewById(R.id.btnSupprimerInscription);

        // Remplissage

        // Badge date (formatée sur 3 lignes)
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
                tvDate.setText(dateFormatee);
            } catch (ParseException e) {
                e.printStackTrace();
                tvDate.setText("Date\ninvalide");
            }
        } else {
            tvDate.setText("Date\ninconnue");
        }

        tvNom.setText(evenement.getNomEvent());
        tvLieu.setText("Lieu : " + evenement.getLieu());

        // Obtenir nom de l'organisateur dynamiquement
        Organisateur org = organisateurViewModel.getOrganisateurParId(evenement.getId_organisateur());
        String nomOrganisation = (org != null) ? org.getNomOrg() : "Organisateur inconnu";
        tvOrg.setText("Organisation : " + nomOrganisation);

        tvStatut.setText("Statut : " + formatRole(inscription.getRole()));
        String url = evenement.getImageUrl();
        if (url != null && !url.trim().isEmpty()) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(img);
        } else {
            img.setImageResource(R.drawable.placeholder);
        }



        // Action bouton désinscription
        btnX.setOnClickListener(v -> {
            if (desinscriptionClickListener != null) {
                desinscriptionClickListener.onDesinscriptionClick(inscription);
            }
        });

        return convertView;
    }

    private String formatRole(String role) {
        switch (role.toLowerCase()) {
            case "benevole":
                return "Bénévole";
            case "appliquant":
                return "Appliquant";
            default:
                return "Visiteur";
        }
    }
}
