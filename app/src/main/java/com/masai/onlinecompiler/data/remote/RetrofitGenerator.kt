package com.masai.onlinecompiler.data.remote

import com.masai.onlinecompiler.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit



class RetrofitGenerator {


 companion object{
    //private static final String BASE_URL = "https://digi-api.airtel.in/compassLocation/rest/address/autocomplete?queryString=airtel&city=gurgaon";
    private val BASE_URL = "https://api.jdoodle.com/v1/"

    val CLIENT_ID = "a40b83bb35744787ef54491c1c12e9e9"
    val CLIENT_SECRET = "6c93f6747b5858525dc8ec185b38fd81feeef16611aa959b4044d5e5a3b81374"

    fun <S> createService(serviceClass: Class<S>?): S {
        val retrofit = getRetrofit(BASE_URL)
        return retrofit.create(serviceClass)
    }

    fun <S> createServiceForServer(serviceClass: Class<S>?, url: String): S {
        val retrofit = getRetrofit(url)
        return retrofit.create(serviceClass)
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        val httpClient: Builder = Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .cache(null)
        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                val newRequest: Request.Builder = request.newBuilder()
                newRequest.header("Content-Type", "application/x-www-form-urlencoded")
                newRequest.header("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6")
                newRequest.header("Origin", "http://www.dooccn.com")
                newRequest.header("Referer", "http://www.dooccn.com/python/")
                newRequest.header("Accept-Language", "en-US")
                return chain.proceed(newRequest.build())
            }
        })
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        }
        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()) //.addCallAdapterFactory(new LiveDataCallAdapterFactory())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
        return builder.client(httpClient.build()).build()
    }

 }

}