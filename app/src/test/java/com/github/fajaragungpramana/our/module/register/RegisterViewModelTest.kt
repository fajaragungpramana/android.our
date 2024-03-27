package com.github.fajaragungpramana.our.module.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.app.AppResultState
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.core.domain.auth.AuthUseCase
import com.github.fajaragungpramana.our.core.domain.auth.model.Register
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
class RegisterViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var authUseCase: AuthUseCase

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = RegisterViewModel(authUseCase)
    }

    @Test
    fun `on register invalid name should return state OnResultState`() = runTest {
        val registerRequest = RegisterRequest(name = "Eko")

        Mockito.`when`(authUseCase.register(registerRequest)).thenReturn(flow { emit(AppResult.State(AppResultState.InvalidName)) })
        viewModel.setEvent(RegisterEvent.OnRegister(registerRequest))

        val stateLoadingTrue = viewModel.state.first()
        Assert.assertTrue((stateLoadingTrue as RegisterState.OnLoadingRegister).isLoading)

        val stateLoadingFalse = viewModel.state.first()
        Assert.assertFalse((stateLoadingFalse as RegisterState.OnLoadingRegister).isLoading)

        val stateResultState = viewModel.state.first()
        Assert.assertTrue((stateResultState as RegisterState.OnResultState).state == AppResultState.InvalidName)
    }

    @Test
    fun `on register invalid email should return state OnResultState`() = runTest {
        val registerRequest = RegisterRequest(name = "Fajar Agung Pramana", email = "fajar.agungpramana@gmail")

        Mockito.`when`(authUseCase.register(registerRequest)).thenReturn(flow { emit(AppResult.State(AppResultState.InvalidEmail)) })
        viewModel.setEvent(RegisterEvent.OnRegister(registerRequest))

        val stateLoadingTrue = viewModel.state.first()
        Assert.assertTrue((stateLoadingTrue as RegisterState.OnLoadingRegister).isLoading)

        val stateLoadingFalse = viewModel.state.first()
        Assert.assertFalse((stateLoadingFalse as RegisterState.OnLoadingRegister).isLoading)

        val stateResultState = viewModel.state.first()
        Assert.assertTrue((stateResultState as RegisterState.OnResultState).state == AppResultState.InvalidEmail)
    }

    @Test
    fun `on register invalid password should return state OnResultState`() = runTest {
        val registerRequest = RegisterRequest(name = "Fajar Agung Pramana", email = "fajar.agungpramana@gmail.com", password = "fajar12")

        Mockito.`when`(authUseCase.register(registerRequest)).thenReturn(flow { emit(AppResult.State(AppResultState.InvalidPassword)) })
        viewModel.setEvent(RegisterEvent.OnRegister(registerRequest))

        val stateLoadingTrue = viewModel.state.first()
        Assert.assertTrue((stateLoadingTrue as RegisterState.OnLoadingRegister).isLoading)

        val stateLoadingFalse = viewModel.state.first()
        Assert.assertFalse((stateLoadingFalse as RegisterState.OnLoadingRegister).isLoading)

        val stateResultState = viewModel.state.first()
        Assert.assertTrue((stateResultState as RegisterState.OnResultState).state == AppResultState.InvalidPassword)
    }

    @Test
    fun `on register success should return state OnSuccesRegister`() = runTest {
        val registerRequest = RegisterRequest(name = "Fajar Agung Pramana", email = "fajar.agungpramana@gmail.com", password = "fajar123")

        Mockito.`when`(authUseCase.register(registerRequest)).thenReturn(flow { emit(AppResult.Success(Register(message = "User Created"))) })
        viewModel.setEvent(RegisterEvent.OnRegister(registerRequest))

        val stateLoadingTrue = viewModel.state.first()
        Assert.assertTrue((stateLoadingTrue as RegisterState.OnLoadingRegister).isLoading)

        val stateLoadingFalse = viewModel.state.first()
        Assert.assertFalse((stateLoadingFalse as RegisterState.OnLoadingRegister).isLoading)

        val stateSuccess = viewModel.state.first()
        Assert.assertTrue(stateSuccess is RegisterState.OnSuccessRegister)
    }

}