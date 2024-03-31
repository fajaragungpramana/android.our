package com.github.fajaragungpramana.our.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.our.core.domain.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _isLogin by lazy { MutableLiveData<Boolean>() }
    val isLogin: LiveData<Boolean>
        get() = _isLogin

    fun setEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnLogin -> isLogin()
        }
    }

    private fun isLogin(): Job = viewModelScope.launch {
        _isLogin.postValue(authUseCase.isLogin())
    }

}