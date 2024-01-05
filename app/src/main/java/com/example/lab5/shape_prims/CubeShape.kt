package com.example.lab5.shape_prims

import android.graphics.Canvas
import android.graphics.Paint
import com.example.lab5.Shape
import kotlin.math.abs

class CubeShape(paint: Paint): RectShape(paint) {
    override fun show(canvas: Canvas) {
        val dx = abs(startX - endX) / 2
        val dy = abs(startY - endY)
        if (!isGumTrace) {
            setPaintStyle()
        } else {
            setGumTracePaintStyle()
        }
        canvas.drawRect(2 * startX - endX, 2 * startY - endY, endX, endY, paint)
        canvas.drawRect(2 * startX - endX + dx, 2 * startY - endY - dy, endX + dx, endY - dy, paint)
        canvas.drawLine(2 * startX - endX, 2 * startY - endY, 2 * startX - endX + dx, 2 * startY - endY - dy, paint)
        canvas.drawLine(endX, endY, endX + dx, endY - dy, paint)
        canvas.drawLine(2 * startX - endX, endY, 2 * startX - endX + dx, endY - dy, paint)
        canvas.drawLine(endX, 2 * startY - endY, endX + dx, 2 * startY - endY - dy, paint)

    }

    override fun createShape(): Shape {
        return CubeShape(paint)
    }
}
