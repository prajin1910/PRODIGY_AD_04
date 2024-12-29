package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Winning:AppCompatActivity() {
    private lateinit var winnername:String
    private lateinit var windisp:TextView
    private var name1=""
    private var name2=" "
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.winning_acivity)
        supportActionBar?.hide()
        windisp=findViewById(R.id.winnerdisp)
        winnername=intent.getStringExtra("val").toString()
        if(intent.getBooleanExtra("isdrawn",false)){
            windisp.text="Match Drawn"
        }else{
        windisp.text="$winnername Had Won The Game"}
        name1=intent.getStringExtra("n1").toString()
        name2=intent.getStringExtra("n2").toString()
        var replay=findViewById<Button>(R.id.replaybtn)
        replay.setOnClickListener {
            val intent=Intent(this,Board:: class.java)
            intent.putExtra("Name1",name1)
            intent.putExtra("Name2",name2)
            startActivity(intent)
        }
        var home=findViewById<Button>(R.id.homebtn)
        home.setOnClickListener {
            intent=Intent(this,MainActivity:: class.java)
            startActivity(intent)
        }
    }
}