package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.male_impact.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register_Activity : AppCompatActivity() {

private lateinit var binding:ActivityRegisterBinding
private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityRegisterBinding.inflate(layoutInflater)
            setContentView(binding.root)
            firebaseAuth = FirebaseAuth.getInstance()

            binding.textView.setOnClickListener{
                firebaseAuth.signOut()

                val intent = Intent(this, Login_Activity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{

            val email = binding.emailEt.text.toString()
            val contra = binding.passET.text.toString()
            val confirmcontra= binding.confirmPassEt.text.toString()

            if(email.isNotEmpty() && contra.isNotEmpty() && confirmcontra.isNotEmpty()){
                if(contra == confirmcontra){
                firebaseAuth.createUserWithEmailAndPassword(email,contra).addOnCompleteListener{
                if(it.isSuccessful){
                    firebaseAuth.signOut()
                        val intent = Intent(this, Login_Activity::class.java)
                    startActivity(intent)

                    }else{
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
                }
                }else{
                    Toast.makeText(this, "Contraseña diferente", Toast.LENGTH_SHORT).show()
                }

                }else{
                Toast.makeText(this, "No se permiten campos vacios!!", Toast.LENGTH_SHORT).show()
            }
        }
        }
    }
