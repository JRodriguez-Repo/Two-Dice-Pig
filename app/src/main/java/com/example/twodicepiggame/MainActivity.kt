package com.example.twodicepiggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private var player1Pts: Int = 0
    private var player2Pts: Int = 0
    private var gamePoint: Int = 50
    private var announcement1: String = "Player 1 WINS!!!"
    private var announcement2: String = "Player 2 WINS!!!"
    private var player1Turn: Boolean = true
    private var player2Turn: Boolean = false
    private lateinit var rollBtn: Button
    private lateinit var holdBtn: Button
    private lateinit var dieImgs: ImageView
    private lateinit var dieImgs2: ImageView
    private lateinit var player1Score: TextView
    private lateinit var player2Score: TextView
    private lateinit var winner: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dieImgs = findViewById(R.id.imageView2)
        dieImgs2 = findViewById(R.id.imageView5)
        player1Score = findViewById(R.id.player1PtView)
        player1Score.text = "0"
        player2Score = findViewById(R.id.player2PtView)
        player2Score.text = "0"
        rollBtn = findViewById(R.id.Roll)
        rollBtn.setOnClickListener {diceRandomizer()}
        holdBtn = findViewById(R.id.Hold)
        holdBtn.setOnClickListener{holdTurn()}
        winner = findViewById(R.id.winner)

    }

    //Function to randomize dice and determine what to do based of roll
    private fun diceRandomizer() {


        val die1 = Dice(6)
        val roll = die1.rollDie()
        val die2 = Dice(6)
        val roll2 = die2.rollDie()


        val image = when (roll) {
            1 -> R.drawable.d1
            2 -> R.drawable.d2
            3 -> R.drawable.d3
            4 -> R.drawable.d4
            5 -> R.drawable.d5
            else -> R.drawable.d6
        }
        val image2 = when (roll2) {
            1 -> R.drawable.d1
            2 -> R.drawable.d2
            3 -> R.drawable.d3
            4 -> R.drawable.d4
            5 -> R.drawable.d5
            else -> R.drawable.d6
        }

        //Set image content based on random draw
        dieImgs.setImageResource(image)
        dieImgs2.setImageResource(image2)



        if(player1Turn) {
            //Rule for when a single one is rolled
            if ((die1.equals(1) ) && (!die2.equals(1) ) || (!die1.equals(1)) && (die2.equals(1))) {

                player1Score.text = player1Pts.toString()
                player1Turn = false
                player2Turn = true


            }
            //Rule for when double ones are rolled
            else if (roll == 1 && roll2 == 1) {

                player1Pts -= player1Pts
                player1Score.text = player1Pts.toString()
                player1Turn = false
                player2Turn = true

            }
            //Rule for when dice land on same number(minus 1)
            else if (die1 == die2 && !die1.equals(1) && !die2.equals(1)) {


                player1Pts += roll + roll2
                player1Score.text = player1Pts.toString()
                player2Turn = false

            }
            //Rule for landing any other combination of numbers
            else {


                player1Pts += roll + roll2
                player1Score.text = player1Pts.toString()
                player2Turn = false
            }
        }
        if(player2Turn) {
            //Rule for when a single one is rolled
            if ((die1.equals(1) ) && (!die2.equals(1) ) || (!die1.equals(1)) && (die2.equals(1))) {

                player2Score.text = player2Pts.toString()
                player2Turn = false
                player1Turn = true




            }
            //Rule for when double ones are rolled
            else if (roll == 1 && roll2 == 1)  {

                player2Pts -= player2Pts
                player2Score.text = player2Pts.toString()
                player2Turn = false
                player1Turn = true

            }
            //Rule for when dice land on same number(minus 1)
            else if (die1 == die2 && !die1.equals(1) && !die2.equals(1)) {


                player2Pts += roll + roll2
                player2Score.text = player2Pts.toString()
                holdBtn.isEnabled = false
                player1Turn = false
            }
            //Rule for landing any other combination of numbers
            else {


                player2Pts += roll + roll2
                player2Score.text = player2Pts.toString()
                player1Turn =  false
            }
        }
        //display winner of game and create a new match
        if (player1Pts >= gamePoint){

            winner.text = announcement1
            recreate()


        }
        else if (player2Pts >= gamePoint){

            winner.text = announcement2
            recreate()
        }
    }
    //Function for holding turn and moving to next player
    private fun holdTurn(){

        if(player1Turn) {
            player1Turn = false
            player2Turn = true

        }
        else if(player2Turn) {
            player2Turn = false
            player1Turn = true
        }
    }
}



