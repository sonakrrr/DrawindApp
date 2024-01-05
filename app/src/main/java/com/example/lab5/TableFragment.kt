package com.example.lab5

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.allViews
import com.example.lab5.R
import java.io.File
import java.io.FileInputStream

class TableFragment : Fragment() {

    private val rows = mutableListOf<Row>()

    private lateinit var fragmentView: View
    private lateinit var mainCanvas: MainCanvas
    private lateinit var tableLayout: TableLayout
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.fragment_table, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainCanvas = requireActivity().findViewById(R.id.mainCanvas)
        tableLayout = fragmentView.findViewById(R.id.tableLayout)
        setLayout(tableLayout)
        mainCanvas.setTable(this)
        saveButton = fragmentView.findViewById(R.id.saveButton)
        loadButton = fragmentView.findViewById(R.id.loadButton)

        saveButton.setOnClickListener {
            val path = context?.filesDir
            val file = File(path, "DATA")
            file.delete()
            for (row in this.getRows()) {
                file.appendText(row.name.trim() + "\t" + row.x1.trim() + "\t" + row.y1.trim() + "\t"
                                    + row.x2.trim() + "\t" + row.y2.trim() + "\n")
            }
        }

        loadButton.setOnClickListener {
            clearRows()
            setLayout(tableLayout)
            val path = context?.filesDir
            val file = File(path, "DATA")
            val inputAsString = FileInputStream(file).bufferedReader().use {
                it.readText()
            }
            val splitInput = inputAsString.split("\n")
            val shapesData = splitInput.filter { splitInput.indexOf(it) != splitInput.lastIndex }
            mainCanvas.loadData(shapesData)
        }
    }

    fun addRow(row: Row) {
        rows.add(row)
        val newTextRow = TableRow(tableLayout.context)
        val name = TextView(newTextRow.context).apply { text = row.name.split(".").last()}
        val x1 = TextView(newTextRow.context).apply { text = row.x1}
        val y1 = TextView(newTextRow.context).apply { text = row.y1}
        val x2 = TextView(newTextRow.context).apply { text = row.x2}
        val y2 = TextView(newTextRow.context).apply { text = row.y2}
        newTextRow.addView(name)
        newTextRow.addView(x1)
        newTextRow.addView(y1)
        newTextRow.addView(x2)
        newTextRow.addView(y2)

        for (i in 0..newTextRow.childCount) {
            val textView: TextView? = newTextRow.getChildAt(i) as TextView?
            textView?.setTextColor(Color.BLACK)
        }

        newTextRow.setOnLongClickListener {
            ShapeManager.removeOnIndex(
                mainCanvas.getShapes().indexOf(row.ref),
                mainCanvas.getShapes()
            )
            tableLayout.removeViewInLayout(newTextRow)
            rows.remove(row)
            tableLayout.invalidate()
            mainCanvas.invalidate()
            true
        }
        newTextRow.setOnClickListener {

            for (i in 0..newTextRow.childCount) {
                val textView: TextView? = newTextRow.getChildAt(i) as TextView?
                if (!row.ref.getHighlightState()) {
                    textView?.setTextColor(Color.RED)
                } else {
                    textView?.setTextColor(Color.BLACK)
                }
            }

            row.ref.setHighlight()
            mainCanvas.invalidate()
        }
        tableLayout.addView(newTextRow)
    }

    fun setLayout(layout: TableLayout) {
        this.tableLayout = layout
        val headerTableRow = TableRow(this.tableLayout.context)
        val name = TextView(headerTableRow.context).apply { text = "Фігура\t\t\t\t\t"}
        val x1 = TextView(headerTableRow.context).apply { text = "x1\t\t\t\t\t"}
        val y1 = TextView(headerTableRow.context).apply { text = "y1\t\t\t\t\t"}
        val x2 = TextView(headerTableRow.context).apply { text = "x2\t\t\t\t\t"}
        val y2 = TextView(headerTableRow.context).apply { text = "y2\t\t\t\t\t"}
        headerTableRow.addView(name)
        headerTableRow.addView(x1)
        headerTableRow.addView(y1)
        headerTableRow.addView(x2)
        headerTableRow.addView(y2)
        this.tableLayout.addView(headerTableRow)
    }

    fun getRows(): MutableList<Row> {
        return rows
    }

    fun clearRows() {
        for (i in rows.size-1 downTo 0) {
            rows.removeAt(rows.lastIndex)
        }
        tableLayout.removeAllViews()
    }
}