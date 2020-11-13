package mx.tecnm.tepic.ladm_u2_practica_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Imagen (punteroLienzo: Lienzo, posX:Float, posY:Float,nombreImagen: Int) {
    var x = posX
    var y = posY
    var imaged = BitmapFactory.decodeResource(punteroLienzo.resources, nombreImagen)
    var imagen= Bitmap.createScaledBitmap(imaged,200,200,true)
    fun pintar(c: Canvas) {
        c.drawBitmap(imagen, x, y, Paint())
    }

    fun estaEnArea(toqueX: Float, toqueY: Float): Boolean {
        var x2 = x + imagen.width
        var y2 = y + imagen.height
        if (toqueX >= x && toqueX <= x2) {
            if (toqueY >= y && toqueY <= y2)
                return true
        }
        return false
    }

    fun mover(toqueX: Float, toqueY: Float){
        x=toqueX
        y=toqueY
    }


}