package com.techjd.medicinereminder.ui.activity

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.techjd.medicinereminder.R
import java.util.*


@Suppress("DEPRECATION")
class UIActivity : AppCompatActivity() {

    lateinit var dismiss: Button
    private lateinit var tts: TextToSpeech
    private lateinit var aM: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
            (WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        )
        setContentView(R.layout.activity_u_i)
        supportActionBar?.hide()
        dismiss = findViewById(R.id.dismiss)

        aM = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        val vol = aM.getStreamVolume(AudioManager.STREAM_MUSIC)
        aM.setStreamVolume(
            AudioManager.STREAM_MUSIC,
            aM.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
            AudioManager.FLAG_PLAY_SOUND
        )
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result: Int = tts.setLanguage(Locale.ENGLISH)
                if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    Log.e("TTS", "Language not supported")
                } else {

                    tts.speak("Time to Take Your medicines", TextToSpeech.QUEUE_FLUSH, null)

                    for (i in 1..10) {
                        tts.speak("Time to Take Your Medicines", TextToSpeech.QUEUE_ADD, null)
                    }

                }
            } else {

                Log.e("TTS", "Initialization failed")
            }
        }


        dismiss.setOnClickListener {
            tts.stop()
            aM.setStreamVolume(AudioManager.STREAM_MUSIC, vol, AudioManager.FLAG_PLAY_SOUND)
            finish()
        }


    }
}