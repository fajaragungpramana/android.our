package com.github.fajaragungpramana.our.module.main

import android.os.Bundle
import android.view.ViewGroup
import com.github.fajaragungpramana.our.common.app.AppFragment
import com.github.fajaragungpramana.our.databinding.FragmentMainBinding

class MainFragment : AppFragment<FragmentMainBinding>() {

    override fun onViewBinding(container: ViewGroup?): FragmentMainBinding =
        FragmentMainBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

}