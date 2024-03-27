package com.github.fajaragungpramana.our.module.login

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.fajaragungpramana.our.common.app.AppFragment
import com.github.fajaragungpramana.our.common.contract.AppState
import com.github.fajaragungpramana.our.databinding.FragmentLoginBinding
import com.github.fajaragungpramana.our.widget.extension.snackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : AppFragment<FragmentLoginBinding>(), AppState {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initTextField()
        initOnClick()
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {

                when (it) {

                    is LoginState.OnLoadingLogin -> viewBinding.opbLogin.isLoading = it.isLoading

                    is LoginState.OnSuccessLogin -> {}

                    is LoginState.OnMessage -> viewBinding.root.snackBar(it.message)

                }

            }
        }
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