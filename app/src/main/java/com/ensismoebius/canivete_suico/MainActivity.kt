package com.ensismoebius.canivete_suico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun abrirCalculadora() {
        val intencao = Intent(this, Calculadora::class.java)
        startActivity(intencao)
    }
}
