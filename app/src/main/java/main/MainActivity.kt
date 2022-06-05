package com.example.gameattempt


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    lateinit var btnEasy:Button
    lateinit var btnMedium:Button
    lateinit var btnHard:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnEasy=findViewById(R.id.btnEasy)
        btnMedium=findViewById(R.id.btnMedium)
        btnHard=findViewById(R.id.btnHard)
        var bundle = Bundle()
        var name = intent?.getStringExtra("name")
        println("In main activity ->" + name)
        btnEasy.setOnClickListener {
            val intent = Intent(this, WordsActivity::class.java)
            intent.putExtra("value" , 1)
            intent.putExtra("name" , name)
            startActivity(intent)

        }
        btnMedium.setOnClickListener {
            val intent = Intent(this, WordsActivity::class.java)
            intent.putExtra("value" , 2)
            intent.putExtra("name" , name)
            startActivity(intent)

        }
        btnHard.setOnClickListener {

            val intent = Intent(this, WordsActivity::class.java)
            intent.putExtra("value" , 3)
            intent.putExtra("name" , name)
            startActivity(intent)
        }
    }

}