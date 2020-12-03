package com.onecupcode.flicker.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val IN_QUALIFIER = "in:name,description"

interface FlickerServices {
  //  https://api.flickr.com/services/rest/?
    //  method=flickr.interestingness.getList
    // &api_key=213bb6109c899b200212b0109e125ae6
    // &per_page=5&page=1&format=json&nojsoncallback=1


    @GET("services/rest/?method=flickr.interestingness.getList")
    suspend fun getImages(@Query("api_key") api_key : String = "213bb6109c899b200212b0109e125ae6",
    @Query("per_page") per_page: Int,
    @Query("page") page:Int,
    @Query("format") format:String="json",
    @Query("nojsoncallback") nojsoncallback:String="1") : FlickerResponse


    @GET("services/rest/?method=flickr.photos.search")
    suspend fun searchImages(@Query("api_key") api_key : String = "213bb6109c899b200212b0109e125ae6",
                             @Query("text") text : String,
                             @Query("per_page") per_page: Int,
                          @Query("page") page:Int,
                          @Query("format") format:String="json",
                          @Query("nojsoncallback") nojsoncallback:String="1") : FlickerResponse


    companion object {
        private const val BASE_URl = "https://api.flickr.com/"
        fun create(): FlickerServices {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FlickerServices::class.java)
        }
    }

}