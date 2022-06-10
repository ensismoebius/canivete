package com.ensismoebius.canivete_suico

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Calculadora : AppCompatActivity() {

    private val avaliador: EvaluateString = EvaluateString()
    private val txtformula: TextView by lazy { findViewById(R.id.txtFormula) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculadora)

        val zero: Button = findViewById(R.id.btn0)
        zero.setOnClickListener {
            txtformula.text = "${txtformula.text}0"
        }

        val um: Button = findViewById(R.id.btn1)
        um.setOnClickListener {
            txtformula.text = "${txtformula.text}1"
        }

        val dois: Button = findViewById(R.id.btn2)
        dois.setOnClickListener {
            txtformula.text = "${txtformula.text}2"
        }

        val tres: Button = findViewById(R.id.btn3)
        tres.setOnClickListener {
            txtformula.text = "${txtformula.text}3"
        }

        val quatro: Button = findViewById(R.id.btn4)
        quatro.setOnClickListener {
            txtformula.text = "${txtformula.text}4"
        }

        val cinco: Button = findViewById(R.id.btn5)
        cinco.setOnClickListener {
            // txtformula.text = txtformula.text.toString() + "5"
            txtformula.text = "${txtformula.text}5"
        }

        val seis: Button = findViewById(R.id.btn6)
        seis.setOnClickListener {
            txtformula.text = "${txtformula.text}6"
        }

        val sete: Button = findViewById(R.id.btn7)
        sete.setOnClickListener {
            txtformula.text = "${txtformula.text}7"
        }

        val oito: Button = findViewById(R.id.btn8)
        oito.setOnClickListener {
            txtformula.text = "${txtformula.text}8"
        }

        val nove: Button = findViewById(R.id.btn9)
        nove.setOnClickListener {
            txtformula.text = "${txtformula.text}9"
        }

        val limpa: Button = findViewById(R.id.btnCe)
        limpa.setOnClickListener {
            txtformula.text = ""
        }

        val mais: Button = findViewById(R.id.btnSoma)
        mais.setOnClickListener {
            txtformula.text = "${txtformula.text}+"
        }

        val menos: Button = findViewById(R.id.btnSubtracao)
        menos.setOnClickListener {
            txtformula.text = "${txtformula.text}-"
        }


        val mult: Button = findViewById(R.id.btnMultiplicacao)
        mult.setOnClickListener {
            txtformula.text = "${txtformula.text}*"
        }

        val div: Button = findViewById(R.id.btnDivisao)
        div.setOnClickListener {
            txtformula.text = "${txtformula.text}/"
        }

        val abre: Button = findViewById(R.id.btnAbreParenteses)
        abre.setOnClickListener {
            txtformula.text = "${txtformula.text}("
        }

        val fecha: Button = findViewById(R.id.btnFecharParenteses)
        fecha.setOnClickListener {
            txtformula.text = "${txtformula.text})"
        }

        val igual: Button = findViewById(R.id.btnIgual)
        igual.setOnClickListener {
            val expr: String = txtformula.text.toString()
            txtformula.text = avaliador.evaluate(expr)
        }
    }
}
