package com.example.android.intivetaskone.fragments.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val DELAY_TIME = 5000L

class SplashModelView : ViewModel() {
    private val _isTimeUp = MutableLiveData<Boolean>()
    val isTimeUp: LiveData<Boolean>
        get() = _isTimeUp

    init {
        timer()
    }

    private fun timer() {
        Handler(Looper.getMainLooper()).postDelayed({
            _isTimeUp.value = true
        }, DELAY_TIME)
    }
}