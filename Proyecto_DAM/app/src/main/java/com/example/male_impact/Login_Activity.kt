package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.male_impact.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login_Activity : AppCompatActivity() {

private lateinit var binding:ActivityLoginBinding
private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.textView.setOnClickListener{
            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val contra = binding.passET.text.toString()
            if(email.isNotEmpty() && contra.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email,contra).addOnCompleteListener{
                        if(it.isSuccessful){
                            firebaseAuth.signOut()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "No se permiten campos vacios", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onStart(){
        super.onStart()
        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}