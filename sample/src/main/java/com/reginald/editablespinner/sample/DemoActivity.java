package com.reginald.editablespinner.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

import com.reginald.editablespinner.EditableSpinner;

public class DemoActivity extends Activity {

    private static final String TAG = "DemoActivity";

    EditableSpinner mEditableSpinner1;
    EditableSpinner mEditableSpinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_layout);
        initViews();
    }

    private void initViews() {
        mEditableSpinner1 = (EditableSpinner) findViewById(R.id.editable_spinner_1);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.edits_array_1));
        mEditableSpinner1.setAdapter(adapter);

//        mEditableSpinner1.setDropDownDrawable(getResources().getDrawable(R.drawable.picker), 60, 60);
//        mEditableSpinner1.setDropDownDrawableSpacing(50);
//        mEditableSpinner1.setDropDownAnimationStyle(R.style.CustomPopupAnimation);
//        mEditableSpinner1.setDropDownBackgroundResource(R.drawable.your_custom_dropdown_bkg);
        mEditableSpinner1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d(TAG, "mEditableSpinner1 popup window dismissed!");
            }
        });

        mEditableSpinner1.setOnShowListener(new EditableSpinner.OnShowListener() {
            @Override
            public void onShow() {
                hideSoftInputPanel();
            }
        });


        final String[] stringArray2 = getResources().getStringArray(R.array.edits_array_2);
        mEditableSpinner2 = (EditableSpinner) findViewById(R.id.editable_spinner_2);
        mEditableSpinner2.setDropDownDrawable(getResources().getDrawable(R.drawable.spinner), 25, 25);
        mEditableSpinner2.setDropDownDrawableSpacing(50);
        mEditableSpinner2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArray2));
        mEditableSpinner2.setItemConverter(new EditableSpinner.ItemConverter() {
            @Override
            public String convertItemToString(Object selectedItem) {
                if (selectedItem.toString().equals(stringArray2[stringArray2.length - 1])) {
                    return "";
                } else {
                    return selectedItem.toString();
                }
            }
        });

        mEditableSpinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick() position = " + position);
                if (position == stringArray2.length - 1) {
                    showSoftInputPanel(mEditableSpinner2);
                }
            }
        });

        mEditableSpinner2.setOnShowListener(new EditableSpinner.OnShowListener() {
            @Override
            public void onShow() {
                hideSoftInputPanel();
            }
        });

        mEditableSpinner2.selectItem(0);
    }


    private void hideSoftInputPanel() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mEditableSpinner1.getWindowToken(), 0);
        }
    }

    private void showSoftInputPanel(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

}
