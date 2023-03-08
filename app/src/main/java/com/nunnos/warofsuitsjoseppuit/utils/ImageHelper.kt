package com.nunnos.warofsuitsjoseppuit.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView

class ImageHelper {
    companion object {
        fun resizeImage(imageView: ImageView, bm: Bitmap?) {
            if (bm == null) return
            val width = bm.width.toFloat()
            val height = bm.height.toFloat()
            val proporcion = if (height > width) height / width else width / height
            imageView.scaleY = proporcion
            imageView.scaleX = proporcion
        }

        fun drawableToBitmap(drawable: Drawable): Bitmap? {
            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }
    }
}