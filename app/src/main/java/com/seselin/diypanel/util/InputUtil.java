package com.seselin.diypanel.util;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by Seselin on 2018/5/12 10:13.
 */
public class InputUtil {

    public static boolean checkInput(EditText editText, String errorString) {
        String inputString = editText.getText().toString().trim();
        if (TextUtils.isEmpty(inputString)) {
            editText.setError(errorString);
            editText.requestFocus();
            return true;
        }
        return false;
    }

    public static boolean checkInput(EditText editText) {
        return checkInput(editText, "未输入");
    }

}
