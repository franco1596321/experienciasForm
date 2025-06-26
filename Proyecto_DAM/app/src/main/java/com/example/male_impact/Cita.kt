package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.male_impact.databinding.ActivityCitaBinding
import com.example.male_impact.util.PDFHelper
import com.google.firebase.database.FirebaseDatabase
import java.io.File

class Cita : AppCompatActivity() {
    private lateinit var binding: ActivityCitaBinding
    private lateinit var almacenesDBHelper: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                val archivoPDF: File? = PDFHelper.generarReportePDF(
                    this, nombre, apellido, edad, correo, telefono, motivo, "Cita General")
                subirAFirebaseRealtime(nombre, apellido, edad, correo, telefono, motivo)
                limpiarCampos()
                Toast.makeText(this, "✅ Registro añadido exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No se añadió", Toast.LENGTH_SHORT).show() } }
        binding.btRegresarJ.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish() } }
    private fun limpiarCampos() {
        binding.etNombre.text.clear()
        binding.etApellido.text.clear()
        binding.etEdad.text.clear()
        binding.etCorreo.text.clear()
        binding.etTelefono.text.clear()
        binding.etMotivos.text.clear() }
    private fun subirAFirebaseRealtime(nombre: String, apellido: String, edad: String, correo: String, telefono: String, motivo: String) {
        val db = FirebaseDatabase.getInstance().getReference("citas_generales")
        val datos = mapOf(
            "nombre" to nombre,
            "apellido" to apellido,
            "edad" to edad,
            "correo" to correo,
            "telefono" to telefono,
            "motivo" to motivo
        )
        db.push().setValue(datos).addOnSuccessListener {
            Toast.makeText(this, " ", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "❌ Error al subir cita", Toast.LENGTH_SHORT).show()
        }
    }
}
