package com.example.youinvited

import android.content.Intent
import android.location.GnssNavigationMessage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var progressBar:ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "Iniciar Sesión"
//        this.progressBar = findViewById(R.id.)
        button_register.setOnClickListener { this.btnRegisterAction() }
        button_login.setOnClickListener { this.loginUser() }
    }

    fun loginUser(){
        if (textFieldEmail.text.isNotEmpty() && textFieldPass.text.isNotEmpty()) {
            this.progressBar?.visibility = View.VISIBLE
            FirebaseAuth.getInstance().signInWithEmailAndPassword(textFieldEmail.text.toString(),textFieldPass.text.toString()).addOnCompleteListener {
                this.progressBar?.visibility = View.INVISIBLE
                if (it.isSuccessful){
                    val user = FirebaseAuth.getInstance().currentUser
                    if (user?.isEmailVerified == false){
                        Toast.makeText(this, "Su cuenta no ha sido verificada, favor de revisar su correo eléctronico", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Se inició sesión correctamente", Toast.LENGTH_LONG).show()
                        this.showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }
                } else {
                    Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    fun btnRegisterAction(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun showHome(email: String, provider: ProviderType){
        val intent = Intent(this, PrincipalActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider)
        }
        startActivity(intent)
    }

}