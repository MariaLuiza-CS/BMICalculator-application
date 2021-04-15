package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.NumberFormatException
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        try {
            button_calculate.setOnClickListener {
                text_result.isVisible = true
                if (edit_height.text.toString() != "" && edit_weight.text.toString() != "") {
                    calculateIMC(edit_weight!!.text.toString(), edit_height!!.text.toString())
                } else {
                    text_result.text = "Erro\n \nvocê precisa\n inserir algum valor"
                }
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Erro, insira os valores novamente", Toast.LENGTH_SHORT).show()
        }

    }

    private fun calculateIMC(weight: String, height: String) {
        var weight = weight.toFloat()
        var height = height.toFloat()
        var totalIMC: Float

        try {

            if (weight < 0 || height < 0) {
                totalIMC = -1f
                tableIMC(totalIMC)
            } else if (weight == 0f || height == 0f) {
                totalIMC = 0f
                tableIMC(totalIMC)
            } else if (weight == " ".toFloat() || height == " ".toFloat()) {
                totalIMC = -2f
                tableIMC(totalIMC)
            } else if (weight != null && height != null) {
                totalIMC = weight / height.pow(2)
                tableIMC(totalIMC)
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Erro, insira os valores novamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun tableIMC(totalIMC: Float) {
        when (totalIMC) {
            in 1f..18.4f -> {
                text_result.text = "IMC: %.2f\n \nResultado:\n Abaixo do peso".format(totalIMC)
            }
            in 18.5f..24.9f -> {
                text_result.text = "IMC: %.2f\n \nResultado:\n Peso normal".format(totalIMC)
            }
            in 25f..29.9f -> {
                text_result.text = "IMC: %.2f\n \nResultado:\n Sobrepeso".format(totalIMC)
            }
            0f -> {
                text_result.text = "Erro\n \no valor não\n pode ser nulo"
            }
            -1f -> {
                text_result.text = "Erro\n \no valor não\n pode ser negativo"
            }
            -2f -> {
                text_result.text = "Erro\n \nvocê precisa\n inserir algum valor"
            }
            else -> {
                text_result.text = "IMC: %.2f\n \nResultado:\n Obesidade".format(totalIMC)
            }
        }

    }
}