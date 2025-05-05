// Package tempat file berada
package com.example.portalutb

// Import class dan library yang diperlukan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

// Adapter untuk RecyclerView yang menampilkan daftar berita
class NewsAdapter(private val listNews: List<NewsItem>) :

    // Implementasi RecyclerView.Adapter
    RecyclerView.Adapter<NewsAdapter.BeritaViewHolder>() {

    // ViewHolder untuk menyimpan dan mengatur tampilan item berita
    inner class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgThumbnail: ImageView = itemView.findViewById(R.id.imgThumbnail) // Gambar thumbnail
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)             // Judul berita
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)     // Deskripsi berita
    }

    // Dipanggil saat ViewHolder dibuat (inflate layout item berita)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view = LayoutInflater.from(parent.context) // Inisialisasi LayoutInflater
            .inflate(R.layout.news_item, parent, false) // Inflate layout `news_item.xml`
        return BeritaViewHolder(view) // Mengembalikan ViewHolder
    }

    // Menghubungkan data berita ke tampilan (bind data ke setiap item)
    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val news = listNews[position]

        // Memuat gambar ke ImageView menggunakan Glide
        Glide.with(holder.itemView.context)
            .load(news.imageURL) // URL gambar dari objek NewsItem
            .into(holder.imgThumbnail) // Tampilkan ke imgThumbnail

        // Set teks judul dan deskripsi
        holder.tvTitle.text = news.title // Judul berita
        holder.tvDescription.text = news.description // Deskripsi berita
    }

    // Mengembalikan jumlah item dalam daftar berita
    override fun getItemCount(): Int = listNews.size // Mengembalikan panjang list berita
}
