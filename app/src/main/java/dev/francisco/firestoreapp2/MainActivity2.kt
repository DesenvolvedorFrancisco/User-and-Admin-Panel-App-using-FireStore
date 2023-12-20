package dev.francisco.firestoreapp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dev.francisco.firestoreapp2.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore

        binding.editTextUser.setText(intent.getStringExtra("USER"))
        binding.editTextPassword.setText(intent.getStringExtra("PASSWORD"))

        binding.btnUpdate.setOnClickListener {

            val user = hashMapOf(
                "user" to binding.editTextUser.text.toString(),
                "password" to binding.editTextPassword.text.toString(),
            )
            db.collection("users").document(intent.getStringExtra("ID")!!).set(user)
            finish()
        }
    }
}