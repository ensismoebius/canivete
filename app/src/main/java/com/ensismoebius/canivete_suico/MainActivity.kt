package com.ensismoebius.canivete_suico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculadora: Button = findViewById(R.id.btnCalculadora)
        btnCalculadora.setOnClickListener {
            abrirCalculadora()
        }
    }

    private fun abrirCalculadora() {
        val intencao = Intent(this, Calculadora::class.java)
        startActivity(intencao)
    }
}
