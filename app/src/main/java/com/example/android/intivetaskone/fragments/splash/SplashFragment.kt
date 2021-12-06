package com.example.android.intivetaskone.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.android.intivetaskone.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var viewModel: SplashModelView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //View binder to use with NavController in Timer
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[SplashModelView::class.java]

        viewModel.isTimeUp.observe(viewLifecycleOwner, { time ->
            if (time) {
                Navigation.findNavController(binding.root).navigate(
                    SplashFragmentDirections.actionSplashFragmentToMainFragment()
                )
            }
        })

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
                //Hides the AppBar instantly
                (requireActivity() as AppCompatActivity).supportActionBar?.hide()
            } else {
                controller.show(WindowInsetsCompat.Type.systemBars())
                (requireActivity() as AppCompatActivity).supportActionBar?.show()
            }
        }
    }
}