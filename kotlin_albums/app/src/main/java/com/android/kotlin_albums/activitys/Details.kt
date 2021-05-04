package com.android.kotlin_albums.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.kotlin_albums.R
import com.android.kotlin_albums.adapters.PhotosAdapter
import com.android.kotlin_albums.models.AlbumApi
import com.android.kotlin_albums.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Details : AppCompatActivity() {
    private var context = this@Details
    lateinit var listView: ListView
    lateinit var pullToRefresh: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initComponents()

        pullToRefresh.setOnRefreshListener {
            progressBar(true)
            pullToRefresh.isRefreshing = false
            getPhotos()
        }

        progressBar(true)
        getPhotos()
    }

    private fun getPhotos() {
        val intent = intent
        val mId = intent.getStringExtra(MainActivity.Companion.ID)

        val call: Call<List<AlbumApi?>?>? = mId?.let { ApiClient.get.findById(mId) }
        call?.enqueue(object : Callback<List<AlbumApi?>?> {

            override fun onResponse(call: Call<List<AlbumApi?>?>, response: Response<List<AlbumApi?>?>) {
                   if (response.code() == 200) {
                    val res = response.body()
                    if (res != null) {
                        val adapter = PhotosAdapter(context, res as List<AlbumApi>)

                        listView!!.adapter = adapter
                        progressBar(false)
                        listView!!.onItemClickListener =
                            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                                val intent = Intent(context, Fullscreen::class.java)
                                    intent.putExtra(MainActivity.URL, res[i]?.getUrl().toString())
                                startActivity(intent)
                            }
                    }
                }else{
                    showToast("error ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<AlbumApi?>?>, t: Throwable) {
                showToast(t?.message.toString())
            }
        })
    }

    private fun initComponents() {
        listView = findViewById(R.id.lvdetails)
        pullToRefresh = findViewById<View>(R.id.pulltorefresh) as SwipeRefreshLayout
        progressBar = findViewById(R.id.progressBar)
    }
    private fun progressBar(isVisible: Boolean) {
        progressBar.isVisible = isVisible
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}