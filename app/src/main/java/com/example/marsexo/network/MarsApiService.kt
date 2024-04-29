
package com.example.marsexo.network

import com.example.marsexo.data.MarsPic
import retrofit2.http.GET

// public
interface MarsApiService {
   // THE Coroutines !!!!
    @GET("photos")
    suspend fun getPhotos(): List<MarsPic>
}
