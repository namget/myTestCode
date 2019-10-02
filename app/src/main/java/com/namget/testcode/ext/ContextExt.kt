package com.namget.testcode.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Namget on 2019.09.23.
 */
fun Context.getStringPreference(key: String): String {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)?.getString(key, "")
        ?: ""
}

fun Context.setStringPreference(key: String, value: String) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit {
        putString(key, value)
        apply()
    }
}

fun Context.getIntegerPreference(key: String): Int {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).getInt(key, 0)
}


fun Context.setIntegerPreference(key: String, value: Int) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit {
        putInt(key, value)
        apply()
    }
}

fun Context.getBooleanPreference(key: String, default: Boolean = false): Boolean {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)?.getBoolean(
        key,
        default
    )
        ?: false
}

fun Context.setBooleanPreference(key: String, value: Boolean) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit {
        putBoolean(key, value)
        apply()
    }
}


fun Context.getLongPreference(key: String): Long {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
        ?.getLong(key, 0L) ?: 0L
}

fun Context.setLongPreference(key: String, value: Long) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit {
        putLong(key, value)
        apply()
    }
}

//Toast
fun Context.toastMakeToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
//Toast
fun Context.toastMakeToast(res: Int) {
    Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
}


fun Context.callPhone(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
    startActivity(intent)
}

fun Context.showPlayStore() {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
        //run on playstore app
        setPackage("com.android.vending")
    }.also {
        startActivity(it)
    }

}

fun Context.showKeyboard() {
    GlobalScope.launch {
        delay(100)
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            0
        )
    }
}

fun Context.hideKeyboard() {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
        InputMethodManager.HIDE_IMPLICIT_ONLY,
        0
    )
}