package com.ensismoebius.canivete_suico

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ensismoebius.canivete_suico.R.id.btnMeMandaUmOi
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

//import com.ensismoebius.canivete_suico.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //ativa o viewbind
//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val btnCalculadora: ImageButton = findViewById(R.id.btnCalculadora)
        val btnRolaDado: ImageButton = findViewById(R.id.btnDado)
        val btnMeMandaUmOi: ImageButton = findViewById(btnMeMandaUmOi)

        // binding.btnCalculadora.set...
        btnCalculadora.setOnClickListener {
            abrirCalculadora()
        }

        btnRolaDado.setOnClickListener {
            abrirDado()
        }

        btnMeMandaUmOi.setOnClickListener { it ->

            val txtemail: TextView = this.findViewById(R.id.txtLogin)
            val txtsenha: TextView = this.findViewById(R.id.txtSenha)

            val senha = txtsenha.text.toString()
            val login = txtemail.text.toString()

            if (login.isEmpty() || senha.isEmpty()){
                val aviso = Snackbar.make(it, "Preencha todos o campos", Snackbar.LENGTH_LONG) //it se refere ao próprio objeto atual, algo como this
                aviso.setBackgroundTint(Color.RED)
                aviso.show()
            }else{
                val autentica = FirebaseAuth.getInstance()
                autentica.createUserWithEmailAndPassword(login,senha).addOnCompleteListener{ cadastro ->
                    if(cadastro.isSuccessful){
                        val aviso2 = Snackbar.make(it, "Cadastrado com sucesso!", Snackbar.LENGTH_LONG)
                        aviso2.setBackgroundTint(Color.GREEN)
                        aviso2.show()
                    }
                }.addOnFailureListener { erro ->
                    val aviso3 = Snackbar.make(it, erro.message.toString(), Snackbar.LENGTH_LONG)
                    aviso3.setBackgroundTint(Color.RED)
                    aviso3.show()
                }
            }



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
