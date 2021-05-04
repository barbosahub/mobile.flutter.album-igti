package com.android.pokeapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.android.kotlin_albums.R
import com.android.kotlin_albums.models.AlbumApi
import com.android.kotlin_albums.models.AlbumsApi

class AlbumsAdapter(context: Context, private val elements: List<AlbumsApi>) : ArrayAdapter<AlbumsApi?>(context!!, R.layout.albums_menu_layout, elements) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.albums_menu_layout, parent, false)
        val name = rowView.findViewById<View>(R.id.txtname) as TextView
        val title = rowView.findViewById<View>(R.id.txttitle) as TextView

        title.text = "${elements[position].title?.toUpperCase()}"
        name.text =  "Album: ${elements[position].id} | User: ${elements[position].userId}"

        return rowView
    }
}


