package com.example.portalutb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listBerita: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.BeritaViewHolder>() {

    inner class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgThumbnail: ImageView = itemView.findViewById(R.id.imgThumbnail)
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudul)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return BeritaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = listBerita[position]

        // Memuat gambar menggunakan Glide
        Glide.with(holder.itemView.context)
            .load(berita.imageURL) // Bisa juga menggunakan URL jika sumber gambar dari internet
            .into(holder.imgThumbnail)

        holder.tvJudul.text = berita.title
        holder.tvDeskripsi.text = berita.description
    }

    override fun getItemCount(): Int = listBerita.size
}