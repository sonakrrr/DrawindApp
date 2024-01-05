package com.example.lab5

import android.graphics.Paint
import com.example.lab5.shape_prims.LineShape

open class MyEditor private constructor (paint: Paint, shapes: Array<Shape?>) {
    val paint = paint
    val shapes = shapes

    private var currentShape: Shape = LineShape(paint)

    private lateinit var table: TableFragment

    fun onLBDown(x: Float, y: Float) {
        setCurrentShape(currentShape)
        currentShape.setStartCoords(x, y)
    }

    fun onLBUp() {
        if (shapes.contains(currentShape))
            ShapeManager.removeLastShape(shapes)
        currentShape.setGumTrace(false)
        ShapeManager.addShape(currentShape, shapes)
        table.addRow(currentShape.toRow())
    }

    fun onMouseMove(x: Float, y: Float) {
        if (shapes.contains(currentShape))
            ShapeManager.removeLastShape(shapes)
        currentShape.setEndCoords(x, y)
        ShapeManager.addShape(currentShape, shapes)
    }

    fun setCurrentShape(shape: Shape) {
        currentShape = shape.createShape()
    }

    fun setTable(table: TableFragment) {
        this.table = table
    }

    companion object {
        private var instance: MyEditor? = null
        fun getInstance(paint: Paint, shapes: Array<Shape?>): MyEditor {
            if (instance == null) {
                instance = MyEditor(paint, shapes)
            }
            return instance!!
        }
    }
}