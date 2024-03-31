package com.github.fajaragungpramana.our.module.login

import android.os.Bundle
import android.util.Patterns
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.fajaragungpramana.our.common.app.AppFragment
import com.github.fajaragungpramana.our.common.contract.AppState
import com.github.fajaragungpramana.our.core.data.remote.auth.request.LoginRequest
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

                    is LoginState.OnSuccessLogin -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                        findNavController().navigate(action)
                    }

                    is LoginState.OnMessage -> viewBinding.root.snackBar(it.message)

                }

            }
        }
    }

    private fun initTextField() {
        viewBinding.apply {

            oftEmail.addTextChangedListener { isEnableLogin() }

            oftPassword.addTextChangedListener { isEnableLogin() }

        }
    }

    private fun initOnClick() {
        viewBinding.apply {
            opbLogin.setOnClickListener {
                viewModel.setEvent(
                    LoginEvent.OnLogin(
                        LoginRequest(
                            email = oftEmail.text,
                            password = oftPassword.text
                        )
                    )
                )
            }

            oatLogin.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun isEnableLogin() {
        val email = viewBinding.oftEmail.text
        val password = viewBinding.oftPassword.text

        viewBinding.opbLogin.isEnable = Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.isNotEmpty()
    }

}