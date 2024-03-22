package com.github.fajaragungpramana.our.module.register

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.fajaragungpramana.our.common.app.AppFragment
import com.github.fajaragungpramana.our.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : AppFragment<FragmentRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?): FragmentRegisterBinding =
        FragmentRegisterBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initTextField()
        initOnClick()
    }

    private fun initTextField() {
        viewBinding.apply {

            oftName.addTextChangedListener {

            }

            oftEmail.addTextChangedListener {

            }

            oftPassword.addTextChangedListener {

            }

        }
    }

    private fun initOnClick() {
        viewBinding.apply {

            oatRegister.setOnClickListener { findNavController().navigateUp() }

        }
    }

}