package com.example.gameattempt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException

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
        var highScoreList = ArrayList<Leaderboard>()

        try {
            val obj = JSONObject(Utils.getJsonDataFromAsset(applicationContext , "UserScoreData.json")!!)
            val scoreArray = obj.getJSONArray("scores")

            for(i in 0 until scoreArray.length()){
                val user = scoreArray.getJSONObject(i)
                val name = user.getString("name")
                val score = user.getString("score")
                val level = user.getString("level")
                var leaderboard = Leaderboard(name , score , level)
                highScoreList.add(leaderboard)
            }
        } catch (e : JSONException){
            e.printStackTrace()
        }
        val adapter = ScoreAdapter(highScoreList)
        recyclerView.adapter = adapter
        btnBack.setOnClickListener {
            val intent = Intent(this@LeaderBoardActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}