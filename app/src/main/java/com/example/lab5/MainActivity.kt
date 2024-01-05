package com.example.lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.lab5.R
import com.example.lab5.shape_prims.CubeShape
import com.example.lab5.shape_prims.EllipseShape
import com.example.lab5.shape_prims.LineShape
import com.example.lab5.shape_prims.LineWithPointsShape
import com.example.lab5.shape_prims.PointShape
import com.example.lab5.shape_prims.RectShape

class MainActivity : AppCompatActivity() {
    private lateinit var mainCanvas: MainCanvas
    private lateinit var currentCheckedOption: MenuItem
    private lateinit var currentSelectedOption: MenuItem
    private lateinit var mainMenu: Menu
    private lateinit var frameLayout: FrameLayout
    private lateinit var tableFragment: TableFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Lab 5"
        setContentView(R.layout.activity_main)
        mainCanvas = findViewById(R.id.mainCanvas)
        mainCanvas.setShapeEditorCurrentShape(LineShape(mainCanvas.paint))

        frameLayout = findViewById(R.id.tableContainer)

        tableFragment = TableFragment()
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.tableContainer, tableFragment)
        ft.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mainMenuInflater: MenuInflater = menuInflater
        mainMenuInflater.inflate(R.menu.main_menu, menu)
        mainMenu = menu!!
        currentCheckedOption = mainMenu.findItem(R.id.lineSelect)
        currentCheckedOption.isChecked = true
        currentSelectedOption = mainMenu.findItem(R.id.lineButton)
        currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.line)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId != R.id.toggleTableButton && item.itemId != R.id.toggleTableSelect)
            setIconInactive(currentSelectedOption)
        when (item.itemId) {
            R.id.ellipseButton, R.id.ellipseSelect -> {
                currentSelectedOption = mainMenu.findItem(R.id.ellipseButton)
                mainCanvas.setShapeEditorCurrentShape(EllipseShape(mainCanvas.paint))
                currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.ellipse)
            }
            R.id.lineButton, R.id.lineSelect -> {
                currentSelectedOption = mainMenu.findItem(R.id.lineButton)
                mainCanvas.setShapeEditorCurrentShape(LineShape(mainCanvas.paint))
                currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.line)
            }
            R.id.pointButton, R.id.pointSelect -> {
                currentSelectedOption = mainMenu.findItem(R.id.pointButton)
                mainCanvas.setShapeEditorCurrentShape(PointShape(mainCanvas.paint))
                currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.point)
            }
            R.id.rectButton, R.id.rectSelect -> {
                currentSelectedOption = mainMenu.findItem(R.id.rectButton)
                mainCanvas.setShapeEditorCurrentShape(RectShape(mainCanvas.paint))
                currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.rectangle)
            }
            R.id.lineWithPointsButton, R.id.lineWithPointsSelect -> {
                currentSelectedOption = mainMenu.findItem(R.id.lineWithPointsButton)
                mainCanvas.setShapeEditorCurrentShape(LineWithPointsShape(mainCanvas.paint))
                currentSelectedOption.icon = ContextCompat.getDrawable(this,
                    R.drawable.linewithpoints
                )
            }
            R.id.cubeButton, R.id.cubeSelect -> {
                currentSelectedOption = mainMenu.findItem(R.id.cubeButton)
                mainCanvas.setShapeEditorCurrentShape(CubeShape(mainCanvas.paint))
                currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.cube)
            }
            R.id.toggleTableButton, R.id.toggleTableSelect -> {
                if (frameLayout.visibility == View.GONE) {
                    frameLayout.visibility = View.VISIBLE
                } else {
                    frameLayout.visibility = View.GONE
                }
            }
        }
        currentCheckedOption.isChecked = false
        currentCheckedOption = item
        currentCheckedOption.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    private fun setIconInactive(item: MenuItem) {
        when(item.itemId) {
            R.id.ellipseButton -> {
                currentSelectedOption.icon = ContextCompat.getDrawable(this,
                    R.drawable.ellipse_inactive
                )
            }
            R.id.lineButton -> {
                currentSelectedOption.icon = ContextCompat.getDrawable(this,
                    R.drawable.line_inactive
                )
            }
            R.id.pointButton -> {
                currentSelectedOption.icon = ContextCompat.getDrawable(this,
                    R.drawable.point_inactive
                )
            }
            R.id.rectButton -> {
                currentSelectedOption.icon = ContextCompat.getDrawable(this,
                    R.drawable.rectangle_inactive
                )
            }
            R.id.lineWithPointsButton -> {
                currentSelectedOption.icon = ContextCompat.getDrawable(this,
                    R.drawable.linewithpoints_inactive
                )
            }
            R.id.cubeButton -> {
                currentSelectedOption.icon = ContextCompat.getDrawable(this,
                    R.drawable.cube_inactive
                )
            }
        }
    }
}