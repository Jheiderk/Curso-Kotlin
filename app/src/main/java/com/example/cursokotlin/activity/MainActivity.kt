package com.example.cursokotlin.activity


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.TextureView
import android.widget.Button
import android.widget.TextView
import com.example.cursokotlin.R
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
    private var countDownTimer: CountDownTimer? = null
    private lateinit var WeightText:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        view()
        heightCm()
        weightKg()
        touch()
        press()


    }

    @SuppressLint("ClickableViewAccessibility")
    private fun press() {
        plus.setOnTouchListener { _, press ->
            when(press.action) {
                MotionEvent.ACTION_DOWN -> startTimer(true)
                MotionEvent.ACTION_UP -> stopTimer()

            }
            true
        }
        rest.setOnTouchListener { _, press ->
            when(press.action){
                MotionEvent.ACTION_DOWN -> startTimer(false)
                MotionEvent.ACTION_UP -> stopTimer()
            }
            true
        }
    }private fun startTimer(increment: Boolean) {
        countDownTimer=object: CountDownTimer(Long.MAX_VALUE, 150){
            override fun onTick(millisUntilFinished: Long) {
                if(increment) weight++
                else weight--

                weightKg()
            }
            override fun onFinish() {}
        }
        countDownTimer?.start()
    }

    private fun view() {
        heightTv = findViewById(R.id.texto2)
        rest = findViewById(R.id.restFab)
        plus = findViewById(R.id.plusFab)
        slider = findViewById(R.id.slider)
        btnImc = findViewById(R.id.button)
        resultTextTv= findViewById(R.id.resultTextTv)
        descriptionResult=findViewById(R.id.descriptionResult)
        WeightText= findViewById(R.id.texto3)
    }
    private fun stopTimer()  {
        countDownTimer?.cancel()

    }
    private fun heightCm() {
        heightTv.text = " $heightCurrent cm"
    }

    private fun weightKg() {
        WeightText.text = "$weight Kg"
    }

    private fun calculateBMI() {
        val result = (weight/ (heightCurrent.toFloat() / 100).pow(2))
        val formatter:DecimalFormat= DecimalFormat("#.##")
        val description:String?
        val descriptionColor:Int?
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
    private fun touch() {
        slider.addOnChangeListener { _, value, _ ->
            heightCurrent = value.toInt()
            heightCm()
        }

        btnImc.setOnClickListener {
            calculateBMI()
        }
    }
}