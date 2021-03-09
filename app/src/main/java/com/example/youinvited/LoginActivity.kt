package com.example.youinvited

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
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
import java.util.*

class LoginActivity : AppCompatActivity() {
    var progressBar:ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.loadLocate()
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

    fun setLocale(language:String){
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings", Activity.MODE_PRIVATE).edit()
        editor.putString("My_Lang", language)
        editor.apply()
    }

    fun loadLocate(){
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        var language: String? = sharedPreferences.getString("My_Lang", "")
        if (language != null){
            setLocale(language!!)
        }
    }

}