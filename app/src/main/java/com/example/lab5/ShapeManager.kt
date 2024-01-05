package com.example.lab5

import android.util.Log

class ShapeManager {
    companion object {
        private const val MAX_LIST_SIZE: Int = 114
        private var lastIndex: Int = 0
        fun addShape(shape: Shape, shapes: Array<Shape?>) {
            if (lastIndex == MAX_LIST_SIZE) {
                removeFirstShape(shapes)
            }
            shapes[lastIndex] = shape
            lastIndex++
            Log.d("ARRAY SIZE", lastIndex.toString())
        }
        fun removeLastShape(shapes: Array<Shape?>) {
            shapes[lastIndex] = null
            lastIndex--
        }
        fun removeFirstShape(shapes: Array<Shape?>) {
            shapes[0] = null
            for (i in 1 until shapes.size) {
                shapes[i-1] = shapes[i]
            }
            lastIndex--
        }
        fun removeOnIndex(i: Int, shapes: Array<Shape?>) {
            shapes[i] = null
            for (i in i+1 until shapes.size) {
                shapes[i-1] = shapes[i]
            }
            lastIndex--
        }
        fun setLastIndex(i: Int) {
            lastIndex = i
        }
    }
}