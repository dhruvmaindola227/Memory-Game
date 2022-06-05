package main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameattempt.MainActivity
import org.json.JSONObject
import com.example.gameattempt.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.charset.Charset
import java.nio.file.Files.write

class LeaderBoardActivity : AppCompatActivity() {
    private var LIST_KEY = "list_key"
    lateinit var tvNameLabel: TextView
    lateinit var tvScoreLabel: TextView
    lateinit var tvDifficultyLabel: TextView
    lateinit var tvName: TextView
    lateinit var tvScore: TextView
    lateinit var tvDifficulty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var btnBack = findViewById<Button>(R.id.btnBack)
        var difficulty = intent.getIntExtra("level", 0)
        var score = intent.getIntExtra("score", 0)
        var name = intent.getStringExtra("name")
        var level = when (difficulty) {
            1 -> {
                "easy"
            }
            2 -> {
                "medium"
            }
            else -> "hard"
        }

        val list = readListFromPreferences()
        val adapter = ScoreAdapter(list)
        recyclerView.adapter = adapter
        btnBack.setOnClickListener {
            val intent = Intent(this@LeaderBoardActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun readListFromPreferences(): ArrayList<Leaderboard> {
        var sharePref = getSharedPreferences("userScores", Context.MODE_PRIVATE)
        var jsonString = sharePref.getString(LIST_KEY, "")
        var gson = Gson()
        val type = object : TypeToken<List<Leaderboard>>() {}.type
        return gson.fromJson(jsonString, type)
    }


}