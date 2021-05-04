package com.corcoles.popcorn.retrofit

import com.corcoles.popcorn.common.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient {
    private val theMovieDBService: TheMovieDBService
    private val retrofit: Retrofit

    companion object {
        //Singleton
        var instace: TheMovieDBClient? = null
            get() {
                if (field == null) {
                    instace = TheMovieDBClient()
                }
                return instace
            }
    }

    init {
        //Incluir el interceptor definido para ñas peticiones

        val okkHttpClientBuilder = OkHttpClient.Builder()
        okkHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        //Contruir el cliente retrofit

        val cliente = okkHttpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.TMDAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()

        //Intancimos el servicio de Retrofit a partir del objeto retrofit

        theMovieDBService= retrofit.create(TheMovieDBService::class.java)
    }

    fun getTheMovieDBService() = theMovieDBService

}