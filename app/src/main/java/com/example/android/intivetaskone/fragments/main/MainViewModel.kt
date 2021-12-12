package com.example.android.intivetaskone.fragments.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.intivetaskone.network.Api
import com.example.android.intivetaskone.network.InfoProperty
import com.example.android.intivetaskone.network.Infos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status

    private val _info = MutableLiveData<List<InfoProperty>>()

    val info: LiveData<List<InfoProperty>>
        get() = _info

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        viewModelScope.launch(Dispatchers.Default) {
            Api.retrofitService.getProperties().enqueue(object : Callback<Infos> {
                override fun onFailure(call: Call<Infos>, t: Throwable) {
                    _status.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<Infos>, response: Response<Infos>) {
                    _status.value = "Success: ${response.body()?.array?.get(0)} items"
                    _info.value = response.body()?.array
                }
            })
        }
    }
}