package com.george.exchange.rates.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ExchangeRatesInterceptor : Interceptor {
    private val accessKey = BuildConfig.ACCESS_KEY

    override fun intercept(chain: Interceptor.Chain): Response = run {
        val original: Request = chain.request()

        //Build QueryParam Url from the Original URL
        val customisedQueryParamUrl =
            original.url.newBuilder().addQueryParameter("access_key", accessKey).build()

        val requestBuilder = original.newBuilder().url(customisedQueryParamUrl)

        return chain.proceed(requestBuilder.build())
    }
}