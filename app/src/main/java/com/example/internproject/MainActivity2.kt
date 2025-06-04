package com.example.internproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internproject.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up Firebase Storage for another project
        //initializeStorageFirebase()
        //setupTFLiteModel()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.donorCard.setOnClickListener {
            val intent = Intent(this, Donormain::class.java)
            startActivity(intent)
        }

        binding.recipientCard.setOnClickListener {
            val intent = Intent(this, Recipientmain::class.java)
            startActivity(intent)
        }

        binding.hospitalCard.setOnClickListener {
            val intent = Intent(this, Hospitalmain::class.java)
            startActivity(intent)
        }
}
}