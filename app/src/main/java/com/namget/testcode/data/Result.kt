package com.namget.testcode.data

/**
 * Created by Namget on 2019.10.10.
 */
// coroutine을 이용시 callback을 통한 result 추출시 이용되는것 같음
/*
sealed class Result<out R>{

    data class Success<out T>(val data : T) : Result<T>()
    data class Error(val exception : Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}
*/
