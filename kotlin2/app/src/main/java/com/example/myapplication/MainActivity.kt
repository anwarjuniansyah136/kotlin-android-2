package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textFullName:TextView
    private lateinit var textAge:TextView
    private lateinit var btnEdit:Button
    private lateinit var btnDelete:Button
    private var sharedPref:SharedPreferences? =  null

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEdit = findViewById(R.id.btn_edit)
        btnDelete = findViewById(R.id.btn_delete)
        textFullName = findViewById(R.id.full_name)
        textAge = findViewById(R.id.age)

        sharedPref = applicationContext?.getSharedPreferences("my-app", Context.MODE_PRIVATE)

        btnEdit.setOnClickListener{
            val intent = Intent(applicationContext,EditorActivity::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener{
            val editor = sharedPref?.edit()
            editor?.remove("full_name")
            editor?.remove("age")
            editor?.apply()

            onResume()
        }
    }

    override fun onResume() {
        super.onResume()

        val fullName = sharedPref?.getString("full_name","-")
        val age = sharedPref?.getInt("age",0)

        textFullName.text = fullName
        textAge.text = age.toString()
    }
}