package com.example.lab5.shape_prims

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab5.Shape

open class RectShape(paint: Paint) : Shape(paint) {
    override fun show(canvas: Canvas) {
        if (!isGumTrace) {
            setPaintStyle()
            super.show(canvas)
            canvas.drawRect(startX, startY, endX, endY, paint)
        } else {
            setGumTracePaintStyle()
            canvas.drawRect(startX, startY, endX, endY, paint)
        }
    }

    override fun createShape(): Shape {
        return RectShape(paint)
    }

    override fun setPaintStyle() {
        super.setPaintStyle()
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
        }
    }
}