package com.corcoles.popcorn.retrofit

import com.corcoles.popcorn.common.Constantes
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDBInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //Añadimos paramteros a la URL de la cadena que recibimos (chain)
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constantes.URL_PARAM_API_KEY, Constantes.API_KEY)
            .addQueryParameter(Constantes.URL_PARAM_LENGUAJE, Constantes.LANGUAGE)
            .build()

        //Añadimos la URL creada a la URL de la peticion

        var request = chain.request()
        request = request?.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type", "aplication/json")
            .addHeader("Accept", "aplication/json")
            .build()

        return chain.proceed(request)

    }
}