package com.example.codingassignment2

import com.example.codingassignment2.Parsers.*;
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
            parseAndDisplayCSV()
        }
    }

    // Function to parse CSV and display in the TextView
    private fun parseAndDisplayCSV() {

        try{
            //val inputStream = assets.open("test_files/test_data_1.csv")
            //val inputStream = assets.open("test_files/test_data_2.csv")
            val inputStream = assets.open("test_files/test_data_3.csv")

            val reader = InputStreamReader(inputStream)

            // Get the appropriate parser for the file extension (CSV in this case)

            //val fileParser = FileParserFactory.getParser("test_files/test_data_1.csv")
            //val fileParser = FileParserFactory.getParser("test_files/test_data_2.csv")
            val fileParser = FileParserFactory.getParser("test_files/test_data_3.csv")

            if (fileParser != null) {
                // Parse the file and get the data as a List of Lists
                val parsedData = fileParser.parse(reader)

                if (parsedData != null) {
                    val result = StringBuilder()

                    val header = parsedData[0].joinToString(", ")
                    result.append(header).append("\n") // Append the header to result
                    result.append("---------------------------------------------------\n") // Add a line after header

                    // Build the string for displaying the remaining data
                    for (index in 1 until parsedData.size) {
                        result.append(parsedData[index].joinToString(", ")).append("\n")
                    }
                    // Build the string for displaying the data
                    //for (row in parsedData) {
                        //result.append(row.joinToString(", ")).append("\n")
                    //}

                    // Display the parsed CSV data in the TextView
                    textView.text = result.toString()

                } else {
                    // Displays Error message for empty or invalid data
                    textView.text = "Error: Invalid or empty CSV file."
                }

            } else {
                // Displays Error if no parser is found
                textView.text = "Error: Could not find an appropriate parser."
            }

            // Handles IOExceptions and displays a message to the user
        } catch (e: IOException) {
            textView.text = "An error occurred while reading the CSV file."
        }


    }
}