package com.example.lab5.shape_prims

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab5.Shape

class PointShape(paint: Paint) : Shape(paint) {
    override fun show(canvas: Canvas) {
        setPaintStyle()
        super.show(canvas)
        canvas.drawCircle(startX, startY, 10f, paint)
    }

    override fun createShape(): Shape {
        return PointShape(paint)
    }

    override fun setPaintStyle() {
        super.setPaintStyle()
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
        }
    }

    override fun setEndCoords(x: Float, y: Float) {

    }
}