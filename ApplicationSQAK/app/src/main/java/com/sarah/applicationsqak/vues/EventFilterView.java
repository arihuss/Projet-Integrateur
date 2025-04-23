package com.sarah.applicationsqak.vues;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;

import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Categorie;

import java.util.Calendar;
import java.util.Locale;

public class EventFilterView extends LinearLayout {

    private Spinner spLocation, spEtat;
    private Button btnBenevole, btnVisiteur, btnTout, btnDate, btnRecherche;
    private AutoCompleteTextView searchBar;

    private String roleSelectionne = "tout";  // benevole, visiteur, tout
    private String dateChoisie = "";  // format "dd/mm/yyyy
    private OnFilterChangeListener listener;


    public EventFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.event_filter, this, true);

        // Liaisions avec la vue
        spLocation = findViewById(R.id.spLocation);
        spEtat = findViewById(R.id.spEtat);
        btnVisiteur = findViewById(R.id.btnFiltreVisiteur);
        btnBenevole = findViewById(R.id.btnFiltreBenevole);
        btnTout = findViewById(R.id.btnFiltreTout);
        btnDate = findViewById(R.id.btnDate);
        btnRecherche = findViewById(R.id.btnRecherche);
        searchBar = findViewById(R.id.searchBar);


        // Adapters
        // Pour les lieux
        ArrayAdapter<CharSequence> adapterLocation = ArrayAdapter.createFromResource(context, R.array.event_locations, R.layout.event_filter_spinner_item);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLocation.setAdapter(adapterLocation);

        // Pour les états
        ArrayAdapter<CharSequence> adapterEtat = ArrayAdapter.createFromResource(context, R.array.event_etat, R.layout.event_filter_spinner_item);
        adapterEtat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEtat.setAdapter(adapterEtat);

        // Set-up le search bar
        String[] autoCompleteSuggestions = Categorie.getAllLabels();
        ArrayAdapter<String> adapterRecherche = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, autoCompleteSuggestions);
        searchBar.setAdapter(adapterRecherche);
        searchBar.setThreshold(1);  // les suggestions apparaissent après 1 caractère saisi

        // Listeners pour déclencher un filtre
        btnBenevole.setOnClickListener(v -> {
            roleSelectionne = "benevole";
            notifyFilterChanged();
        });

        btnVisiteur.setOnClickListener(v -> {
            roleSelectionne = "visiteur";
            notifyFilterChanged();
        });

        btnTout.setOnClickListener(v -> {
            resetFilters();
        });

        btnDate.setOnClickListener(v -> {
            // Obtenir la date actuelle
            final Calendar calendrier = Calendar.getInstance();
            int annee = calendrier.get(Calendar.YEAR);
            int mois = calendrier.get(Calendar.MONTH);
            int jour = calendrier.get(Calendar.DAY_OF_MONTH);

            // Créer le DatePickerDialog (le calendrier qui apparait)
            DatePickerDialog datePicker = new DatePickerDialog(
                    getContext(),
                    (view, year, month, dayOfMonth) -> {
                        dateChoisie = String.format(Locale.CANADA, "%02d/%02d/%04d", dayOfMonth, (month + 1), year);
                        btnDate.setText(dateChoisie);
                        notifyFilterChanged();
                    },
                    annee, mois, jour
            );

            datePicker.show();
        });

        btnRecherche.setOnClickListener(v -> {
            notifyFilterChanged();
        });

        spLocation.setOnItemSelectedListener(onAnyChangeListener);
        spEtat.setOnItemSelectedListener(onAnyChangeListener);


    }

    // Interface de communication
    public interface OnFilterChangeListener {
        void onFilterChanged(String lieu, String etat, String role, String date, String recherche);
    }

    public void setOnFilterChangeListener(OnFilterChangeListener listener) {
        this.listener = listener;
    }

    private void notifyFilterChanged() {
        Log.d("FILTER", "Lieu=" + getSelectedLocation() + ", Etat=" + getSelectedEtat()
                + ", Role=" + getSelectedRole() + ", Date=" + getSelectedDate()
                + ", Recherche=" + getSearchText());

        if(listener != null) {
            listener.onFilterChanged(getSelectedLocation(), getSelectedEtat(), getSelectedRole(), getSelectedDate(), getSearchText());
        }
    }

    private final AdapterView.OnItemSelectedListener onAnyChangeListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            notifyFilterChanged();
        }
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public void resetFilters() {
        spLocation.setSelection(0);
        spEtat.setSelection(0);
        roleSelectionne = "tout";
        searchBar.setText("");
        dateChoisie = "";
        btnDate.setText("Date");
        notifyFilterChanged();
    }


    // Accès aux filtres sélectionnés
    public Spinner getSpLocation() {
        return spLocation;
    }

    public String getSelectedLocation() {
        return spLocation.getSelectedItem().toString();
    }

    public String getSelectedEtat() {
        return spEtat.getSelectedItem().toString();
    }

    public String getSelectedRole() {
        return roleSelectionne;
    }

    public String getSearchText() {
        return searchBar.getText().toString().trim();
    }

    public String getSelectedDate() {
        return dateChoisie;
    }


}
