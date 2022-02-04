package com.elmiramammadova.catchkenny

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score=0
    var imageArray=ArrayList<ImageView>()
    var runnable= Runnable {  }
    var handler=Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray.add(kenny1)
        imageArray.add(kenny2)
        imageArray.add(kenny3)
        imageArray.add(kenny4)
        imageArray.add(kenny5)
        imageArray.add(kenny6)
        imageArray.add(kenny7)
        imageArray.add(kenny8)
        imageArray.add(kenny9)

        hideImages()
        object : CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {
                textView.text="Time: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                textView.text="Time: 0"
                handler.removeCallbacks(runnable)
                for(images in imageArray){
                    images.visibility=View.INVISIBLE
                }
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game over")
                alert.setMessage("Continue?")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                    //restart
                    val intent=intent
                    finish()
                    startActivity(intent)
                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity,"Score: $score",Toast.LENGTH_LONG).show()
                })
                alert.show()


            }

        }.start()

    }

    fun Click(view: View){
        score++
        scoreText.text="Score: $score"
    }

    fun hideImages(){

        runnable=object :Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random=Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,200)
            }
        }
        handler.post(runnable)

    }
}