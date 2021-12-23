package com.example.cryptogetter.Service

import com.example.cryptogetter.Model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():Call<List<CryptoModel>>
}