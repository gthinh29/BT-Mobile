package com.example.thuchanh1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCheck.setOnClickListener {
            val name = etName.text.toString().trim()
            val ageStr = etAge.text.toString().trim()

            // Sử dụng resource string cho Toast
            if (name.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(this, R.string.please_input_info, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val age = ageStr.toInt()
                val category = when {
                    age < 0 -> getString(R.string.unknown)
                    age > 65 -> getString(R.string.elderly)
                    age in 6..65 -> getString(R.string.adult)
                    age in 2..5 -> getString(R.string.child)
                    else -> getString(R.string.infant)
                }

                // Sử dụng placeholder từ resource string
                tvResult.text = getString(R.string.result_message, name, category)

            } catch (e: NumberFormatException) {
                Toast.makeText(this, R.string.invalid_age, Toast.LENGTH_SHORT).show()
            }
        }
    }
}