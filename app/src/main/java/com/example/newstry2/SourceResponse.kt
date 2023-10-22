package com.example.newstry2
import com.google.gson.annotations.SerializedName


data class SourceResponse(
    @SerializedName("status") val status : String,
    @SerializedName("totalResults") val totalResults : Int,
    @SerializedName("articles") val article : List<Articles>
    )

data class Articles (

    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,

    @SerializedName("author") val author : String,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("url") val url : String,
    @SerializedName("urlToImage") val urlToImage : String,

)