package com.github.fajaragungpramana.our.core.data.remote.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.app.AppResultState
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.core.data.remote.auth.response.RegisterResponse
import com.github.fajaragungpramana.our.rule.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AuthRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var authService: AuthService

    private lateinit var repository: IAuthRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = AuthRepository(authService)
    }

    @Test
    fun `register name less than 4 character, should return state InvalidName`() = runTest {
        val registerRequest = RegisterRequest(name = "Eko")

        val result = repository.register(registerRequest).first()
        Assert.assertTrue(result is AppResult.State)
        Assert.assertEquals(AppResultState.InvalidName, (result as AppResult.State).state)
    }

    @Test
    fun `register email invalid, should return state InvalidEmail`() = runTest {
        val registerRequest = RegisterRequest(name = "Fajar Agung Pramana", email = "fajar.agungpramana01@gmail")

        val result = repository.register(registerRequest).first()
        Assert.assertTrue(result is AppResult.State)
        Assert.assertEquals(AppResultState.InvalidEmail, (result as AppResult.State).state)
    }

    @Test
    fun `register password less than 8 character, should return state InvalidPassword`() = runTest {
        val registerRequest = RegisterRequest(
            name = "Fajar Agung Pramana",
            email = "fajar.agungpramana01@gmail.com",
            password = "fajar12"
        )

        val result = repository.register(registerRequest).first()
        Assert.assertTrue(result is AppResult.State)
        Assert.assertEquals(AppResultState.InvalidPassword, (result as AppResult.State).state)
    }

    @Test
    fun `register request good and server return success, should return success`() = runTest {
        val expectedResponse = RegisterResponse(
            error = false,
            message = "User Created"
        )
        val registerRequest = RegisterRequest(
            name = "Fajar Agung Pramana",
            email = "fajar.agungpramana01@gmail.com",
            password = "fajar123"
        )

        Mockito.`when`(authService.register(registerRequest)).thenReturn(Response.success(expectedResponse))
        val result = repository.register(registerRequest).first()

        Assert.assertTrue(result is AppResult.Success)
        Assert.assertEquals(expectedResponse, (result as AppResult.Success).data)
    }

    @Test
    fun `register request good and server return error, should return error`() = runTest {
        val expectedResponse = RegisterResponse(
            error = true,
            message = "Internal Server Error"
        )
        val registerRequest = RegisterRequest(
            name = "Fajar Agung Pramana",
            email = "fajar.agungpramana01@gmail.com",
            password = "fajar123"
        )

        Mockito.`when`(authService.register(registerRequest)).thenReturn(Response.error(500,
            """
                {
                    "error": true,
                    "message": "Internal Server Error"
                }
            """.trimIndent().toResponseBody(null)
        ))
        val result = repository.register(registerRequest).first()

        Assert.assertTrue(result is AppResult.Error)

        val resultError = result as AppResult.Error
        Assert.assertTrue(resultError.code == 500)
        Assert.assertEquals(expectedResponse.message, result.message)
    }

}