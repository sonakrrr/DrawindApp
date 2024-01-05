package com.example.lab5

import android.graphics.*
import android.util.Log
import java.math.RoundingMode
import java.text.DecimalFormat

open class Shape(paint: Paint) {
    protected var startX: Float = 0f
    protected var startY: Float = 0f
    protected var endX: Float = 0f
    protected var endY: Float = 0f
    protected var paint = paint

    protected var isGumTrace = true
    protected var isHighlighted = false

    fun setStartCoords(x: Float, y: Float) {
        startX = x
        startY = y
    }

    open fun setEndCoords(x: Float, y: Float) {
        endX = x
        endY = y
    }

    fun formatCoord(coord: Float): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        return df.format(coord) + "\t\t\t\t\t"
    }

    fun toRow(): Row {
        return Row(this::class.java.name + "\t\t\t\t\t", formatCoord(startX), formatCoord(startY), formatCoord(endX), formatCoord(endY), this)
    }

    @JvmName("setGumTrace1")
    fun setGumTrace(gumTrace: Boolean) {
        isGumTrace = gumTrace
    }

    fun setHighlight() {
        isHighlighted = !isHighlighted
        Log.d("HIGHLIGHT", "It works!")
    }

    open fun show(canvas: Canvas) {
        if (isHighlighted) {
            paint.apply {
                color = Color.RED
            }
        }
    }

    open fun setPaintStyle() {
        paint.apply {
            pathEffect = PathEffect()
        }
    }

    fun setGumTracePaintStyle() {
        val dashIntervals = FloatArray(2) {30f; 10f}
        paint.apply {
            color = Color.BLUE
            style = Paint.Style.STROKE
            pathEffect = DashPathEffect(dashIntervals, 0f)
        }
    }

    open fun createShape(): Shape {
        return Shape(paint)
    }

    fun getHighlightState(): Boolean {
        return isHighlighted
    }
}