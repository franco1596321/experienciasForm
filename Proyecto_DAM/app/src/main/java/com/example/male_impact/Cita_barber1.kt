package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.male_impact.databinding.ActivityCitaBarber1Binding

class Cita_barber1 : AppCompatActivity() {

    lateinit var binding : ActivityCitaBarber1Binding
    lateinit var citaDBHelper: SQLiteHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cita_barber1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityCitaBarber1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        citaDBHelper = SQLiteHelper(this)

        binding.btnGuardar.setOnClickListener{
            if (binding.etNombre.text.isNotBlank() && binding.etApellido.text.isNotBlank()
                && binding.etEdad.text.isNotBlank() && binding.etCorreo.text.isNotBlank()
                && binding.etTelefono.text.isNotBlank() && binding.etFecha.text.isNotBlank()){
                citaDBHelper.anadirDatos(
                    binding.etNombre.text.toString(),
                    binding.etApellido.text.toString(),
                    binding.etEdad.text.toString(),
                    binding.etCorreo.text.toString(),
                    binding.etTelefono.text.toString(),
                    binding.etFecha.text.toString()
                )
                binding.etNombre.text.clear()
                binding.etApellido.text.clear()
                binding.etEdad.text.clear()
                binding.etCorreo.text.clear()
                binding.etTelefono.text.clear()
                binding.etFecha.text.clear()
                Toast.makeText(this, "Cita enviada exitosamente", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "cita no enviada", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnAtras.setOnClickListener {
            // Crear el Intent para ir a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Cerramos la actividad actual
            finish()
        }

    }
}