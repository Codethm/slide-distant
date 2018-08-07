package me.codethm.slide_distant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.util.Log
import android.view.GestureDetector


abstract class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gD = GestureDetector(this,GestureDetector.SimpleOnGestureListener())

        view.setOnTouchListener { _, motionEvent -> gD.onTouchEvent(motionEvent) }

    }

    override fun onFling(event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        Log.d("DEBUG_TAG", "onFling: " + event1.toString() + event2.toString())
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        val action = event.actionMasked


        when (action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("DEBUG_TAG", "Action was DOWN")
                Log.d("DEBUG_TAG", event.getPointerId(0).toString())
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("DEBUG_TAG", "Action was MOVE")
                Log.d("POSITION_X",x.toString())
                Log.d("POSITION_Y",y.toString())
                return true
            }
            MotionEvent.ACTION_UP -> {
                Log.d("DEBUG_TAG", "Action was UP")
                return true
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.d("DEBUG_TAG", "Action was CANCEL")
                return true
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Log.d("DEBUG_TAG", "Movement occurred outside bounds " + "of current screen element")
                return true
            }
            else -> return super.onTouchEvent(event)
        }
    }
}