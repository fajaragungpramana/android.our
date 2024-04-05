package com.github.fajaragungpramana.our.module.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.data.remote.auth.request.LoginRequest
import com.github.fajaragungpramana.our.core.domain.auth.AuthUseCase
import com.github.fajaragungpramana.our.core.domain.auth.model.Login
import com.github.fajaragungpramana.our.rule.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var authUseCase: AuthUseCase

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = LoginViewModel(authUseCase)
    }

    @Test
    fun `on login success should return state OnSuccessLogin`() = runTest {
        val loginRequest =
            LoginRequest(email = "fajar.agungpramana01@gmail.com", password = "fajar123")
        val login = Login(token = "this is token")

        Mockito.`when`(authUseCase.login(loginRequest))
            .thenReturn(flow { emit(AppResult.Success(login)) })
        viewModel.setEvent(LoginEvent.OnLogin(loginRequest))

        val stateLoadingTrue = viewModel.state.first()
        Assert.assertTrue((stateLoadingTrue as LoginState.OnLoadingLogin).isLoading)

        val stateLoadingFalse = viewModel.state.first()
        Assert.assertFalse((stateLoadingFalse as LoginState.OnLoadingLogin).isLoading)

        val stateSuccess = viewModel.state.first()
        Assert.assertTrue(stateSuccess is LoginState.OnSuccessLogin)
    }

}