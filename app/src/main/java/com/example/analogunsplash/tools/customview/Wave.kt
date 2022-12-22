package com.example.analogunsplash.tools.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.sin

class Wave @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        strokeWidth = 5F
    }

    private var width = 0f
    private var height = 0f

    private var heightWave = 0f
    private var widthWave = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w.toFloat()
        height = h.toFloat()
        heightWave = 0.04f
        widthWave = 1.5f
        createPathWave(heightWave, widthWave)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(path, paint)
    }

    fun setHeightWave(heightWave: Float) {
        this.heightWave = height * heightWave
        createPathWave(heightWave, widthWave)
        invalidate()
    }

    val path = Path()

    private fun createPathWave(heightWave: Float, widthWave: Float) {
        path.reset()
        var y = 0.0
        var x = 0f

      val b= if (heightWave <0.04)  height*heightWave
        else -heightWave *heightWave

        path.moveTo(x, height / 2)
        while (x < width) {
            val a = x / width * (widthWave * 3.14)
            y = height / 2 - sin(a) * (b)
            path.lineTo(x, y.toFloat())
            x++
        }
        path.lineTo(x, height)
        path.lineTo(0f, height)

    }
}