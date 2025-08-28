@file:Suppress("DEPRECATION")

package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import coil.load

class SecondActivity : ComponentActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val savedState = sharedPref.getBoolean(KEY_SWITCH_STATE, false)
        binding.toggle.isChecked = savedState

        binding.toggle.setOnCheckedChangeListener { _, isChecked ->
            getPreferences(Context.MODE_PRIVATE).edit().apply() {
                putBoolean(KEY_SWITCH_STATE, isChecked)
                apply()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra(KEY_SWITCH_STATE, binding.toggle.isChecked)
            setResult(RESULT_OK, returnIntent)
            finish()
        }

        binding.memeImageView.load("https://resizer.mail.ru/p/f6c24987-c603-5dfd-9bc5-229441df5b0d/AQAGxrt3DN5y_PUff4JJjXpGyD4huogSefewMROw6Ow59rGZkH7hx4z2-AJ7wjIXUpQws48MxbVd4kl4_icwoKZhZK0.webp")
    }

    override fun onStart() {
        Log.d("Second_Activity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Second_Activity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Second_Activity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("Second_Activity", "onRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("Second_Activity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Second_Activity", "onDestroy")
        super.onDestroy()
    }

    companion object {
        const val KEY_SWITCH_STATE = "switch_state"
    }
}


