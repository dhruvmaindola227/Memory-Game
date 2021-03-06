package main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.gameattempt.MainActivity
import com.example.gameattempt.R

class StartingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)
        var etName = findViewById<EditText>(R.id.et_name)
        var btnStart = findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener {
            if(etName.text.isEmpty())
                Toast.makeText(this,"Please enter your name first",Toast.LENGTH_LONG).show()
            else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name" , etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}