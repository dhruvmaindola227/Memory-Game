package com.example.gameattempt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LeaderBoardActivity : AppCompatActivity() {
    lateinit var tvNameLabel : TextView
    lateinit var tvScoreLabel : TextView
    lateinit var tvDifficultyLabel : TextView
    lateinit var tvName : TextView
    lateinit var tvScore : TextView
    lateinit var tvDifficulty : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var btnBack = findViewById<Button>(R.id.btnBack)
        var highScoreList = mutableListOf(
            Leaderboard("Dhruv" , "6" , "easy"),
            Leaderboard("Mukesh" , "3" , "medium"),
            Leaderboard("Suresh" , "3" , "hard"),
            Leaderboard("Mukesh" , "3" , "medium"),
            Leaderboard("Mukesh" , "3" , "medium"),
            Leaderboard("Mukesh" , "3" , "medium"),
            Leaderboard("Mukesh" , "3" , "medium"),
            Leaderboard("Mukesh" , "3" , "medium")


        )

        val adapter = ScoreAdapter(highScoreList)
        recyclerView.adapter = adapter
        btnBack.setOnClickListener {
            val intent = Intent(this@LeaderBoardActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}