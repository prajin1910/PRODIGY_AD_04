package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var name1=""
    var name2=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val button=findViewById<Button>(R.id.playbtn)
        button.setOnClickListener {
            val n1=findViewById<EditText>(R.id.name1)
            val n2=findViewById<EditText>(R.id.name2)
            name1=n1.text.toString()
            name2=n2.text.toString()
            if(name1.isEmpty()){
                Toast.makeText(this,"Please Enter The Player 1 Name",Toast.LENGTH_SHORT).show()
            }else if(name2.isEmpty()){
                Toast.makeText(this,"Please Enter The Player 2 Name",Toast.LENGTH_SHORT).show()
            }else{
                val intent=Intent(this,Board::class.java)
                intent.putExtra("Name1",name1)
                intent.putExtra("Name2",name2)
                startActivity(intent)
            }
        }

    }
}