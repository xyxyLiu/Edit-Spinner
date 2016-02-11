package com.reginald.editablespinner.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.reginald.editablespinner.EditableSpinner;

public class DemoActivity extends Activity {

    EditableSpinner mEditableSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_layout);
        initViews();
    }

    private void initViews() {
        mEditableSpinner = (EditableSpinner) findViewById(R.id.editable_spinner);
        mEditableSpinner.setDrawable(getResources().getDrawable(R.drawable.picker), 60, 60);
        mEditableSpinner.setRightDrawablePadding(50);
        mEditableSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.edits_array)));
    }



}
