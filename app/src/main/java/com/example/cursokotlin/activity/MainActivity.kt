package com.example.cursokotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import com.example.cursokotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    private var weight= 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(getString(R.string.btn_login))
        val heightTv:TextView=findViewById(R.id.texto2)
        val rest:FloatingActionButton=findViewById(R.id.rest)
        val plus:FloatingActionButton=findViewById(R.id.plus)
        var slider:RangeSlider=findViewById(R.id.slider)

        slider.addOnChangeListener { _, value, _ ->
            heightTv.text =" ${value.toString()} cm"
        }
        rest.setOnClickListener {
            weight++
            weightKg()
        }
        plus.setOnClickListener {
            weight--
            weightKg()
        }

}
    fun weightKg() {
        var WeightText:TextView=findViewById(R.id.texto3)
        WeightText.text="Peso $weight Kg"
    }
}