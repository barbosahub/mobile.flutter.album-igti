package com.android.kotlin_albums.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import com.android.kotlin_albums.R
import com.android.kotlin_albums.models.AlbumApi

class PhotosAdapter(context: Context, private val elements: List<AlbumApi>) : ArrayAdapter<AlbumApi?>(context!!, R.layout.photos_menu_layout, elements) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.photos_menu_layout, parent, false)
        val title = rowView.findViewById<View>(R.id.txtphotostitle) as TextView
        val image = rowView.findViewById<View>(R.id.imagephotos) as ImageView
        val imageUri = Uri.parse(elements[position].getUrl())

        image.load(imageUri)
        title.text = "${elements[position].getTitle()?.toUpperCase()}"

        return rowView
    }
}


