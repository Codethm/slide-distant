package me.codethm.slide_distant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        activity_main.setOnDragListener { view, dragEvent -> divider.canScrollVertically(dragEvent.y.toInt()) }
    }
}