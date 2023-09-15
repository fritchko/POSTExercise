package com.example.postexercise.data

import com.google.gson.annotations.SerializedName

sealed class TranslatorData {

    data class LanguageData(
        @SerializedName("code")
        val code: String?,
        @SerializedName("name")
        val name: String?
    )

    data class RequestData(
        @SerializedName("source_language")
        val sourceLanguage: String,
        @SerializedName("target_language")
        val targetLanguage: String,
        @SerializedName("text")
        val text: String
    )

    data class ResponseData(
        @SerializedName("status")
        val status: String,
        @SerializedName("data")
        val textData: Data
    )

    data class Data(
        @SerializedName("translatedText")
        val translatedText: String
    )

}



