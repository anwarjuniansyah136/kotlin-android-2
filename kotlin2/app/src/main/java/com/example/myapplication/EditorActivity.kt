package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editor)

        val editFullName:EditText = findViewById(R.id.full_name)
        val editAge:EditText = findViewById(R.id.age)
        val btnSave:Button = findViewById(R.id.btn_save)

        val sharedPref = applicationContext?.getSharedPreferences("my-app", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

//        save
        btnSave.setOnClickListener{
            if(editFullName.length() > 0 && editAge.length() > 0){
                editor?.putString("full_name",editFullName.text.toString())
                editor?.putInt("age",editAge.text.toString().toInt())
                editor?.apply()

                finish()
            }
            else{
                Toast.makeText(applicationContext,"Silahkan isi semua data",Toast.LENGTH_SHORT).show()
            }
        }
    }
}