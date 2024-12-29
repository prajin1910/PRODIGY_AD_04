package com.example.tictactoe
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class Board : AppCompatActivity() {
    private lateinit var vsdisp:TextView
    private var totalmoves=0
    private lateinit var turndisp:TextView
    private var winnerfound=false
    private lateinit var cells:Array<Array<Button>>
    private lateinit var PlayerNames:Array<String>
    private var vals= arrayOf(arrayOf(" "," "," "),arrayOf(" "," "," "),arrayOf(" "," "," "))
    private var currentplayer=0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board_layout)
        supportActionBar?.hide()
        vsdisp=findViewById(R.id.vsdisp)
        turndisp=findViewById(R.id.turndisp)
        PlayerNames= arrayOf(intent.getStringExtra("Name1").toString(),intent.getStringExtra("Name2").toString())
        vsdisp.text="${PlayerNames[0]} VS ${PlayerNames[1]}"
        turndisp.text="${PlayerNames[0]}'s Turn"
        cells= arrayOf(arrayOf(findViewById(R.id.e00),findViewById(R.id.e01),findViewById(R.id.e02)),
            arrayOf(findViewById(R.id.e10),findViewById(R.id.e11),findViewById(R.id.e12)),
            arrayOf(findViewById(R.id.e20),findViewById(R.id.e21),findViewById(R.id.e22)))
        for(i in 0..2){
            for(j in 0..2){
                cells[i][j].setOnClickListener {
                    if(vals[i][j]!=" ") Toast.makeText(this,"This Box Is Already Chosen",Toast.LENGTH_SHORT).show()
                    else{
                        when(currentplayer){
                            0->{
                                vals[i][j]="X"
                                cells[i][j].text="X"
                                totalmoves++
                            }
                            1->{
                                vals[i][j]="O"
                                cells[i][j].text="O"
                                totalmoves++
                            }
                        }
                        checkWinner()
                        if(winnerfound==false) {
                            currentplayer = (currentplayer + 1) % 2;
                            turndisp.text = "${PlayerNames[currentplayer]}'s Turn"
                        }
                        if(totalmoves==9){
                            turndisp.text="Match Drawn"
                            intent=Intent(this,Winning::class.java)
                            intent.putExtra("isdrawn",true)
                            intent.putExtra("n1",PlayerNames[0])
                            intent.putExtra("n2",PlayerNames[1])
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
    private fun checkWinner(){
        var curchecker=""
        if(currentplayer==0) curchecker="X"
        else curchecker="O"
        var counter=0
        var c1=0
        var c2=0
        for(i in 0..2){
            for(j in 0..2){
               if(vals[i][j]==curchecker) counter++
            }
            if(counter==3) winact(PlayerNames[currentplayer])
            counter=0
            for(k in 0..2){
                if(vals[k][i]==curchecker) counter++
            }
            if(counter==3) winact(PlayerNames[currentplayer])
            counter=0
            if(vals[i][i]==curchecker) c1++
            if(vals[i][2-i]==curchecker) c2++
        }
        if(c1==3) winact(PlayerNames[currentplayer])
        if(c2==3) winact(PlayerNames[currentplayer])
    }
    private fun winact(winnername:String){
        winnerfound=true
        val intent=Intent(this,Winning ::class.java)
        intent.putExtra("n1",PlayerNames[0])
        intent.putExtra("val",winnername)
        intent.putExtra("n2",PlayerNames[1])
        turndisp.text="$winnername Won"
        startActivity(intent)
    }
}