package com.example.lab5.shape_prims

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab5.Shape

class EllipseShape(paint: Paint): Shape(paint) {
    override fun show(canvas: Canvas) {
        if (!isGumTrace) {
            setFillPaintStyle()
            canvas.drawOval(2 * startX - endX, 2 * startY - endY, endX, endY, paint)
            setPaintStyle()
            super.show(canvas)
            canvas.drawOval(2 * startX - endX, 2 * startY - endY, endX, endY, paint)
        } else {
            setGumTracePaintStyle()
            canvas.drawOval(2 * startX - endX, 2 * startY - endY, endX, endY, paint)
        }
    }

    override fun setPaintStyle() {
        super.setPaintStyle()
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
        }
    }

    private fun setFillPaintStyle() {
        paint.apply {
            color = Color.rgb(170, 255, 140)
            style = Paint.Style.FILL
        }
    }

    override fun createShape(): Shape {
        return EllipseShape(paint)
    }
}