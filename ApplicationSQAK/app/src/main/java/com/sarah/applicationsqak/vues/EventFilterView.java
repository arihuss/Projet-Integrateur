package com.sarah.applicationsqak.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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

    }

    public String getSelectedCategory() {
        return spLocation.getSelectedItem().toString();
    }

    public String getSelectedEtat() {
        return spEtat.getSelectedItem().toString();
    }

//    public void setOnFilterApplyListener(OnClickListener listener) {
//        buttonApply.setOnClickListener(listener);
//    }
}
