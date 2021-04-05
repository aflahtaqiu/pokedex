package com.aflah.pokedex.base

import com.aflah.pokedex.model.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class BaseRepository {

    protected suspend fun <L, R> singleSource(
            databaseQuery: () -> Flow<L>,
            networkCall: suspend () -> ResultState<R>,
            saveCallResult: suspend (R) -> Unit
    ): Flow<ResultState<L>> =
        flow {
            emit(ResultState.loading())

            val source = databaseQuery.invoke().map {
                ResultState.success(it)
            }
            val responseStatus = networkCall.invoke()

            if (responseStatus.status == ResultState.Status.SUCCESS) {
                responseStatus.data?.let { data -> saveCallResult(data) }
            } else if (responseStatus.status == ResultState.Status.ERROR) {
                emit(ResultState.error<L>(responseStatus.message))
            }

            source.apply {
                emitAll(this)
            }
        }.flowOn(Dispatchers.IO)
}