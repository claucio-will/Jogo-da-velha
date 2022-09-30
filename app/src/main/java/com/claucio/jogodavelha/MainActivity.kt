package com.claucio.jogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.claucio.jogodavelha.databinding.ActivityMainBinding
import java.time.Duration

class MainActivity : AppCompatActivity() {

    var isPlayer1 = true
    var gameEnd = false

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configureBox(binding.top)
        configureBox(binding.startTop)
        configureBox(binding.topEnd)

        configureBox(binding.centerStart)
        configureBox(binding.centerEnd)
        configureBox(binding.center)

        configureBox(binding.bottomStart)
        configureBox(binding.bottom)
        configureBox(binding.bottomEnd)

        binding.buttomReset.setOnClickListener {
            resetGame(binding.top)
            resetGame(binding.startTop)
            resetGame(binding.topEnd)
            resetGame(binding.centerStart)
            resetGame(binding.centerEnd)
            resetGame(binding.center)
            resetGame(binding.bottomStart)
            resetGame(binding.bottom)
            resetGame(binding.bottomEnd)
        }


    }

    private fun resetGame(box: ImageView){
        box.setImageDrawable(null)
        box.tag = null
        isPlayer1 = true
        gameEnd = false
    }

    private fun configureBox(box: ImageView) {

        box.setOnClickListener {
            if (box.tag == null && !gameEnd) {
                if (isPlayer1) {
                    box.setImageResource(R.drawable.ic_baseline_circle_24)
                    isPlayer1 = false
                    box.tag = 1
                } else {
                    box.setImageResource(R.drawable.ic_baseline_close_24)
                    isPlayer1 = true
                    box.tag = 2
                }

                if (playerwin(1)){
                    Toast.makeText(this,"Player 1 Venceu!", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }else if (playerwin(2)){
                    Toast.makeText(this,"Player 2 Venceu!", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }
            }
        }
    }

    private fun playerwin(value: Int): Boolean {
        if ((binding.top.tag == value && binding.center.tag == value && binding.bottom.tag == value) ||
            (binding.startTop.tag == value && binding.centerStart.tag == value && binding.bottomStart.tag == value) ||
            (binding.topEnd.tag == value && binding.centerEnd.tag == value && binding.bottomEnd.tag == value) ||

            (binding.startTop.tag == value && binding.top.tag == value && binding.topEnd.tag == value) ||
            (binding.centerStart.tag == value && binding.center.tag == value && binding.centerEnd.tag == value) ||
            (binding.bottomStart.tag == value && binding.bottom.tag == value && binding.bottomEnd.tag == value) ||

            (binding.startTop.tag == value && binding.center.tag == value && binding.bottomEnd.tag == value) ||
            (binding.topEnd.tag == value && binding.center.tag == value && binding.bottomStart.tag == value)

                ) {
            return true
        }
        return false
    }


}

