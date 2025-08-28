package ru.urfu.droidpractice1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(
    private val startLikes: Int,
    private val startDislikes: Int,
    private val onLikesCounterChanged: (Int) -> Unit,
    private val onDislikesCounterChanged: (Int) -> Unit
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityScreenViewModel(startLikes, startDislikes, onLikesCounterChanged, onDislikesCounterChanged) as T
    }
}