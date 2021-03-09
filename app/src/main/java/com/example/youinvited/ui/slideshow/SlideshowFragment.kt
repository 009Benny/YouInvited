package com.example.youinvited.ui.slideshow

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.youinvited.PrincipalActivity
import com.example.youinvited.R
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_slideshow, container, false)
        val view =  inflater.inflate(R.layout.fragment_slideshow, container, false)

        btnLanguage.setOnClickListener { view ->
            val principal:PrincipalActivity = activity as PrincipalActivity
            principal.hello()
        }
        return view
//        slideshowViewModel =
//            ViewModelProvider(this).get(SlideshowViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
//        btnLanguage.setOnClickListener {
//            Toast.makeText(activity, "Hola", Toast.LENGTH_SHORT).show()
//        }
//        return root
    }

    fun sayHello(){
        Toast.makeText(activity, "Hola", Toast.LENGTH_SHORT).show()
    }

}