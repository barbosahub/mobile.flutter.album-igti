package com.android.kotlin_albums.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AlbumsApi {
    @SerializedName("userId")
    @Expose
    var userId: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

}