package com.example.lab5.shape_prims

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab5.Shape

open class LineShape(paint: Paint): Shape(paint) {
    override fun show(canvas: Canvas) {
        if (!isGumTrace) {
            setPaintStyle()
            super.show(canvas)
        }
        else {
            setGumTracePaintStyle()
        }
        canvas.drawLine(startX, startY, endX, endY, paint)
    }

    override fun createShape(): Shape {
        return LineShape(paint)
    }

    override fun setPaintStyle() {
        super.setPaintStyle()
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
        }
    }
}