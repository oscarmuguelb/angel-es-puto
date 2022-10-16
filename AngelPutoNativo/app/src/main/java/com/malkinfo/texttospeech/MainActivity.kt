package com.malkinfo.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var speakBtn:Button
    private lateinit var angelBtn:Button
    private lateinit var cristianBtn:Button
    private lateinit var userText:EditText
    private lateinit var seekBerPitch:SeekBar
    private lateinit var seekBerSpeed:SeekBar
    private lateinit var mTTs :TextToSpeech
    private lateinit var mTTsEspa :TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**set find view Id*/
        speakBtn = findViewById(R.id.btnSpeak)
        angelBtn =findViewById(R.id.angel)
        cristianBtn =findViewById(R.id.cristian)
        userText = findViewById(R.id.edTv)
        seekBerPitch = findViewById(R.id.seekBar_pitch)
        seekBerSpeed = findViewById(R.id.seekBar_speed)
        mTTs = TextToSpeech(this){status->
            if (status == TextToSpeech.SUCCESS){
                val result = mTTs.setLanguage(Locale.ENGLISH)
                //val result = mTTs.isLanguageAvailable( Locale("spa"))//*
                if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("TTs","Language is not Supported")
                }else{
                    speakBtn.isEnabled = true
                }
            }
            else{
                Log.e("TTs","Initialization failed")
            }
        }
        mTTsEspa = TextToSpeech(this){status->
            if (status == TextToSpeech.SUCCESS){

                val result = mTTsEspa.isLanguageAvailable( Locale("spa"))
                if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("TTs","Language is not Supported")
                }else{
                    speakBtn.isEnabled = true
                }
            }
            else{
                Log.e("TTs","Initialization failed")
            }
        }
        speakBtn.setOnClickListener { speak() }
        angelBtn.setOnClickListener { angelFun() }
        cristianBtn.setOnClickListener { cristianFun() }
    }
    private fun angelFun() {
        val text = userText.text.toString()
        var pitch = seekBerPitch.progress.toFloat()/50
        if (pitch<0.1) pitch = 0.1f
        var speed = seekBerSpeed.progress.toFloat()/50
        if (speed<0.1) speed = 0.1f
        mTTsEspa.setPitch(pitch)
        mTTsEspa.setSpeechRate(speed)
        mTTsEspa.speak("Angel es puto",TextToSpeech.QUEUE_FLUSH,null)
    }
    private fun cristianFun() {
        val text = userText.text.toString()
        var pitch = seekBerPitch.progress.toFloat()/50
        if (pitch<0.1) pitch = 0.1f
        var speed = seekBerSpeed.progress.toFloat()/50
        if (speed<0.1) speed = 0.1f
        mTTsEspa.setPitch(pitch)
        mTTsEspa.setSpeechRate(speed)
        mTTsEspa.speak("Paga Cristian rata, Paga Cristian rata",TextToSpeech.QUEUE_FLUSH,null)
    }

    private fun speak() {
        val text = userText.text.toString()
        var pitch = seekBerPitch.progress.toFloat()/50
        if (pitch<0.1) pitch = 0.1f
        var speed = seekBerSpeed.progress.toFloat()/50
        if (speed<0.1) speed = 0.1f
        mTTs.setPitch(pitch)
        mTTs.setSpeechRate(speed)
        mTTs.speak(text,TextToSpeech.QUEUE_FLUSH,null)
    }

    override fun onDestroy() {
        if (mTTs != null){
            mTTs.stop()
            mTTs.shutdown()
        }
        super.onDestroy()
    }
    
}