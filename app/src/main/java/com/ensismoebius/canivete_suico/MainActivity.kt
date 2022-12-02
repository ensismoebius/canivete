package com.ensismoebius.canivete_suico

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ensismoebius.canivete_suico.R.id.btnMeMandaUmOi
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

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
        val btnMeMandaUmOi: Button = findViewById(btnMeMandaUmOi)

        // binding.btnCalculadora.set...
        btnCalculadora.setOnClickListener {
            abrirCalculadora()
        }

        btnRolaDado.setOnClickListener {
            abrirDado()
        }

        btnMeMandaUmOi.setOnClickListener { it ->
            chat(it)
        }
    }

    private fun chat(view: View) {

        val txtemail: TextView = this.findViewById(R.id.txtLogin)
        val txtsenha: TextView = this.findViewById(R.id.txtSenha)

        val senha = txtsenha.text.toString()
        val login = txtemail.text.toString()

        val autentica = FirebaseAuth.getInstance()

        if (login.isEmpty() || senha.isEmpty()) {
            exibeSnack(view, "Preencha todos o campos", Color.RED)
            return
        }

        var autenticacao =
            autentica.signInWithEmailAndPassword(login, senha)
                .addOnCompleteListener { statusLogin ->
                    if (statusLogin.isSuccessful) {
                        abrirChat()
                    }
                }.addOnFailureListener {
                    exibeSnack(view, "Não foi possivel entrar, tentando cadastrar...", Color.RED)
                    autentica.createUserWithEmailAndPassword(login, senha)
                        .addOnCompleteListener { statusCadastro ->
                            if (statusCadastro.isSuccessful) {
                                exibeSnack(view, "Cadastro feito com sucesso entrando...", Color.GREEN)
                                abrirChat();
                            }
                        }.addOnFailureListener { erroCadastro ->
                        var mensagem = when (erroCadastro) {
                            is FirebaseAuthWeakPasswordException -> "Senha curta"
                            is FirebaseAuthInvalidCredentialsException -> "E-mail inválido"
                            is FirebaseAuthUserCollisionException -> "Já existe o email!"
                            is FirebaseNetworkException -> "Sem net"
                            else -> "Falha desconhecida"
                        }
                        exibeSnack(view, mensagem, Color.RED)
                    }
                }
    }

    private fun exibeSnack(view: View, mensagem: String, color: Int) {
        val aviso = Snackbar.make(view, mensagem, Snackbar.LENGTH_LONG)
        aviso.setBackgroundTint(color)
        aviso.show()
    }

    private fun abrirChat() {
        val intencao = Intent(this, Chat::class.java)
        startActivity(intencao)
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
