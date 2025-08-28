package ru.urfu.droidpractice1.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainActivityScreenViewModel(startLikes: Int, startDislikes: Int, private val onLikesCounterChanged: (Int) -> Unit, private val onDislikesCounterChanged: (Int) -> Unit) : ViewModel() {
    var _likesCounter by mutableStateOf(startLikes)
    var _dislikesCounter by mutableStateOf(startDislikes)

    fun increaseLikes() {
        _likesCounter++
        onLikesCounterChanged(_likesCounter)
    }

    fun increaseDislikes() {
        _dislikesCounter++
        onDislikesCounterChanged(_dislikesCounter)
    }
}