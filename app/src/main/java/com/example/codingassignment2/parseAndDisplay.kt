package com.example.codingassignment2

import android.content.res.AssetManager
import android.widget.TextView
import com.example.codingassignment2.Parsers.FileParserFactory
import java.io.IOException
import java.io.InputStreamReader

// Utility object to handle CSV parsing and displaying the result in a TextView.
object CSVParserUtil {

    // Function to parse the CSV file and display its contents in a given TextView.
    fun parseAndDisplayCSV(assets: AssetManager, textView: TextView, filePath: String) {
        try {

            // Open the CSV file from the assets folder using the provided file path.
            val inputStream = assets.open(filePath)
            val reader = InputStreamReader(inputStream)

            // Get the appropriate parser for the file extension (in this case, CSV).
            val fileParser = FileParserFactory.getParser(filePath)

            // Check if a valid parser is found.
            if (fileParser != null) {

                // Parse the CSV file and store the data as a List of Lists (rows of data).
                val parsedData = fileParser.parse(reader)

                // Check if the parsing was successful and the data is not null.
                if (parsedData != null) {
                    val result = StringBuilder()

                    // Get the header row and append it to the result StringBuilder.
                    val header = parsedData[0].joinToString(", ")
                    result.append(header).append("\n")
                    result.append("---------------------------------------------------\n")

                    // Loop through the remaining rows and append them to the result
                    for (index in 1 until parsedData.size) {
                        result.append(parsedData[index].joinToString(", ")).append("\n")
                    }

                    // Display the parsed CSV data in the provided TextView
                    textView.text = result.toString()

                } else {
                    // If parsing fails or the file is empty, display an error message
                    textView.text = "Error: Invalid or empty CSV file."
                }

            } else {
                // If no suitable parser is found, display an error message
                textView.text = "Error: Could not find an appropriate parser."
            }

        } catch (e: IOException) {
            // Handle any IOExceptions that occur while reading the CSV file
            textView.text = "An error occurred while reading the CSV file."
        }
    }
}