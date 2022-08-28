package com.jadikoding.qntask1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jadikoding.qntask1.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsViewModel : ViewModel() {

    private val _sportsItem = MutableLiveData<List<SportsItem>>()
    val sportsItem: LiveData<List<SportsItem>> = _sportsItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllSports()
    }

    private fun getAllSports() {
        _isLoading.value = true
        val client = ApiService.getApiEnpoint().getAllSports()
        client.enqueue(object : Callback<MainModel> {
            override fun onResponse(
                call: Call<MainModel>,
                response: Response<MainModel>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _sportsItem.value = response.body()?.sports
                } else {
                    Log.e("SportsViewModel", "onResponse: Failure")
                }
            }

            override fun onFailure(call: Call<MainModel>, t: Throwable) {
                _isLoading.value = false
                Log.e("SportsViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}