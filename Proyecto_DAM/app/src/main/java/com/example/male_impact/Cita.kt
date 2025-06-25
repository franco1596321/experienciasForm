package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.male_impact.databinding.ActivityCitaBinding
import com.example.male_impact.util.PDFHelper

class Cita : AppCompatActivity() {
    lateinit var binding: ActivityCitaBinding
    lateinit var almacenesDBHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        almacenesDBHelper = SQLiteHelper(this)

        binding.btGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val edad = binding.etEdad.text.toString()
            val correo = binding.etCorreo.text.toString()
            val telefono = binding.etTelefono.text.toString()
            val motivo = binding.etMotivos.text.toString()

            if (nombre.isNotBlank() && apellido.isNotBlank() &&
                edad.isNotBlank() && correo.isNotBlank() && telefono.isNotBlank()) {

                almacenesDBHelper.anadirDatosJ(nombre, apellido, edad, correo, telefono, motivo)

                // Generar PDF
                PDFHelper.generarReportePDF(this, nombre, apellido, edad, correo, telefono, motivo, "Cita General")

                binding.etNombre.text.clear()
                binding.etApellido.text.clear()
                binding.etEdad.text.clear()
                binding.etCorreo.text.clear()
                binding.etTelefono.text.clear()
                binding.etMotivos.text.clear()

                Toast.makeText(this, "Registro añadido exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No se añadió", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btRegresarJ.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
