package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.male_impact.databinding.ActivityCitaBinding

class Cita : AppCompatActivity() {
    lateinit var binding: ActivityCitaBinding
    lateinit var almacenesDBHelper: SQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding=ActivityCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        almacenesDBHelper=SQLiteHelper(this)

        binding.btGuardar.setOnClickListener{
            if (binding.etNombre.text.isNotBlank() &&
                binding.etApellido.text.isNotBlank() &&
                binding.etEdad.text.isNotBlank() &&
                binding.etCorreo.text.isNotBlank() &&
                binding.etTelefono.text.isNotBlank()){

                almacenesDBHelper.anadirDatosJ(binding.etNombre.text.toString(),

                    binding.etApellido.text.toString(),
                    binding.etEdad.text.toString(),
                    binding.etCorreo.text.toString(),
                    binding.etTelefono.text.toString(),
                    binding.etMotivos.text.toString())


                binding.etNombre.text.clear()
                binding.etApellido.text.clear()
                binding.etEdad.text.clear()
                binding.etCorreo.text.clear()
                binding.etTelefono.text.clear()
                binding.etMotivos.text.clear()
                Toast.makeText(this,"Registro añadido exitosamente", Toast.LENGTH_SHORT).show()

            } else{
                Toast.makeText(this,"No se añadio", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btRegresarJ.setOnClickListener {
            // Crear el Intent para ir a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Cerramos la actividad actual
            finish()
        }




    }
}