package com.example.lab5.shape_prims

import android.graphics.Canvas
import android.graphics.Paint
import com.example.lab5.Shape

class LineWithPointsShape(paint: Paint) : LineShape(paint) {
    private var circleSize = 20f

    override fun show(canvas: Canvas) {
        super.show(canvas)
        canvas.drawCircle(startX, startY, circleSize, paint)
        canvas.drawCircle(endX, endY, circleSize, paint)
    }

    override fun createShape(): Shape {
        return LineWithPointsShape(paint)
    }
}