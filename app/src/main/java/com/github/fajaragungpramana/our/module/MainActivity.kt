package com.github.fajaragungpramana.our.module

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.github.fajaragungpramana.our.R
import com.github.fajaragungpramana.our.common.app.AppActivity
import com.github.fajaragungpramana.our.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity<ActivityMainBinding>() {

    private val navigationController by lazy { supportFragmentManager.findFragmentById(R.id.fcv_main_container) as NavHostFragment }

    private var keep = true

    override fun onViewBinding(): ActivityMainBinding {
        installSplashScreen().let {
            it.setKeepOnScreenCondition { keep }
            it.setOnExitAnimationListener { viewProvider ->

                val slideDown = ObjectAnimator.ofFloat(
                    viewProvider.view,
                    View.TRANSLATION_Y,
                    0f,
                    +viewProvider.view.height.toFloat()
                )
                slideDown.interpolator = AnticipateInterpolator()
                slideDown.duration = 200L

                slideDown.doOnEnd { viewProvider.remove() }

                slideDown.start()
            }
        }

        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        initView()

        Handler(mainLooper).postDelayed({ keep = false }, DELAY)
    }

    private fun initView() {
        setSupportActionBar(viewBinding.mtlMain)
        viewBinding.mtlMain.setNavigationOnClickListener { navigationController.navController.navigateUp() }
        navigationController.navController.addOnDestinationChangedListener { _, destination, _ ->
            initToolbarTitle(destination)
        }
    }

    private fun initToolbarTitle(navDestination: NavDestination) {
        supportActionBar?.title = when (navDestination.id) {
            else -> ""
        }
    }

    private companion object {
        const val DELAY = 3000L
    }

}