package com.github.fajaragungpramana.our.module.register

import android.os.Bundle
import android.util.Patterns
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.fajaragungpramana.our.common.app.AppFragment
import com.github.fajaragungpramana.our.common.contract.AppState
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.databinding.FragmentRegisterBinding
import com.github.fajaragungpramana.our.widget.extension.snackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : AppFragment<FragmentRegisterBinding>(), AppState {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?): FragmentRegisterBinding =
        FragmentRegisterBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initTextField()
        initOnClick()
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {

                when (it) {

                    is RegisterState.OnLoadingRegister ->
                        viewBinding.opbRegister.isLoading = it.isLoading

                    is RegisterState.OnSuccessRegister -> {
                        viewBinding.root.snackBar(getString(com.github.fajaragungpramana.our.R.string.register_successfully))
                        findNavController().navigateUp()
                    }

                    is RegisterState.OnMessage ->
                        viewBinding.root.snackBar(it.message)

                }

            }
        }
    }

    private fun initTextField() {
        viewBinding.apply {

            oftName.addTextChangedListener { isEnableRegister() }

            oftEmail.addTextChangedListener { isEnableRegister() }

            oftPassword.addTextChangedListener { isEnableRegister() }

        }
    }

    private fun initOnClick() {
        viewBinding.apply {

            oatRegister.setOnClickListener { findNavController().navigateUp() }

            opbRegister.setOnClickListener {
                val name = oftName.text
                val email = oftEmail.text
                val password = oftPassword.text

                viewModel.setEvent(
                    RegisterEvent.OnRegister(
                        request = RegisterRequest(
                            name = name,
                            email = email,
                            password = password
                        )
                    )
                )
            }

        }
    }

    private fun isEnableRegister() {
        val name = viewBinding.oftName.text
        val email = viewBinding.oftEmail.text
        val password = viewBinding.oftPassword.text

        viewBinding.opbRegister.isEnable = name.length >= 4 &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length >= 8
    }

}