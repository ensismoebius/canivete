package com.ensismoebius.canivete_suico

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculadora: ImageButton = findViewById(R.id.btnCalculadora)
        val btnRolaDado: ImageButton = findViewById(R.id.btnDado)

        btnCalculadora.setOnClickListener {
            abrirCalculadora()
        }

        btnRolaDado.setOnClickListener {
            abrirDado()
        }
    }

    private fun abrirCalculadora() {
        val intencao = Intent(this, Calculadora::class.java)
        startActivity(intencao)
    }

    private fun abrirDado() {
        val intencao = Intent(this, RolaDado::class.java)
        startActivity(intencao)
    }
}
