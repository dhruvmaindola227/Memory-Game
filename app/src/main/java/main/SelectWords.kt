package main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameattempt.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class SelectWords : AppCompatActivity() {
    private var LIST_KEY = "list_key"
    private var numSet = HashSet<Int>()
    private var selectedCount = 0
    private var correctCount = 0
    lateinit var scoreTv: TextView
    lateinit var selectedTv: TextView
    lateinit var userName : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_words)
        val start = 1
        val end = 24
        scoreTv = findViewById<TextView>(R.id.scoreTv)
        selectedTv = findViewById(R.id.selectedTv)
        userName = intent.getStringExtra("name").toString()
        var btnFinish = findViewById<Button>(R.id.btnFinish)
        var tv1 = findViewById<TextView>(R.id.tv1)
        var tv2 = findViewById<TextView>(R.id.tv2)
        var tv3 = findViewById<TextView>(R.id.tv3)
        var tv4 = findViewById<TextView>(R.id.tv4)
        var tv5 = findViewById<TextView>(R.id.tv5)
        var tv6 = findViewById<TextView>(R.id.tv6)
        var tv7 = findViewById<TextView>(R.id.tv7)
        var tv8 = findViewById<TextView>(R.id.tv8)
        var tv9 = findViewById<TextView>(R.id.tv9)
        var tv10 = findViewById<TextView>(R.id.tv10)
        var tv11 = findViewById<TextView>(R.id.tv11)
        var tv12 = findViewById<TextView>(R.id.tv12)
        var tv13 = findViewById<TextView>(R.id.tv13)
        var tv14 = findViewById<TextView>(R.id.tv14)
        var tv15 = findViewById<TextView>(R.id.tv15)
        var tv16 = findViewById<TextView>(R.id.tv16)
        var tv17 = findViewById<TextView>(R.id.tv17)
        var tv18 = findViewById<TextView>(R.id.tv18)
        var tv19 = findViewById<TextView>(R.id.tv19)
        var tv20 = findViewById<TextView>(R.id.tv20)
        var tv21 = findViewById<TextView>(R.id.tv21)
        var tv22 = findViewById<TextView>(R.id.tv22)
        var tv23 = findViewById<TextView>(R.id.tv23)
        var tv24 = findViewById<TextView>(R.id.tv24)
        var leveldiff = intent.getIntExtra("value" , 0)
        var level = when (leveldiff) {
            1 -> {
                "easy"
            }
            2 -> {
                "medium"
            }
            else -> "hard"
        }
        println("in selectWords name ->" + userName)
        println("in selectWords lveldiff ->" + leveldiff)

        var arrayOfTextViews = arrayListOf<TextView>(
            tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10,
            tv11, tv12, tv13, tv14, tv15, tv16, tv17, tv18, tv19, tv20,
            tv21, tv22, tv23, tv24
        )

        when (leveldiff) {

            1 -> {
                for (i in arrayOfTextViews.indices) {
                    arrayOfTextViews[i].text = WordArrays.easyArray[rand(0, 23)]
                }
                setClickListeners(arrayOfTextViews, leveldiff)
            }

            2 -> {
                for (i in arrayOfTextViews.indices) {
                    arrayOfTextViews[i].text = WordArrays.mediumArray[rand(0, 23)]
                }
                setClickListeners(arrayOfTextViews, leveldiff)
            }

            3 -> {
                for (i in arrayOfTextViews.indices) {
                    arrayOfTextViews[i].text = WordArrays.hardArray[rand(0, 23)]
                }
                setClickListeners(arrayOfTextViews, leveldiff)
            }

        }

        var newUser = true
        var list = ArrayList<Leaderboard>()
//        writeListToPreferences(list)
        btnFinish.setOnClickListener {
            if (selectedCount < 10) {
                Toast.makeText(this, "You need to select 10 words.", Toast.LENGTH_SHORT).show()
            } else {
                var leaderboard = Leaderboard(userName.toString() , correctCount.toString() , level)
                list = readListFromPreferences()
                if(list.isEmpty()){
                    list.add(leaderboard)
//                    writeListToPreferences(list)
                //if list in spfs is empty then add new leaderboard element
                }else{ //otherwise compare previous values and then add.
                    for (i in 0 until list.size){
                        newUser = true
                        if(list[i].name == userName){
                            if(list[i].level == level){
                                if(list[i].highScore.toInt() < correctCount){
                                    list[i].highScore = correctCount.toString()
                                    newUser = false
                                    break
                                }
                            }else{
                                list.add(leaderboard)
                                newUser = false
                                break
                            }
                        }
                    }
                    if(newUser){
                        list.add(leaderboard)
                    }
                }
                writeListToPreferences(list)
                val intent = Intent(this@SelectWords, FinalScreenActivity::class.java)
                intent.putExtra("score" , correctCount)
                intent.putExtra("name" , userName)
                startActivity(intent)
            }

        }
            }

    fun setClickListeners(arrayOfTextViews: ArrayList<TextView>, level: Int) {
        var wasClicked = false
        for (i in arrayOfTextViews.indices) {
            arrayOfTextViews[i].setOnClickListener {
                wasClicked = true
                if (selectedCount < 10 && correctCount < 11) {
                    selectedCount++
                    selectedTv.text = "$selectedCount/10"
                    if (checkAnswer(arrayOfTextViews[i].text.toString(), level)) {
                        correctCount++
                        arrayOfTextViews[i].background = ContextCompat.getDrawable(
                            this,
                            R.drawable.correct_border
                        )
                        //loading our custom made animations
                        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                        //starting the animation
                        arrayOfTextViews[i].startAnimation(animation)
                        scoreTv.text = "$correctCount/10"
                    } else {
                        arrayOfTextViews[i].background = ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_border
                        )
                        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                        //starting the animation
                        arrayOfTextViews[i].startAnimation(animation)
                    }
                }
                arrayOfTextViews[i].isEnabled = false
            }
        }
    }


    private fun checkAnswer(textViewString: String, level: Int): Boolean {
        when (level) {
            1 -> return WordArrays.easyArray.copyOfRange(0, 10)
                .contains(textViewString) //10 because the end parameter is exclusive
            2 -> return WordArrays.mediumArray.copyOfRange(0, 10)
                .contains(textViewString)  //and we want 0-9 index.
            3 -> return WordArrays.hardArray.copyOfRange(0, 10).contains(textViewString)
        }
        return false
    }

    //to set different elements in different textviews.
    fun rand(start: Int, end: Int): Int {
        var numberToReturn = (start..end).random()
        if (!numSet.contains(numberToReturn)) {
            numSet.add(numberToReturn)
        } else {
            numberToReturn = rand(start, end)
        }
        return numberToReturn
    }


    @SuppressLint("CommitPrefEdits")
    fun writeListToPreferences(list: ArrayList<Leaderboard>) {
        var gson = Gson()
        var jsonString: String = gson.toJson(list)
        var sharePref = getSharedPreferences("userScores", Context.MODE_PRIVATE)
        val editor = sharePref.edit()
        editor.putString(LIST_KEY, jsonString)
        editor.apply()
    }


    fun readListFromPreferences(): ArrayList<Leaderboard> {
        var sharePref = getSharedPreferences("userScores", Context.MODE_PRIVATE)
        var jsonString = sharePref.getString(LIST_KEY, "")
        var gson = Gson()
        val type = object : TypeToken<List<Leaderboard>>() {}.type
        return gson.fromJson(jsonString, type)
    }

}
