package main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import com.example.gameattempt.R
import org.json.JSONException
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.charset.Charset
import java.nio.file.Files.write

class LeaderBoardActivity : AppCompatActivity() {
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
        var highScoreList = ArrayList<Leaderboard>()
        //Reading data from the json file.
        try {
            val obj = JSONObject(
                Utils.getJsonDataFromAsset(
                    applicationContext,
                    "UserScoreData.json"
                )!!
            )
            val scoreArray = obj.getJSONArray("scores")

            for (i in 0 until scoreArray.length()) {
                val user = scoreArray.getJSONObject(i)
                val name = user.getString("name")
                val score = user.getString("score")
                val level = user.getString("level")
                var leaderboard = Leaderboard(name, score, level)
                highScoreList.add(leaderboard)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //before we write to the json we need to check for the highest score.
//        for (i in 0 until highScoreList.size) {
//            if (highScoreList[i].name == name) {
//                if (highScoreList[i].level == level) {
//                    if (highScoreList[i].highScore.toInt() < score) {
//                        highScoreList[i].highScore = score.toString()
//                    }
//                } else {
//                    highScoreList.add(Leaderboard(name, score.toString(), level))
//                }
//            }
//        }
//        highScoreList.add(Leaderboard(name!!, score.toString(), level))
//        val json = JSONObject()
//        try {
//            val obj = JSONObject(
//                Utils.getJsonDataFromAsset(
//                    applicationContext,
//                    "UserScoreData.json"
//                )!!
//            )
//            val scoreArray = obj.getJSONArray("scores")
//
//            scoreArray.put(json.put("name" , name))
//            scoreArray.put(json.put("score" , score))
//            scoreArray.put(json.put("level" , level))
////            for (i in 0 until scoreArray.length()) {
////                val user = scoreArray.getJSONObject(i)
////                user.put("name" , name)
////                user.put("score" , score)
////                user.put("level" , level)
////            }
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }

        val adapter = ScoreAdapter(highScoreList)
        recyclerView.adapter = adapter
        btnBack.setOnClickListener {
            val intent = Intent(this@LeaderBoardActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

}