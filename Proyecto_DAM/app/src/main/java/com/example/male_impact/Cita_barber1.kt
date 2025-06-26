package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.male_impact.databinding.ActivityCitaBarber1Binding
import com.example.male_impact.util.PDFHelper
import com.google.firebase.database.FirebaseDatabase
import java.io.File

class Cita_barber1 : AppCompatActivity() {
    private lateinit var binding: ActivityCitaBarber1Binding
    private lateinit var citaDBHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitaBarber1Binding.inflate(layoutInflater)
        setContentView(binding.root)

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

                val archivoPDF = PDFHelper.generarReportePDF(
                    this, nombre, apellido, edad, correo, telefono, fecha, "cliente"
                )

                subirAFirebaseRealtime(nombre, apellido, edad, correo, telefono, fecha)

                limpiarCampos()
                Toast.makeText(this, "❌ Cita enviada exitosamente. ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Cita no enviada", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAtras.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun limpiarCampos() {
        binding.etNombre.text.clear()
        binding.etApellido.text.clear()
        binding.etEdad.text.clear()
        binding.etCorreo.text.clear()
        binding.etTelefono.text.clear()
        binding.etFecha.text.clear()
    }

    private fun subirAFirebaseRealtime(nombre: String, apellido: String, edad: String, correo: String, telefono: String, fecha: String) {
        val db = FirebaseDatabase.getInstance().getReference("citas_barberia1")
        val datos = mapOf(
            "nombre" to nombre,
            "apellido" to apellido,
            "edad" to edad,
            "correo" to correo,
            "telefono" to telefono,
            "fecha" to fecha
        )
        db.push().setValue(datos).addOnSuccessListener {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "❌ Error al subir cita", Toast.LENGTH_SHORT).show()
        }
    }
}
