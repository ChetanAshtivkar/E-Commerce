package com.ecommerce.data.network

import com.ecommerce.data.models.Product
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

/**
 * Created by Chetan on 09/08/20.
 */
interface ProductService {

    @GET("productList?format=json")
    suspend fun getProductList(): ListApiResponse

    @GET("productDetail/{productId}?format=json")
    suspend fun getProductDetails(
        @Path("productId") productId: Int
    ): Product
}

object RetrofitFactory {
    const val BASE_URL = "http://35.154.217.118:7000/interview/"

    fun makeRetrofitService(): ProductService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(ProductService::class.java)
    }
}