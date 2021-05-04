package com.android.kotlin_albums.interfaces

import com.android.kotlin_albums.models.AlbumApi
import com.android.kotlin_albums.models.AlbumsApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IGitApi {
    @Headers("Content-type: application/json")
    @GET("albums")
    fun findList(): Call<List<AlbumsApi>>

    @GET("photos/")
    fun findById(@Query("albumId") movieName: String?): Call<List<AlbumApi?>?>?

}