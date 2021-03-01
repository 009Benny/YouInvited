package com.example.youinvited

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "Iniciar Sesión"
        button_register.setOnClickListener { this.btnRegisterAction() }
        button_login.setOnClickListener { this.loginUser() }
    }

    fun loginUser(){
        if (textFieldEmail.text.isNotEmpty() && textFieldPass.text.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(textFieldEmail.text.toString(),textFieldPass.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    this.showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                }else{
                    this.showAlert()
                }
            }
        }
    }
    fun btnRegisterAction(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al registrar usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showHome(email: String, provider: ProviderType){
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider)
        }
        startActivity(intent)
    }

}