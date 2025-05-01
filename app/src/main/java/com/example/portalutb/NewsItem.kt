// Package tempat file berada
package com.example.portalutb

// Data class item berita
data class NewsItem(
    val title: String,       // Judul dari berita
    val description: String, // Deskripsi singkat dari berita
    val imageURL: String     // URL gambar thumbnail berita (bisa berupa link dari internet)
)