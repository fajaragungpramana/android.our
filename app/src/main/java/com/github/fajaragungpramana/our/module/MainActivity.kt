package com.github.fajaragungpramana.our.module

import android.os.Bundle
import com.github.fajaragungpramana.our.common.app.AppActivity
import com.github.fajaragungpramana.our.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity<ActivityMainBinding>() {

    override fun onViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}