package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.male_impact.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthException

class Login_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent) }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            val contra = binding.passET.text.toString().trim()
            if (email.isNotEmpty() && contra.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, contra).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish() } else {
                        val errorMessage = when (val exception = it.exception) {
                            is FirebaseAuthInvalidUserException -> "Datos Incorrectos."
                            is FirebaseAuthInvalidCredentialsException -> "Datos Incorrectos."
                            is FirebaseAuthException -> "Error de autenticación: ${exception.message}"
                            else -> "Error inesperado: ${exception?.localizedMessage}"
                        }
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show() } } } else {
                Toast.makeText(this, "No se permiten campos vacíos", Toast.LENGTH_SHORT).show() } } }
    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() } } }