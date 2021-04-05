package com.aflah.pokedex.base

import android.accounts.NetworkErrorException
import com.aflah.pokedex.model.ResultState
import okio.IOException
import retrofit2.Response
import java.util.concurrent.TimeoutException

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultState<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResultState.success(body)
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return when (e) {
                is IOException -> error<T>("No Internet Connection!")
                is TimeoutException -> error("Connection Timeout!")
                is NetworkErrorException -> error("Network Error!")
                else -> error(e.message ?: e.toString())
            }
        }
    }


    private fun <T> error(message: String): ResultState<T> = ResultState.error(message = message)
}