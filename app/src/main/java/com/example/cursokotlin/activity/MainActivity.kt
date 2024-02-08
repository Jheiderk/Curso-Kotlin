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
    private lateinit var heightTv:TextView
    private lateinit var rest:FloatingActionButton
    private lateinit var plus:FloatingActionButton
    private lateinit var slider:RangeSlider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(getString(R.string.btn_login))
        heightTv=findViewById(R.id.texto2)
        rest=findViewById(R.id.rest)
        plus=findViewById(R.id.plus)
        slider=findViewById(R.id.slider)

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