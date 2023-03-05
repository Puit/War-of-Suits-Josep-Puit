package com.nunnos.warofsuitsjoseppuit.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

class AlertsManager {
    companion object {
        fun showTwoButtonsAlert(
            activity: Activity?,
            listener: TwoButtonsAlertListener,
            message: String?,
            leftButton: String?,
            rightButton: String?,
            closeOnRight: Boolean
        ) {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(rightButton) { dialog: DialogInterface, id: Int ->
                    listener.onRightClick()
                    if (closeOnRight) {
                        dialog.cancel()
                    }
                }
                .setNegativeButton(
                    leftButton
                ) { dialog: DialogInterface?, id: Int -> listener.onLeftClick() }
            val alert = builder.create()
            alert.show()
        }
    }

    interface TwoButtonsAlertListener {
        fun onLeftClick()
        fun onRightClick()
    }

}