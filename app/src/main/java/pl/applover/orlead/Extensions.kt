package pl.applover.orlead

import android.content.Context
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.graphics.drawable.DrawableCompat
import android.os.Build
import android.support.v4.content.ContextCompat
import android.graphics.drawable.Drawable


/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
fun getArrayOfLatLongs(vararg coordinates: Double): ArrayList<LatLng> {
    return if (coordinates.size % 2 == 1) {
        Log.e("Coordinate Parse", "Coordinate number is not even!")
        arrayListOf()
    } else {
        val points = arrayListOf<LatLng>()
        for (i in 0 until coordinates.size step 2)
            points.add(LatLng(coordinates[i], coordinates[i + 1]))
        points
    }
}

fun Context.getBitmapFromVectorDrawable(drawableId: Int): Bitmap {
    var drawable = ContextCompat.getDrawable(this, drawableId)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        drawable = DrawableCompat.wrap(drawable!!).mutate()
    }

    val bitmap = Bitmap.createBitmap(
        drawable!!.intrinsicWidth,
        drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    drawable.draw(canvas)

    return bitmap
}