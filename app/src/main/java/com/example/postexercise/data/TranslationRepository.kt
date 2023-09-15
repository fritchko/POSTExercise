package com.example.postexercise.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TranslationRepository {

    var translationEndpoint: TranslationInterface? = null

    suspend fun getLanguage(): Response<TranslatorData.LanguageData>? {
        if (translationEndpoint == null) {
            translationEndpoint = createRetrofitInstance().create(TranslationInterface::class.java)
        }

        return translationEndpoint?.getLanguages()
    }

    suspend fun postData(requestData: TranslatorData.RequestData): Response<TranslatorData.ResponseData>? {

        if (translationEndpoint == null) {
            translationEndpoint = createRetrofitInstance().create(TranslationInterface::class.java)
        }

        return translationEndpoint?.postTranslation(requestData.sourceLanguage,requestData.targetLanguage,requestData.text)
    }


    fun createRetrofitInstance(): Retrofit {

        val baseUrl = "https://text-translator2.p.rapidapi.com/"
        val loggingInterceptor = HttpLoggingInterceptor()
        val authInterceptor = AuthInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}