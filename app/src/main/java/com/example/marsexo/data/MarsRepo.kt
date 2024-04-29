
package com.example.marsexo.data

import com.example.marsexo.network.MarsApiService

// Repository
interface MarsPhotosRepository {
  // Mars API
    suspend fun getMarsPhotos(): List<MarsPic>
}

// Network
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
// Mars API
    override suspend fun getMarsPhotos(): List<MarsPic> = marsApiService.getPhotos()
}
