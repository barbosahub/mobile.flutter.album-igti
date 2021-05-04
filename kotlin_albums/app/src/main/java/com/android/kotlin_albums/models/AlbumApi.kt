package com.android.kotlin_albums.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AlbumApi {
    @SerializedName("albumId")
    @Expose
    private var albumId: Int? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("url")
    @Expose
    private var url: String? = null

    @SerializedName("thumbnailUrl")
    @Expose
    private var thumbnailUrl: String? = null

    fun getAlbumId(): Int? {
        return albumId
    }

    fun setAlbumId(albumId: Int?) {
        this.albumId = albumId
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getThumbnailUrl(): String? {
        return thumbnailUrl
    }

    fun setThumbnailUrl(thumbnailUrl: String?) {
        this.thumbnailUrl = thumbnailUrl
    }

}