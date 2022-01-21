package com.example.twodicepiggame

class Dice(private val dieSides: Int ) {
    fun rollDie(): Int{
        return(1..dieSides).random()
    }

}