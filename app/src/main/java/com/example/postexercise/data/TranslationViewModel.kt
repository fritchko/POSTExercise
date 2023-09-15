package com.example.postexercise.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TranslationViewModel: ViewModel() {

    val result = MutableLiveData<TranslatorData.ResponseData?>()
    val languageResult = MutableLiveData<TranslatorData.LanguageData?>()

    // Function to fetch data using a GET request
    fun getLanguage() {
        viewModelScope.launch(IO) {
            val response = TranslationRepository.getLanguage()
            if (response?.isSuccessful == true) {
                languageResult.postValue(response.body())
                // Handle the response data as needed
            } else {
                Log.e("NETWORK ERROR", "Network Call failed")
                // Handle the error
            }
        }
    }

    // Function to send data using a POST request
    fun postData(requestData: TranslatorData.RequestData) {
        viewModelScope.launch(IO) {
            val response = TranslationRepository.postData(requestData)
            if (response?.isSuccessful == true) {
                val responseData = response.body()
                result.postValue(responseData)
                // Handle the response data as needed
            } else {
                Log.e("NETWORK ERROR", "Network Call failed")
                // Handle the error
            }
        }
    }

    fun translateText(inputText: String){
        val requestData = TranslatorData.RequestData(
            "it",
            "en",
            text = inputText
        )

        postData(requestData)
    }

}