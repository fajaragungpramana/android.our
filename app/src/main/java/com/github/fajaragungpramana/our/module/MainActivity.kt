package com.github.fajaragungpramana.our.module

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.github.fajaragungpramana.our.R
import com.github.fajaragungpramana.our.common.app.AppActivity
import com.github.fajaragungpramana.our.databinding.ActivityMainBinding
import com.github.fajaragungpramana.our.module.login.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

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

                if (viewModel.isLogin.value == true) {
                    val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                    navigationController.navController.navigate(action)
                }
            }
        }

        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        viewModel.setEvent(MainEvent.OnLogin)

        initView()

        Handler(mainLooper).postDelayed({ keep = false }, DELAY)
    }

    private fun initView() {
        setSupportActionBar(viewBinding.mtlMain)
        viewBinding.mtlMain.setNavigationOnClickListener { navigationController.navController.navigateUp() }
        navigationController.navController.addOnDestinationChangedListener { _, destination, _ ->
            initToolbarTitle(destination)
            initToolbarBack(destination)
        }
    }

    private fun initToolbarTitle(navDestination: NavDestination) {
        supportActionBar?.title = when (navDestination.id) {
            else -> ""
        }
    }

    private fun initToolbarBack(navDestination: NavDestination) {
        supportActionBar?.setDisplayHomeAsUpEnabled(
            when (navDestination.id) {
                R.id.login_fragment, R.id.main_fragment -> false

                else -> true
            }
        )
    }

    private companion object {
        const val DELAY = 3000L
    }

}