package com.ensismoebius.canivete_suico

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class RolaDado : AppCompatActivity() {

    // Well... theres no way to initialize a field with
    // a view before the layout inflation, then, we have
    // to do this "lazy" initialization which is executed
    // after the "onCreate" event
    private val txtMessages: TextView by lazy { findViewById(R.id.txtMessages) }
    private val imgDice: ImageView by lazy { findViewById(R.id.imgEmptyImage) }

    // There is not main function or something like that
    // What really exists its a method which have to be
    // overwrite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Here we have to define which layout must be inflated
        // "R" stands for "Resources" and it is from where we
        // load our texts, images, string, etc.
        setContentView(R.layout.rola_dado)

        // Now we have all (or some) of our GUI defined
        // we have to do something with it, lets begin
        // by creating a variable that references the
        // button object

        val rollButton: Button = findViewById(R.id.btnRollButton)

        // You can change any property in the xml programmatically
        rollButton.text = getString(R.string.letsRoll)

        // And even add some action to buttons!
        rollButton.setOnClickListener {
            // Here we call a method
            rollDice()
        }
    }

    private fun rollDice() {
        // This shows a temporally message on the screen
        Toast.makeText(this, getString(R.string.diceRolled), Toast.LENGTH_SHORT).show()

        val randomValue: Int = Random.nextInt(6) + 1

        // You can change any property in the xml programmatically
        this.txtMessages.text = getString(R.string.rolled).plus(" $randomValue")

        // This is an alternative solution to a switch
        // IMPORTANT! Each resource is referred as an
        // integer number!
        val chosenDiceSide: Int = when (randomValue) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.empty_dice
        }

        this.imgDice.setImageResource(chosenDiceSide)
    }
}
