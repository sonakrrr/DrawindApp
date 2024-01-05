package com.example.lab5

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MainCanvas (context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    private val USER_STROKE_WIDTH = 8f
    private val MAX_LIST_SIZE: Int = 114

    private val backgroundColor = Color.BLACK
    private var canvas1: Canvas = Canvas()

    private val penColor = Color.BLACK

    val paint = Paint().apply {
        color = penColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeWidth = USER_STROKE_WIDTH
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private var motionX = 0f
    private var motionY = 0f

    private val shapes = arrayOfNulls<Shape>(MAX_LIST_SIZE)

    private var shapeEditor: MyEditor = MyEditor.getInstance(paint, shapes)

    private lateinit var table: TableFragment

    fun getShapes(): Array<Shape?> {
        return shapes
    }

    fun setShapeEditorCurrentShape(shape: Shape) {
        shapeEditor.setCurrentShape(shape)
    }

    fun setTable(table: TableFragment) {
        this.table = table
        shapeEditor.setTable(table)
    }

    fun loadData(data: List<String>) {
        clearShapes()
        for (string in data) {
            val fields = string.split("\t")

            for (field in fields) {
                Log.d("FIELD", field)
            }
            for (i in shapes) {
                i?.show(canvas1)
            }

            val constructor = Class.forName(fields[0]).getConstructor(paint::class.java)
            val shape: Shape = constructor.newInstance(paint) as Shape
            shape.setStartCoords(fields[1].toFloat(), fields[2].toFloat())
            shape.setEndCoords(fields[3].toFloat(), fields[4].toFloat())
            shape.setGumTrace(false)
            table.addRow(shape.toRow())
            ShapeManager.addShape(shape, shapes)
            invalidate()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        canvas1 = Canvas()
        canvas1.drawColor(backgroundColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (i in shapes) {
            i?.show(canvas!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        motionX = event!!.x
        motionY = event.y
        Log.d("EVENT_ACTION", event.action.toString())
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchUp() {
        invalidate()
        shapeEditor.onLBUp()
    }

    private fun touchMove() {
        invalidate()
        shapeEditor.onMouseMove(motionX, motionY)
        }

    private fun touchStart() {
        invalidate()
        shapeEditor.onLBDown(motionX, motionY)
    }

    private fun clearShapes() {
        for (i in shapes.indices)
            shapes[i] = null
        ShapeManager.setLastIndex(0)
    }

}