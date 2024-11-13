package com.nalldev.keep.utils

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate

object CommonHelper {
    fun useLightTheme(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val uiModeManager =
                context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
            uiModeManager.setApplicationNightMode(
                UiModeManager.MODE_NIGHT_NO
            )
        } else {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    fun clearEditTexts(editTexts: List<EditText>) {
        editTexts.forEach {
            it.text.clear()
        }
    }
}