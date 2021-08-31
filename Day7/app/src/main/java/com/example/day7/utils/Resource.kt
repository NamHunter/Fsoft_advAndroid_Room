package com.example.day7.utils


sealed class Resource<out T> {
    data class Success<out T>(val data: T?) : Resource<T>()

    data class Failure(
        val message: String
    ) : Resource<Nothing>()
}