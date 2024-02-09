package com.example.cursokotlin.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import com.example.cursokotlin.R
import com.example.cursokotlin.R.color
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private var weight = 50
    private var heightCurrent = 100
    private lateinit var heightTv: TextView
    private lateinit var rest: FloatingActionButton
    private lateinit var plus: FloatingActionButton
    private lateinit var slider: RangeSlider
    private lateinit var btnImc: Button
    private lateinit var resultTextTv:TextView
    private lateinit var descriptionResult:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        view()
        heightCm()
        weightKg()

        slider.addOnChangeListener { _, value, _ ->
            heightCurrent = value.toInt()
            heightCm()
        }
        rest.setOnClickListener {
            weight++
            weightKg()
        }
        plus.setOnClickListener {
            weight--
            weightKg()
        }
        btnImc.setOnClickListener {
            calculateBMI()
        }
    }
    private fun view() {
        heightTv = findViewById(R.id.texto2)
        rest = findViewById(R.id.restFab)
        plus = findViewById(R.id.plusFab)
        slider = findViewById(R.id.slider)
        btnImc = findViewById(R.id.button)
        resultTextTv= findViewById(R.id.resultTextTv)
        descriptionResult=findViewById(R.id.descriptionResult)
    }

    private fun heightCm() {
        heightTv.text = " $heightCurrent cm"
    }

    fun weightKg() {
        var WeightText: TextView = findViewById(R.id.texto3)
        WeightText.text = "Peso $weight Kg"
    }

    private fun calculateBMI() {
        var result = (weight/ (heightCurrent.toFloat() / 100).pow(2))
        val formatter:DecimalFormat= DecimalFormat("#.##")
        var description:String?
        var descriptionColor:Int?
        when(result){
            in 0.0..18.5-> {
                description=getString(R.string.low_weight)
                descriptionColor=getColor(R.color.low)
            }
            in 18.5..24.9-> {
                description=getString(R.string.normal_weight)
                descriptionColor=getColor(R.color.mid)
            }
            in 24.9..29.9-> {
                description=getString(R.string.over_weight)
                descriptionColor=getColor(R.color.over)
            }
            else -> {
                description=getString(R.string.obesity)
                descriptionColor=getColor(R.color.obesity)
            }
        }
        textView(formatter, description, descriptionColor, result)
    }

    private fun textView(formatter:DecimalFormat, description:String?, descriptionColor:Int,result:Float ) {
        resultTextTv.text=(formatter.format(result))
        descriptionResult.text=(description)
        descriptionResult.setTextColor(descriptionColor)
        resultTextTv.setTextColor(descriptionColor)
    }
}