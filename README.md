# EditableSpinner
An EditText which supports spinner FOR `API LEVEL >= 11`

<div><img src='https://github.com/xyxyLiu/EditableSpinner/blob/master/art/demo.gif' width="400px" style='border: #f1f1f1 solid 1px'/></div>


<br>
<br>
[Download Demo APK](https://github.com/xyxyLiu/EditableSpinner/releases/download/1.0/EditableSpinner-Demo-1.0.apk)

## Usage

* **add EditableSpinner in xml**
```xml
<com.reginald.editablespinner.EditableSpinner
        android:id="@+id/editable_spinner"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:dropDownDrawable="@drawable/spinner_icon"
        app:dropDownDrawableSpacing="15dp"
        app:dropDownDrawableWidth="25dp"
        app:dropDownDrawableHeight="25dp"
        />
```

* **setup datas using Adapter**
```java
        mEditableSpinner = (EditableSpinner) findViewById(R.id.editable_spinner);
        ListAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
        getResources().getStringArray(R.array.your_string_array));
        mEditableSpinner.setAdapter(adapter);
```

key properties:

|     attr    	|  required/optional(default value)  	|                         meaning                         	|
|:-----------:	|:---------:	|:----------------------------------------------------:	|
|  dropDownDrawable  	|   required    | dropdown drawable shown on the right of the EditText  	|
|   dropDownDrawableWidth   	|     optional, default using drawable intrinsic width     	|         width of dropdown drawable        	|
| dropDownDrawableHeight 	| optional, default using drawable intrinsic height     	|    height of dropdown drawable     	  	|
| dropDownDrawableSpacing      | optional, default is 0 	|          the spacing between edittext area and dropdown drawable area    |


## More Usages

* **config editable**

In some cases, you want to make it uneditable. You can

use android:editable in xml to disable editable:
```xml
android:editable="false"
```
or set it dynamically:
```java
mEditableSpinner.setEditable(false);
```

* **config dropdown window**

```java
        // set the dropdown drawable on the right of EditText and its size
        mEditableSpinner.setDropDownDrawable(getResources().getDrawable(R.drawable.picker), 60, 60);

        // set the spacing bewteen Edited area and DropDown click area
        mEditableSpinner.setDropDownDrawableSpacing(50);

        // set DropDown popup window background
        mEditableSpinner.setDropDownBackgroundResource(R.drawable.your_custom_dropdown_bkg);

        // set DropDown animation of the popup window
        mEditableSpinner.setDropDownAnimationStyle(R.style.CustomPopupAnimation);
```

* **config listeners**

```java
        // triggered when dropdown popup window dismissed
        mEditableSpinner.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

        // triggered when dropdown popup window shown
        mEditableSpinner.setOnShowListener(new EditableSpinner.OnShowListener() {
            @Override
            public void onShow() {
            }
        });

        // it converts the item in the list to a string shown in EditText.
        mEditableSpinner.setItemConverter(new EditableSpinner.ItemConverter() {
            @Override
            public String convertItemToString(Object selectedItem) {
            }
        });

        // triggered when one item in the list is clicked
        mEditableSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
```

## License

    Copyright 2016 xyxyLiu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
