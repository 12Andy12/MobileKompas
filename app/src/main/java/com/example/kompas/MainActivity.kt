package com.example.kompas

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var manager: SensorManager? = null
    private var currentDegree:Int = 0
    private lateinit var textView : TextView
    private lateinit var kompas : ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.rotate)
        textView.text = "aboba"
        kompas = findViewById(R.id.kompasss)
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()
        manager?.registerListener(this,manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME)

    }

    override fun onPause() {
        super.onPause()
        manager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p: SensorEvent?) {
        val degree:Int = p?.values?.get(0)?.toInt()!!
        println(degree)
        textView.text = degree.toString()
        val rotationAnim = RotateAnimation(currentDegree.toFloat(),(-degree).toFloat(),Animation.RELATIVE_TO_SELF,
            0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        rotationAnim.duration = 210
        rotationAnim.fillAfter = true
        currentDegree = -degree
        kompas.startAnimation(rotationAnim)

    }
}