package main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.gameattempt.R
import java.util.*
import kotlin.system.exitProcess

class FinalScreenActivity : AppCompatActivity() {
    lateinit var  imageView: ImageView //declaring the imageView in the xml file
    lateinit var tvname : TextView
    lateinit var tvScore : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_screen)
         tvname = findViewById(R.id.tv_name)
         tvScore = findViewById(R.id.tv_score)
        var btnFinish = findViewById<Button>(R.id.btn_finish)
        var btnLeaderboad = findViewById<Button>(R.id.btn_leaderboard)
        val score = intent.getIntExtra("score" , 0)
        val name = intent.getStringExtra("name")
        println("In final screen act name -> $name")
        println("In final screen act name -> $score")
        tvname.text = "${intent.getStringExtra("name")}".uppercase(Locale.getDefault())
        tvScore.text = "You scored ${intent.getIntExtra("score" , 0)}/10"
       imageView=findViewById(R.id.imageView) //binding it to the imageView via its ID.
        /*using the glide library and its methods to load the gif into the imageView*/
        if(score < 4){
            Glide.with(this).load(R.raw.bd9bac239e3e456e229d436bf0dd3a33_w200).into(imageView)
//        This is used to hide the status bar and make
//         the splash screen as a full screen activity.

        }else if(score < 8){
            Glide.with(this).load(R.raw.goodbrain).into(imageView)
        }
        else{
            Glide.with(this).load(R.raw.wowugotgoodmemory).into(imageView)
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    btnLeaderboad.setOnClickListener {
        val intent = Intent(this@FinalScreenActivity, LeaderBoardActivity::class.java)
        startActivity(intent)
    }

        btnFinish.setOnClickListener { android.os.Process.killProcess(android.os.Process.myPid()) }
    }
}