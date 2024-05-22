package com.test.yamoowikiproject.retrofit

import com.google.gson.annotations.SerializedName

data class SearchAddressResponse(
    @SerializedName("documents") val documents: List<Document>,
)

data class Document(
    @SerializedName("address") val address: Address,
)

data class Address(
    @SerializedName("address_name") val addressName: String,
    @SerializedName("region_1depth_name") val region1depthName: String,
    @SerializedName("region_2depth_name") val region2depthName: String,
    @SerializedName("region_3depth_name") val region3depthName: String,
)
