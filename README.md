# EditableSpinner
An EditText which supports spinner FOR `API LEVEL >= 11`

<div><img src='https://github.com/xyxyLiu/EditableSpinner/blob/master/art/demo.gif' width="400px" style='border: #f1f1f1 solid 1px'/></div>

[Download Demo APK](https://github.com/xyxyLiu/EditableSpinner/releases/download/1.0/EditableSpinner-Demo-1.0.apk)

## Usage
add EditableSpinner in xml
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

setup datas using ListAdapter
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

