package com.example.postexercise.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-RapidAPI-Key","eb18cd9c71msh0577909b20d2a98p196086jsn96cc2361a0fe")
            .addHeader("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
            .build()

        return chain.proceed(newRequest)
    }
}