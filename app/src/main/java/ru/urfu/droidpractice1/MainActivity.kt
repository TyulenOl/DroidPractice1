package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.viewmodel.MainActivityScreenViewModel
import ru.urfu.droidpractice1.viewmodel.MainViewModelFactory

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    private var isArticleRead by mutableStateOf(false)
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = this.getSharedPreferences(KEY_READ_STATE, Context.MODE_PRIVATE)

        val startLikes = sharedPref.getInt(KEY_LIKE_STATE, 0)
        val startDislikes = sharedPref.getInt(KEY_DISLIKE_STATE, 0)
        isArticleRead = sharedPref.getBoolean(KEY_READ_STATE, false)

        val factory = MainViewModelFactory(
            startLikes = startLikes,
            startDislikes = startDislikes,
            onLikesCounterChanged = { newLikes: Int ->
                sharedPref.edit().putInt(
                    KEY_LIKE_STATE, newLikes
                ).apply()
            },
            onDislikesCounterChanged = { newDislikes: Int ->
                sharedPref.edit().putInt(
                    KEY_DISLIKE_STATE, newDislikes
                ).apply()
            })

        val viewModel: MainActivityScreenViewModel by viewModels { factory }

        setContent {
            MainActivityScreen(
                onShare = ::share,
                onNavigate = ::navigateToSecondArticle,
                isArticleRead = isArticleRead,
                onReadStateChange = { newState: Boolean ->
                    isArticleRead = newState
                    saveReadState(sharedPref, newState)
                }, viewModel = viewModel
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    fun share() {
        val articleName = this.getString(R.string.article_name)
        val articleText = this.getString(R.string.article_text)
        val shareText = "$articleName\n\n$articleText"

        val intent =
            Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, shareText)
        val shareIntent = Intent.createChooser(intent, null)
        this.startActivity(shareIntent)
    }

    fun navigateToSecondArticle() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivityForResult(intent, 1)
    }

    fun saveReadState(sharedPreferences: SharedPreferences, state: Boolean) {
        sharedPreferences.edit()
            .putBoolean(KEY_READ_STATE, state).apply()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val isRead = data?.getBooleanExtra(SecondActivity.KEY_SWITCH_STATE, false) ?: false
            isArticleRead = isRead
            saveReadState(sharedPref, isRead)
        }
    }

    override fun onStart() {
        Log.d("Main_Activity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Main_Activity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Main_Activity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("Main_Activity", "onRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("Main_Activity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Main_Activity", "onDestroy")
        super.onDestroy()
    }

    companion object {
        const val KEY_READ_STATE = "read_state"
        const val KEY_LIKE_STATE = "like_state"
        const val KEY_DISLIKE_STATE = "dislike_state"
    }
}

