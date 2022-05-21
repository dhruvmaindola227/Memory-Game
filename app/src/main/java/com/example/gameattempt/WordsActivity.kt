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
                    if(index < 10) {
                        tvWord.text = WordArrays.easyArray[index]
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
                        tvWord.text = WordArrays.mediumArray[index]
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
                        tvWord.text = WordArrays.hardArray[index]
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
    }














