package com.jadikoding.qntask1.retrofit

import com.jadikoding.qntask1.MainModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
        @GET("all_sports.php")
        fun getAllSports(): Call<MainModel>

}
