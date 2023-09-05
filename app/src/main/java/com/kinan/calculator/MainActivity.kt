package com.kinan.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.kinan.calculator.databinding.ActivityMainBinding

typealias BinaryOperator = (Double, Double) -> Double

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var current: TextView
    private var changed = false
    public lateinit var operation: BinaryOperator
    var number1 : Double = 0.0
    var number2 : Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        current = binding.result

    }

    fun input(view: View) {
        with(binding){
            when ((view as AppCompatButton).text){
                "+" -> {
                    operation = {a, b -> (a + b).toDouble()}
                    current.text = current.text.toString() + view.text.toString()
                }


                "-" -> {
                    operation = {a, b -> (a - b).toDouble()}
                    current.text = current.text.toString() + view.text.toString()
                }


                "/" -> {
                    operation = {a, b -> (a / b).toDouble()}
                    current.text = current.text.toString() + view.text.toString()
                }


                "X" -> {
                    operation  = {a, b -> (a * b).toDouble()}
                    current.text = current.text.toString() + view.text.toString()
                }

                "C" -> {
                    current.text = ""
                }

                "" -> {
                    current.text = current.text.dropLast(1)
                }

                "^" -> {
                    operation = {a, b -> (Math.pow(a, b).toDouble())}
                    current.text = current.text.toString() + view.text.toString()
                }

                "=" -> {
                    val temp = result.text.split("+", "X", "-", "/", "^")
                    number1 = temp[0].toDouble()
                    number2 = temp[1].toDouble()
                    val equals = operation(number1, number2)
                    result.text = equals.toString()
                }

                else -> {
                    if(!changed){
                        current.text = ""
                        changed = true
                    }
                    current.text = current.text.toString() + view.text.toString()
                }
            }
        }
    }
}