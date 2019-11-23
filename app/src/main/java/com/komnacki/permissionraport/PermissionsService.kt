package com.komnacki.permissionraport

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class PermissionsService {
    companion object {

        val BASE_URL : String = "https://us-central1-permissionraport.cloudfunctions.net"
        var rxAdapter : RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        var gson = GsonBuilder()
            .setLenient()
            .create()
        var interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        var client : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxAdapter)
            .build()
        val api : PermissionsApi = retrofit.create(PermissionsApi::class.java)
    }


    fun getContacts(email : String) : Observable<ContactsPOJO> {
        return api.getContacts(email)
    }

    fun sendContacts(email : String, contactsPOJO : ContactsPOJO) : Observable<ApiResponse> {
        Log.d("SERVICE", "sendContacts: $contactsPOJO")
        return api.sendContacts(email, contactsPOJO)
    }

}