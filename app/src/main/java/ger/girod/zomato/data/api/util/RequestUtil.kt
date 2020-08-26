package ger.girod.zomato.data.api.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> executeRequest(
    dispatcher: CoroutineDispatcher,
    request: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(request.invoke())
        } catch (e: Exception) {
            ResultWrapper.ServerError(e.message!!)
        }
    }
}