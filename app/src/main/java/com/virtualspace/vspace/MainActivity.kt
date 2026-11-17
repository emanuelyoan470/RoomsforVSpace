package com.virtualspace.vspace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.view.Gravity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // En lugar de cargar un XML, creamos un texto directo en el código
        val textView = TextView(this)
        textView.text = "¡VSpace Funciona!"
        textView.textSize = 30f
        textView.gravity = Gravity.CENTER
        
        setContentView(textView)
    }
}
