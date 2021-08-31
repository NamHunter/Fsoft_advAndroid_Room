package com.example.day7.base

import com.example.day7.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    suspend fun <T>safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO){
            try{
                Resource.Success(apiCall.invoke())
            }catch (throwable: Throwable){
                Resource.Failure("loi")
            }
        }
    }
}