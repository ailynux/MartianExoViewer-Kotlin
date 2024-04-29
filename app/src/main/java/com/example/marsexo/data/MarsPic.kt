
package com.example.marsexo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// defines a Mars photo
@Serializable
data class MarsPic(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
