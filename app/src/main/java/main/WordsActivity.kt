package main

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import java.util.*

import com.example.gameattempt.R


class WordsActivity : AppCompatActivity() {
    private lateinit var tvWord: TextView
    private var level: Int = 0
    private var index = 0
    private var mHandler=Handler()
    private var numSet = HashSet<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)
        tvWord = findViewById<TextView>(R.id.tvWord)
        level = intent.getIntExtra("value", 0)
        printArrays()
    }

    private fun printArrays(){
        mrunnable.run()
    }

    val mrunnable:Runnable = object : Runnable {
        override fun run() {
            when(level)
            {
                1 -> {
                    if(index < 10) {
                        tvWord.text = WordArrays.easyArray[rand(0,9)]
                        index++
                        mHandler.postDelayed(this,1200)
                        //"this" keyword is being used for recursion , calling the run() method.
                    }else{
                    val intent = Intent(this@WordsActivity, SelectWords::class.java)
                    intent.putExtra("value",1)
                    startActivity(intent)
                    }
                }

                2-> {
                    if(index < 10) {
                        tvWord.text = WordArrays.mediumArray[rand(0,9)]
                        index++
                        mHandler.postDelayed(this,1000)
                    }else{
                        val intent = Intent(this@WordsActivity, SelectWords::class.java)
                        intent.putExtra("value",2)
                        startActivity(intent)
                    }
                }

                3 -> {
                    if(index < 10) {
                        tvWord.text = WordArrays.hardArray[rand(0,9)]
                        index++
                        mHandler.postDelayed(this,500)
                    }else{
                        val intent = Intent(this@WordsActivity, SelectWords::class.java)
                        intent.putExtra("value",3)
                        startActivity(intent)
                    }
                }
            }
        }
        }

    //to set different elements in different textviews.
    fun rand(start: Int, end: Int): Int {
        var numberToReturn = (start..end).random()
        if(!numSet.contains(numberToReturn)){
            numSet.add(numberToReturn)
        }else{
            numberToReturn = rand(start,end)
        //recursively calling the function until it returns a unique number in the given range
        }
        return numberToReturn
    }
    }














