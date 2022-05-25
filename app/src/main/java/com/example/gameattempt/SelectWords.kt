package com.example.gameattempt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class SelectWords : AppCompatActivity() {
    private var numSet = HashSet<Int>()
    private var selectedCount = 0
    private var correctCount = 0
    lateinit var scoreTv : TextView
    lateinit var selectedTv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_words)
        val start = 1
        val end = 24
        scoreTv = findViewById<TextView>(R.id.scoreTv)
        selectedTv = findViewById(R.id.selectedTv)
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
        var level = intent.getIntExtra("value", 0)
        var arrayOfTextViews = arrayListOf<TextView>(tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,
                                                    tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv18,tv19,tv20,
                                                    tv21,tv22,tv23,tv24)



        when(level){

            1 -> {
                for (i in arrayOfTextViews.indices){
                        arrayOfTextViews[i].text = WordArrays.easyArray[rand(0,23)]
                }
                setClickListeners(arrayOfTextViews , level)
            }

            2 ->{
                for (i in arrayOfTextViews.indices){
                    arrayOfTextViews[i].text = WordArrays.mediumArray[rand(0,23)]
                }
                setClickListeners(arrayOfTextViews , level)
            }

            3 -> {
                for (i in arrayOfTextViews.indices){
                    arrayOfTextViews[i].text = WordArrays.hardArray[rand(0,23)]
                }
                setClickListeners(arrayOfTextViews , level)
            }

        }


        btnFinish.setOnClickListener {
            if(selectedCount < 10){
                Toast.makeText(this , "You need to select 10 words." , Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@SelectWords, LeaderBoardActivity::class.java)
                intent.putExtra("level", level)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun setClickListeners(arrayOfTextViews: ArrayList<TextView>, level: Int) {
          for (i in arrayOfTextViews.indices){

              arrayOfTextViews[i].setOnClickListener {
                  if(selectedCount < 10 && correctCount < 11){
                  selectedCount++
                      selectedTv.text = "$selectedCount/10"
                  if(checkAnswer(arrayOfTextViews[i].text.toString() , level)){
                      correctCount++
                      arrayOfTextViews[i].background = ContextCompat.getDrawable(this , R.drawable.correct_border)
                          //loading our custom made animations
                          val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                          //starting the animation
                          arrayOfTextViews[i].startAnimation(animation)
                      scoreTv.text = "$correctCount/10"
                  }else{
                      arrayOfTextViews[i].background = ContextCompat.getDrawable(this , R.drawable.wrong_border)
                      val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                      //starting the animation
                      arrayOfTextViews[i].startAnimation(animation)
                  }
              }
              }
          }
    }

    private fun checkAnswer(textViewString: String, level: Int): Boolean {
        when(level){
            1 -> return WordArrays.easyArray.copyOfRange(0, 9).contains(textViewString)
            2 -> return WordArrays.mediumArray.copyOfRange(0, 9).contains(textViewString)
            3 -> return WordArrays.hardArray.copyOfRange(0, 9).contains(textViewString)
        }
        return false
    }

    //to set different elements in different textviews.
    fun rand(start: Int, end: Int): Int {
        var numberToReturn = (start..end).random()
        if(!numSet.contains(numberToReturn)){
                numSet.add(numberToReturn)
        }else{
            numberToReturn = rand(start,end)
        }
        return numberToReturn
    }

}