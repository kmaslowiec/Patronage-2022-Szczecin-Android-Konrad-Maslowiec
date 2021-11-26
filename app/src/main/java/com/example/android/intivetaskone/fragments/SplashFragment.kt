package com.example.android.intivetaskone.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android.intivetaskone.databinding.FragmentSplashBinding


private const val DELAY_TIME = 5000L

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //View binder
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        timer()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        fullScreenMode(true)
    }

    override fun onStop() {
        super.onStop()
        fullScreenMode(false)
    }

    private fun fullScreenMode(isFullScreen: Boolean) {
        WindowCompat.setDecorFitsSystemWindows(activity?.window!!, !isFullScreen)
        WindowInsetsControllerCompat(
            activity?.window!!,
            activity?.window?.decorView!!
        ).let { controller ->

            if (isFullScreen) {
                controller.systemBarsBehavior = WindowInsetsControllerCompat
                    .BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                controller.hide(WindowInsetsCompat.Type.systemBars())
                (requireActivity() as AppCompatActivity).supportActionBar?.hide()
            } else {
                controller.show(WindowInsetsCompat.Type.systemBars())
                (requireActivity() as AppCompatActivity).supportActionBar?.show()
            }
        }
    }

    private fun timer() {
        Handler(Looper.getMainLooper()).postDelayed({
            Navigation.findNavController(binding.root).navigate(
                SplashFragmentDirections
                    .actionSplashFragmentToMainFragment()
            )
        }, DELAY_TIME)
    }
}