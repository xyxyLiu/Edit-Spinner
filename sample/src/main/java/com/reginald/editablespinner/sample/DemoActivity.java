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

        // EditableSpinner 1:
        mEditableSpinner1 = (EditableSpinner) findViewById(R.id.editable_spinner_1);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.edits_array_1));
        mEditableSpinner1.setAdapter(adapter);

        // triggered when dropdown popup window dismissed
        mEditableSpinner1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d(TAG, "mEditableSpinner1 popup window dismissed!");
            }
        });

        // triggered when dropdown popup window shown
        mEditableSpinner1.setOnShowListener(new EditableSpinner.OnShowListener() {
            @Override
            public void onShow() {
                // maybe you want to hide the soft input panel when the popup window is showing.
                hideSoftInputPanel();
            }
        });

        // other optional configurations:

        // use setEditable() to dynamically set whether it can be Edited.
        // Notice: it won't work if you set android:editable="false" in xml.
        // mEditableSpinner1.setEditable(false);

        // set the dropdown drawable on the right of EditText and its size
        // mEditableSpinner1.setDropDownDrawable(getResources().getDrawable(R.drawable.picker), 60, 60);

        // set the spacing bewteen Edited area and DropDown click area
        // mEditableSpinner1.setDropDownDrawableSpacing(50);

        // set DropDown animation of the popup window
        // mEditableSpinner1.setDropDownAnimationStyle(R.style.CustomPopupAnimation);

        // set DropDown popup window background
        // mEditableSpinner1.setDropDownBackgroundResource(R.drawable.your_custom_dropdown_bkg);

        // set the selection
        // mEditableSpinner1.selectItem(1);

        // see more in EditableSpinner


        // EditableSpinner 2:
        final String[] stringArray2 = getResources().getStringArray(R.array.edits_array_2);
        mEditableSpinner2 = (EditableSpinner) findViewById(R.id.editable_spinner_2);
        mEditableSpinner2.setDropDownDrawable(getResources().getDrawable(R.drawable.spinner), 25, 25);
        mEditableSpinner2.setDropDownDrawableSpacing(50);
        mEditableSpinner2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArray2));

        // it converts the item in the list to a string shown in EditText.
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

        // triggered when one item in the list is clicked
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

        // select the first item initially
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
