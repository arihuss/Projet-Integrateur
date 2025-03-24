package com.sarah.applicationsqak.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.sarah.applicationsqak.R;

public class EventFilterView extends LinearLayout {

    private Spinner spinnerLocation, spinnerDate;
    private Button buttonApply;

    public EventFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.event_filter, this, true);

        // Liaisions avec la vue
        //spinnerLocation = findViewById(R.id.spinnerLocation);
        //spinnerDate = findViewById(R.id.spinnerDate);
    }

    public String getSelectedCategory() {
        return spinnerLocation.getSelectedItem().toString();
    }

    public String getSelectedDate() {
        return spinnerDate.getSelectedItem().toString();
    }

    public void setOnFilterApplyListener(OnClickListener listener) {
        buttonApply.setOnClickListener(listener);
    }
}
