package ar.com.pensa.meli.technicalchallenge.data.backend

import ar.com.pensa.meli.technicalchallenge.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendManager {

    var meliService: MELIService

    init {

        val logging = HttpLoggingInterceptor().apply {
            // set your desired log level
            level = HttpLoggingInterceptor.Level.BODY
        }
        val httpClient = OkHttpClient.Builder().apply {
            // add your other interceptors …
            // add logging as last interceptor
            addInterceptor(logging) // <-- this is the important line!
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().apply {

                //--- Services
                meliService = create(MELIService::class.java)
            }


    }


}