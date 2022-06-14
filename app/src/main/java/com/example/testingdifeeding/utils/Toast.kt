package com.example.testingdifeeding.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

class Toast {
    companion object {
        fun showToast(mContext: Context, message: String) {
            val toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL, 0, 0)
            toast.show()
        }
    }
}