package com.example.postexercise.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TranslationInterface {

    @GET("getLanguages")
    suspend fun getLanguages(): Response<TranslatorData.LanguageData>


    /** Nella maggior parte dei casi non serve mettere field, ma si mette direttamente @Body
     * assegnando alla request. ES: suspend fun postTranslation(@Body requestData: RequestData): Response<ResponseData>
     * **/

    @FormUrlEncoded
    @POST("translate")
    suspend fun postTranslation(
        @Field("source_language") sourceLanguage: String,
        @Field("target_language") targetLanguage: String,
        @Field("text") text: String
    ): Response<TranslatorData.ResponseData>

}