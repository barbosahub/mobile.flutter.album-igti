package com.android.kotlin_albums.activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.kotlin_albums.R
import com.android.kotlin_albums.models.AlbumsApi
import com.android.pokeapi.adapters.AlbumsAdapter
import com.android.kotlin_albums.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var context = this@MainActivity
    lateinit var listView: ListView
    lateinit var pullToRefresh: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()

        pullToRefresh.setOnRefreshListener {
            progressBar(true)
            pullToRefresh.isRefreshing = false
            getAlbums()
        }
        progressBar(true)
        getAlbums()
    }

    private fun getAlbums() {
        val call: Call<List<AlbumsApi>> = ApiClient.get.findList()
        call.enqueue(object : Callback<List<AlbumsApi>> {
            override fun onResponse(
                call: Call<List<AlbumsApi>>,
                response: Response<List<AlbumsApi>>
            ) {
                val list = response!!.body()
                if (list != null) {
                    val adapter = list?.let { AlbumsAdapter(context, it) }

                    listView!!.adapter = adapter
                    progressBar(false)
                    listView!!.onItemClickListener =
                        AdapterView.OnItemClickListener { adapterView, view, i, l ->
                            val intent = Intent(context, Details::class.java)
                                intent.putExtra(ID, list?.get(i)?.id.toString())
                            startActivity(intent)
                        }
                }
            }
            override fun onFailure(call: Call<List<AlbumsApi>>, t: Throwable?) {
                showToast(t?.message.toString())
            }
        })
    }

    private fun initComponents() {
        listView = findViewById(R.id.listview)
        pullToRefresh = findViewById<View>(R.id.pulltorefresh) as SwipeRefreshLayout
        progressBar = findViewById(R.id.progressBar)
    }

    private fun progressBar(isVisible: Boolean) {
        progressBar.isVisible = isVisible
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ID = "com.android.kotlin_albums.activitys.ID"
        const val URL = "com.android.kotlin_albums.activitys.URL"
    }
}