package com.ensismoebius.canivete_suico

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Chat : AppCompatActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        val txtMensagem = findViewById<TextView>(R.id.txtMensagem)
        val txtMensagens = findViewById<TextView>(R.id.editTextTextMultiLine2)

        btnEnviar.setOnClickListener {
            val data = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            val formatted = data.format(formatter)

            val msg = hashMapOf(
                "mensagem" to txtMensagem.text.toString(), "data" to formatted,
            )

            db.collection("chat").add(msg).addOnSuccessListener { docRef ->
                Log.d("teste", "DocumentSnapshot added with ID: ${docRef.id}")
            }.addOnFailureListener { error ->
                Log.d("teste", "DocumentSnapshot added with ID: ${error.message}")
            }


            db.collection("chat").orderBy("data", Query.Direction.DESCENDING).limit(1).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        txtMensagens.text =
                            txtMensagens.text.toString() + document.data["mensagem"].toString() + "\n"
                    }
                }.addOnFailureListener { exception ->
                    Log.w("teste", "Error getting documents.", exception)
                }

            txtMensagens.selectionStart.rangeTo(txtMensagens.lineCount)
            txtMensagens.selectionEnd.rangeTo(txtMensagens.lineCount)
        }

        db.collection("chat").get().addOnSuccessListener { result ->
            for (document in result) {
                txtMensagens.text =
                    txtMensagens.text.toString() + document.data["mensagem"].toString() + "\n"
            }
        }.addOnFailureListener { exception ->
            Log.w("teste", "Error getting documents.", exception)
        }

        txtMensagens.selectionStart.rangeTo(txtMensagens.lineCount)
        txtMensagens.selectionEnd.rangeTo(txtMensagens.lineCount)

    }
}