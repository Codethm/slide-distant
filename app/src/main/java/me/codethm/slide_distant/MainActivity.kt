package me.codethm.slide_distant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    override fun onCreate(savedInstanceState: Bundle?) {

         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         val gD = GestureDetector(this,GestureListener())
         imageView.setOnGenericMotionListener { _, motionEvent -> gD.onGenericMotionEvent(motionEvent) }

        val intent = Intent(this,TranslateFlingAnimationActivity::class.java)
        button.setOnClickListener { startActivity(intent) }
    }

    //////////////////////////////////////////////////////

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        val action = event.actionMasked

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("DEBUG_TAG", "Action was DOWN")
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("DEBUG_TAG", "Action was MOVE" + x.toString() + " and " + y.toString())
                imageView.x = x - 30
                imageView.y = y - 270
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

     override fun onFling(event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
         Log.d("DEBUG_TAG", "onFling: " + event1.toString() + event2.toString())
         val flingX = FlingAnimation(imageView, DynamicAnimation.TRANSLATION_X)
         flingX.setStartVelocity(velocityX)
                 .setMinValue(0f) // minimum translationX property
                 .setMaxValue(600f)  // maximum translationX property
                 .setFriction(5f)
                 .start()

         return true
     }

     override fun onDown(event: MotionEvent): Boolean {
         Log.d("DEBUG_TAG", "onDown: " + event.toString())
         return true
     }

     override fun onLongPress(event: MotionEvent) {
         Log.d("DEBUG_TAG", "onLongPress: " + event.toString())
     }

     override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
         Log.d("DEBUG_TAG", "onScroll: " + e1.toString() + e2.toString())
         return true
     }

     override fun onShowPress(event: MotionEvent) {
         Log.d("DEBUG_TAG", "onShowPress: " + event.toString())
     }

     override fun onSingleTapUp(event: MotionEvent): Boolean {
         Log.d("DEBUG_TAG", "onSingleTapUp: " + event.toString())
         return true
     }

     private val SWIPE_MIN_DISTANCE = 120
     private val SWIPE_THRESHOLD_VELOCITY = 200

     private inner class GestureListener : SimpleOnGestureListener() {
         override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
             if (e1.x - e2.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                 return false // Right to left
             } else if (e2.x - e1.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                 return false // Left to right
             }

             if (e1.y - e2.y > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                 return false // Bottom to top
             } else if (e2.y - e1.y > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                 return false // Top to bottom
             }
             return false
         }
     }
}