package com.sarah.applicationsqak.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.sarah.applicationsqak.R;

public class EventFilterView extends LinearLayout {

    private Spinner spLocation, spEtat;
    private Button btnBenevole, btnVisiteur, btnTout;

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

        // Adapters
        //ArrayAdapter<CharSequence> adapterLocation = ArrayAdapter.createFromResource(context, R.array.event_locations, R.layout.event_filter_spinner_item);
        //adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spLocation.setAdapter(adapterLocation);

        ArrayAdapter<CharSequence> adapterEtat = ArrayAdapter.createFromResource(context, R.array.event_etat, R.layout.event_filter_spinner_item);
        spEtat.setAdapter(adapterEtat);

    }

    public Spinner getSpLocation() {
        return spLocation;
    }

    public String getSelectedLocation() {
        return spLocation.getSelectedItem().toString();
    }

    public String getSelectedEtat() {
        return spEtat.getSelectedItem().toString();
    }


}
