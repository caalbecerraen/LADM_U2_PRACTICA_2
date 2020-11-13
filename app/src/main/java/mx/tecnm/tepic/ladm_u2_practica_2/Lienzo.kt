package mx.tecnm.tepic.ladm_u2_practica_2

import android.graphics.Canvas
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import java.util.*
import kotlin.random.Random

class Lienzo (p:MainActivity): View(p) {
    var MX = this.width
    var MY = this.height
    var point: Imagen?=null
    var icono1 = Imagen(this,Random.nextInt(0,500).toFloat(),Random.nextInt(0,700).toFloat(), R.drawable.msc)
    var icono2 = Imagen(this,Random.nextInt(0,500).toFloat(),Random.nextInt(0,700).toFloat(), R.drawable.msc)
    var icono3 = Imagen(this,Random.nextInt(0,500).toFloat(),Random.nextInt(0,700).toFloat(), R.drawable.msc)
    var icono4 = Imagen(this,Random.nextInt(0,500).toFloat(),Random.nextInt(0,700).toFloat(), R.drawable.msc)
    var icono5 = Imagen(this,Random.nextInt(0,500).toFloat(),Random.nextInt(0,700).toFloat(), R.drawable.msc)
    var mancha1=Imagen(this,-2000f,-2000f,R.drawable.splash)
    var mancha2=Imagen(this,-2000f,-2000f,R.drawable.splash)
    var mancha3=Imagen(this,-2000f,-2000f,R.drawable.splash)
    var mancha4=Imagen(this,-2000f,-2000f,R.drawable.splash)
    var mancha5=Imagen(this,-2000f,-2000f,R.drawable.splash)
    var START = Imagen(this,400f,1600f,R.drawable.ico_start)
    var Mosca1=Hilo(this,icono1)
    var Mosca2=Hilo(this,icono2)
    var Mosca3=Hilo(this,icono3)
    var Mosca4=Hilo(this,icono4)
    var Mosca5=Hilo(this,icono5)
    var Resultado="Sin Puntos"
    var CONTM=1//Cuenta la cantidad de moscas muertas
    var IN=false//Ejecución del hilo solo una vez
    var tiempo=0
    var tiemporeal=0
    val LAPSO=1000
    val TIEMPOTOTAL=9999999
    var contadorTimer=0
    var contadorBien=60
    var timer =object: CountDownTimer(TIEMPOTOTAL.toLong(),LAPSO.toLong()){
        override fun onTick(p0: Long){
            //On tick se  ejecuta cuando el tiempo "Lapso llega a ser 0"
            contadorTimer++
            //text_2.text="Timer ${contadorTimer}"
        }
        override fun onFinish(){
            //On fish se ejecutara cuando el tiempo TOTAL se hace 0  una "
            start()
        }
    }
    override fun onDraw(c: Canvas) {
        try {
            //tiempo++
            super.onDraw(c)
            var p = Paint()
            START.pintar(c)
            p.textSize = 40f
            c.drawText(Resultado, 400f, 1500f, p)
            icono1.pintar(c)
            icono2.pintar(c)
            icono3.pintar(c)
            icono4.pintar(c)
            icono5.pintar(c)
            if (Mosca1.tmp >= 250) {
                mancha1.mover(-2000f, -2000f)
            }
            if (Mosca2.tmp >= 250) {
                mancha2.mover(-2000f, -2000f)
            }
            if (Mosca3.tmp >= 250) {
                mancha3.mover(-2000f, -2000f)
            }
            if (Mosca4.tmp >= 250) {
                mancha4.mover(-2000f, -2000f)
            }
            if (Mosca5.tmp >= 250) {
                mancha5.mover(-2000f, -2000f)
            }
            mancha1.pintar(c)
            mancha2.pintar(c)
            mancha3.pintar(c)
            mancha4.pintar(c)
            mancha5.pintar(c)
            Mosca1.join(1)
            Mosca2.join(1)
            Mosca3.join(1)
            Mosca4.join(1)
            Mosca5.join(1)
            timer.onTick(1)
            contadorBien=60-contadorTimer/50
            c.drawText("Tiempo Restante: "+contadorBien, 400f, 1600f, p)
            if(contadorBien==0){
                Mosca1.eliminameplox=false
                Mosca2.eliminameplox=false
                Mosca3.eliminameplox=false
                Mosca4.eliminameplox=false
                Mosca5.eliminameplox=false
                if(CONTM>=Random.nextInt(0,100)){
                    p.textSize=100f
                    c.drawText("Has ganado!", 400f, 400f, p)}
                p.textSize=40f
                c.drawText("Tiempo Restante: "+0, 400f, 1600f, p)
            }
            if (IN) {
                try {
                    Mosca1.start()
                    Mosca2.start()
                    Mosca3.start()
                    Mosca4.start()
                    Mosca5.start()
                    //timer.start()
                } catch (e: Exception) {
                    try {
                        Mosca1.join(2)
                        Mosca2.join(2)
                        Mosca3.join(2)
                        Mosca4.join(2)
                        Mosca5.join(2)
                    } catch (e: InterruptedException) {
                    }
                }
                IN = false
            }
        }catch(e: InterruptedException){
            Mosca1.join(1)
            Mosca2.join(1)
            Mosca3.join(1)
            Mosca4.join(1)
            Mosca5.join(1)}
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (START.estaEnArea(event.x, event.y) == true) {
                    //Resultado="HAS ELIMINADO:"+CONTM++
                    //point = START
                    START.mover(-2000F,-2000F)
                    IN=true
                }

                if (icono1.estaEnArea(event.x, event.y) == true) {
                    if(contadorBien>=0){
                    Resultado="HAS ELIMINADO:"+CONTM++
                    mancha1.mover(event.x,event.y)
                    Mosca1.matar = false
                    icono1.mover(2000f, 2000f)}
                }
                if (icono2.estaEnArea(event.x, event.y) == true) {
                    if(contadorBien>=0){
                    Resultado="HAS ELIMINADO:"+CONTM++
                    mancha2.mover(event.x,event.y)
                    Mosca2.matar = false
                    icono2.mover(3000f, 3000f)}
                }
                if (icono3.estaEnArea(event.x, event.y) == true) {
                    if(contadorBien>=0){
                    Resultado="HAS ELIMINADO:"+CONTM++
                    mancha3.mover(event.x,event.y)
                    Mosca3.matar = false
                    icono3.mover(4000f, 4000f)}
                }
                if (icono4.estaEnArea(event.x, event.y) == true) {
                    if(contadorBien>=0){
                    Resultado="HAS ELIMINADO:"+CONTM++
                    Mosca4.matar = false
                    mancha4.mover(event.x,event.y)
                    icono4.mover(6000f, 6000f)}
                }
                if (icono5.estaEnArea(event.x, event.y) == true) {
                    if(contadorBien>=0){
                        Resultado="HAS ELIMINADO:"+CONTM++
                        mancha5.mover(event.x,event.y)
                        Mosca5.matar = false
                        icono5.mover(5000f, 5000f)}
                }
            }
        }
        invalidate()
        return true
    }
}
class Hilo(p:Lienzo,i:Imagen):Thread(){
    var puntero = p//Existe solo en esta linea.
    var img=i
    var matar = true
    var bordex=Random.nextBoolean()
    var bordey=Random.nextBoolean()
    var movx=i.x
    var movy=i.y
    var tmp=0
    var eliminameplox=true
    override fun run(){
        super.run()
        while(eliminameplox){
            if(matar){
                if(bordex){
                    movx++
                    if(Random.nextBoolean()){movy--}else{movy++}
                    if(movx>=puntero.width-200){
                    bordex=false
                    bordey=Random.nextBoolean()
                    }
                }else{
                    movx--
                    if(Random.nextBoolean()){movy--}else{movy++}
                    if(movx<=0F){
                        bordex=true
                        bordey=Random.nextBoolean()
                    }
                }
                if(bordey){
                    movy++
                    if(Random.nextBoolean()){movx--}else{movx++}
                    if(movy>=puntero.height.toFloat()-200){
                        bordey=false
                        bordex=Random.nextBoolean()
                    }
                }else{
                    movy--
                    if(Random.nextBoolean()){movx--}else{movx++}
                    if(movy<=0F){
                        bordey=true
                        bordex=Random.nextBoolean()
                    }
                }
                img.mover(movx,movy)
                puntero.invalidate()
            }else{
                if(tmp==300){
                    matar=true
                    movx=Random.nextInt(0,puntero.width-200).toFloat()
                    movy=Random.nextInt(0,puntero.height-200).toFloat()
                    img.mover(movx,movy)
                    tmp=0
                }else{
                    tmp++
                    puntero.invalidate()
                }
            }
            sleep(1)
        }
    }
}
