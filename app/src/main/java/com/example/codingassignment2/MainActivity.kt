package com.example.codingassignment2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Main activity class extending AppCompatActivity for Android lifecycle management
class MainActivity : AppCompatActivity() {
    // Declare a lateinit variable for the TextView which will display the CSV data
    private lateinit var textView: TextView

    // The onCreate method is called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge UI for immersive experience
        enableEdgeToEdge()

        // Adjust padding to accommodate system bars (status bar, navigation bar)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize TextView and Button
        textView = findViewById(R.id.textView)
        val parseButton: Button = findViewById(R.id.parseButton)

        // Set button click listener to parse CSV and display data
        parseButton.setOnClickListener {
            CSVParserUtil.ParseAndDisplayCSV(assets, textView, "test_files/test_data_3.csv")
        }
    }
}