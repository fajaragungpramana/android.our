package com.github.fajaragungpramana.our.module.register

import androidx.lifecycle.ViewModel
import com.github.fajaragungpramana.our.core.domain.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {



}