package com.komnacki.permissionraport

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
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
//        return api.sendContacts(email, JSONArray("[{\"id\": 2,\"name\": \"Tomek\",\"email\": \"tomek@hi.com\",\"phone_number\": \"+48 320 992 332\" },{\"id\": 4,\"name\": \"Asia\",\"email\": \"asia@hi.com\",\"phone_number\": \"+48 231 226 211\" }]"))
        var jsonArray =
            JSONArray("[{\"phone_number\":\"+48 321 223 332\",\"id\":1,\"email\":\"99@hi.com\",\"name\":\"99tt\"},{\"email\":\"cecylia@hi.com\",\"name\":\"Cecylia\",\"phone_number\":\"+48 320 345 332\",\"id\":13},{\"id\":2,\"email\":\"tomek@hi.com\",\"name\":\"Tomek\",\"phone_number\":\"+48 320 992 332\"},{\"email\":\"Mor@hi.com\",\"name\":\"Rym\",\"phone_number\":\"+48 121 992 332\",\"id\":3},{\"email\":\"asia@hi.com\",\"name\":\"Asia\",\"phone_number\":\"+48 231 226 211\",\"id\":4},{\"phone_number\":\"+48 665 411 020\",\"id\":43,\"email\":\"RoBo@ssss.com\",\"name\":\"Baro\"},{\"email\":\"tomek@hi.com\",\"name\":\"Tomek\",\"phone_number\":\"+48 555 334 322\",\"id\":65},{\"phone_number\":\"+48 320 345 332\",\"id\":9,\"email\":\"cecylia@hi.com\",\"name\":\"Cecylia\"}]")
        Log.d("SERVICE", jsonArray.toString())
        return api.sendContacts(email, jsonArray)
    }

}