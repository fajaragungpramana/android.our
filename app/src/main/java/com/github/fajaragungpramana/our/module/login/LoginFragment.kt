package com.github.fajaragungpramana.our.module.login

import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.fajaragungpramana.our.common.app.AppFragment
import com.github.fajaragungpramana.our.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : AppFragment<FragmentLoginBinding>() {

    override fun onViewBinding(container: ViewGroup?): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initTextField()
        initOnClick()
    }

    private fun initTextField() {
        viewBinding.apply {

            oftEmail.addTextChangedListener {

            }

            oftPassword.addTextChangedListener {

            }

        }
    }

    private fun initOnClick() {
        viewBinding.apply {
            oatLogin.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
        }
    }

}