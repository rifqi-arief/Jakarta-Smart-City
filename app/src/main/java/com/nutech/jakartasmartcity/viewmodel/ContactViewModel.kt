package com.nutech.jakartasmartcity.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nutech.jakartasmartcity.ContactApplication
import com.nutech.jakartasmartcity.api.response.ContactResponse
import com.nutech.jakartasmartcity.repository.ContactRepository
import com.nutech.jakartasmartcity.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class ContactViewModel (app: Application, val contactRepository: ContactRepository) : AndroidViewModel(app){
    val contact : MutableLiveData<Resource<ContactResponse>> = MutableLiveData()
    var contactResponse: ContactResponse? = null

    init {
        getContact()
    }

    fun getContact() = viewModelScope.launch {
        safeContactCall()
    }

    private suspend fun safeContactCall(){
        contact.postValue(Resource.Loading())
        try{
            if(hasIntentConnection()){
                val response = contactRepository.getContacts()
                contact.postValue(handleContactResponse(response))
            }else{
                contact.postValue(Resource.Error("No internet connection"))
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> contact.postValue(Resource.Error("Network failure"))
                else -> contact.postValue(Resource.Error("Conversion error"))
            }
        }
    }

    private fun handleContactResponse(response: Response<ContactResponse>) : Resource<ContactResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                    contactResponse = resultResponse
                return Resource.Success(contactResponse?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun hasIntentConnection(): Boolean{
        val connectivityManager = getApplication<ContactApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =  connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}