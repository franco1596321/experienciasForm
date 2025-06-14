package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ver_mas_barber : AppCompatActivity() {

    private lateinit var btnReservarBarber1 : Button
    private lateinit var btnInicio : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_mas_barber)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnReservarBarber1 = findViewById(R.id.btnReservarBarber1)
        btnInicio = findViewById(R.id.btnInicio)

        btnReservarBarber1.setOnClickListener{
            val intent = Intent(this,Cita_barber1::class.java)
            startActivity(intent)
        }

        btnInicio.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }



    }
}