package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.male_impact.databinding.ActivityCitaBarber1Binding
import com.example.male_impact.util.PDFHelper

class Cita_barber1 : AppCompatActivity() {

    lateinit var binding: ActivityCitaBarber1Binding
    lateinit var citaDBHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCitaBarber1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        citaDBHelper = SQLiteHelper(this)

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val edad = binding.etEdad.text.toString()
            val correo = binding.etCorreo.text.toString()
            val telefono = binding.etTelefono.text.toString()
            val fecha = binding.etFecha.text.toString()

            if (nombre.isNotBlank() && apellido.isNotBlank() &&
                edad.isNotBlank() && correo.isNotBlank() &&
                telefono.isNotBlank() && fecha.isNotBlank()) {

                citaDBHelper.anadirDatos(nombre, apellido, edad, correo, telefono, fecha)

                // Generar PDF
                PDFHelper.generarReportePDF(this, nombre, apellido, edad, correo, telefono, fecha, "Barbería 1")

                binding.etNombre.text.clear()
                binding.etApellido.text.clear()
                binding.etEdad.text.clear()
                binding.etCorreo.text.clear()
                binding.etTelefono.text.clear()
                binding.etFecha.text.clear()

                Toast.makeText(this, "Cita enviada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Cita no enviada", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAtras.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
