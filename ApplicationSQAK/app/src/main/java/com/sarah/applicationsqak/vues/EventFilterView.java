package com.sarah.applicationsqak.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.sarah.applicationsqak.R;

public class EventFilterView extends LinearLayout {

    private Spinner spinnerCategory, spinnerDate;
    private Button buttonApply;

    public EventFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.event_filter, this, true);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerDate = findViewById(R.id.spinnerDate);
    }

    public String getSelectedCategory() {
        return spinnerCategory.getSelectedItem().toString();
    }

    public String getSelectedDate() {
        return spinnerDate.getSelectedItem().toString();
    }

    public void setOnFilterApplyListener(OnClickListener listener) {
        buttonApply.setOnClickListener(listener);
    }
}
