package com.example.gameattempt

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import java.time.Duration
import kotlin.concurrent.fixedRateTimer
import android.widget.Toast
import java.util.*
import android.view.animation.Animation

import android.view.animation.AlphaAnimation

import android.view.View
import org.w3c.dom.Text
import java.lang.Exception
import java.util.concurrent.DelayQueue
import kotlin.concurrent.timerTask


class WordsActivity : AppCompatActivity() {

    var easyArray = arrayOf(
        "Application",
        "Motorola",
        "Package",
        "India",
        "Water",
        "Bottle",
        "Random",
        "Medicine",
        "Six",
        "Games"
    )
    var mediumArray = arrayOf(
        "Bresenham",
        "Scissors",
        "Quinoa",
        "Callous",
        "Independence",
        "Unambiguous",
        "Quiet",
        "Quite",
        "Exaggeration",
        "Introspection"
    )
    var hardArray = arrayOf(
        "Pellucid",
        "Indoctrination",
        "Gratuitous",
        "Egregious",
        "Circumlocution",
        "Equivocate",
        "Curmudgeon",
        "Surreptitious",
        "Puissant",
        "Solicitous"
    )
    private lateinit var tvWord: TextView
    private var level: Int = 0
    private var index = 0
    private var mHandler=Handler()


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
                    if(index!=easyArray.size) {
                        tvWord.text = easyArray[index]
                        index++
                        mHandler.postDelayed(this,1500)
                    }else{
                    val intent = Intent(this@WordsActivity, SelectWords::class.java)
                    startActivity(intent)
                    }
                }

                2-> {
                    if(index!=mediumArray.size) {
                        tvWord.text = mediumArray[index]
                        index++
                        mHandler.postDelayed(this,1000)
                    }else{
                        val intent = Intent(this@WordsActivity, SelectWords::class.java)
                        startActivity(intent)
                    }
                }

                3 -> {
                    if(index!=hardArray.size) {
                        tvWord.text = hardArray[index]
                        index++
                        mHandler.postDelayed(this,500)
                    }else{
                        val intent = Intent(this@WordsActivity, SelectWords::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
        }
    }














