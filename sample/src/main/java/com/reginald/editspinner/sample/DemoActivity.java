package com.reginald.editspinner.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.reginald.editspinner.EditSpinner;

public class DemoActivity extends Activity {

    private static final String TAG = "DemoActivity";

    EditSpinner mEditSpinner1;
    EditSpinner mEditSpinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_layout);
        initViews();
    }

    private void initViews() {

        // EditSpinner 1:
        mEditSpinner1 = (EditSpinner) findViewById(R.id.edit_spinner_1);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.edits_array_1));
        mEditSpinner1.setAdapter(adapter);

        // triggered when dropdown popup window dismissed
        mEditSpinner1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d(TAG, "mEditSpinner1 popup window dismissed!");
            }
        });

        // triggered when dropdown popup window shown
        mEditSpinner1.setOnShowListener(new EditSpinner.OnShowListener() {
            @Override
            public void onShow() {
                // maybe you want to hide the soft input panel when the popup window is showing.
                hideSoftInputPanel();
            }
        });

        // other optional configurations:

        // use setEditable() to dynamically set whether it can be Edited.
        // Notice: it won't work if you set android:editable="false" in xml.
        // mEditSpinner1.setEditable(false);

        // set the dropdown drawable on the right of EditText and its size
        // mEditSpinner1.setDropDownDrawable(getResources().getDrawable(R.drawable.picker), 60, 60);

        // set the spacing bewteen Edited area and DropDown click area
        // mEditSpinner1.setDropDownDrawableSpacing(50);

        // set DropDown animation of the popup window
        // mEditSpinner1.setDropDownAnimationStyle(R.style.CustomPopupAnimation);

        // set DropDown popup window background
        // mEditSpinner1.setDropDownBackgroundResource(R.drawable.your_custom_dropdown_bkg);

        // set the selection
        // mEditSpinner1.selectItem(1);

        // see more in EditSpinner


        // EditSpinner 2:
        final String[] stringArray2 = getResources().getStringArray(R.array.edits_array_2);
        mEditSpinner2 = (EditSpinner) findViewById(R.id.edit_spinner_2);
        mEditSpinner2.setDropDownDrawable(getResources().getDrawable(R.drawable.spinner), 25, 25);
        mEditSpinner2.setDropDownDrawableSpacing(50);
        mEditSpinner2.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return stringArray2.length;
            }

            @Override
            public Object getItem(int position) {
                return stringArray2[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(DemoActivity.this, R.layout.layout_item, null);
                }

                ImageView icon = convertView.findViewById(R.id.item_icon);
                TextView textView = convertView.findViewById(R.id.item_text);

                String data = (String) getItem(position);

                icon.setImageResource(R.mipmap.ic_launcher);
                textView.setText(data);

                return convertView;
            }
        });

        // it converts the item in the list to a string shown in EditText.
        mEditSpinner2.setItemConverter(new EditSpinner.ItemConverter() {
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
        mEditSpinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick() position = " + position);
                if (position == stringArray2.length - 1) {
                    showSoftInputPanel(mEditSpinner2);
                }
            }
        });

        mEditSpinner2.setOnShowListener(new EditSpinner.OnShowListener() {
            @Override
            public void onShow() {
                hideSoftInputPanel();
            }
        });

        // select the first item initially
        mEditSpinner2.selectItem(1);
    }


    private void hideSoftInputPanel() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mEditSpinner1.getWindowToken(), 0);
        }
    }

    private void showSoftInputPanel(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

}
