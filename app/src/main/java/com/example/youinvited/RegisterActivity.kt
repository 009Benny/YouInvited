package com.example.youinvited

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.textFieldEmail
import kotlinx.android.synthetic.main.activity_login.textFieldPass
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        title = "Registrarse"
        btn_register.setOnClickListener { this.registerUser() }
    }

    fun registerUser(){
        if (textFieldEmail.text.isNotEmpty() && textFieldPass.text.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(textFieldEmail.text.toString(),textFieldPass.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                    Toast.makeText(this, "Se envio un email de verificación a tu correo", Toast.LENGTH_LONG)
                    this.showHome()
                }else{
                    this.showAlert()
                }
            }
        }
    }

    fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al registrar usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }

    fun showHome(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}