package com.example.portalutb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog


class NewsPortalActivity : AppCompatActivity() {

    private lateinit var rvBerita: RecyclerView
    private lateinit var beritaAdapter: NewsAdapter
    private val dataBerita = listOf(
        NewsItem("UTB Hadiri Halal Bihalal Perguruan Tinggi Jawa Barat", "Pererat Silaturahmi, Perkuat Kolaborasi, dan Dorong Sinergi Menuju Transformasi Pendidikan Tinggi.", "https://webutama.utb-univ.ac.id/storage/uploads/images/2049336133_halbi_(1).jpeg"),
        NewsItem("Universitas Teknologi Bandung dan ISBI Bandung", "Universitas Teknologi Bandung dan ISBI Bandung Tandatangani Kerja Sama untuk Pelaksanaan UTBK 2025 sebagai Mitra Seleksi Nasional Perguruan Tinggi.", "https://webutama.utb-univ.ac.id/storage/uploads/images/652728324_fewf.jpeg"),
        NewsItem("Kepala LLDIKTI Wilayah IV Jabar dan Banten Berikan Pengarahan untuk Dosen UTB", "Kepala LLDIKTI Wilayah IV Jabar dan Banten Berikan Pengarahan Untuk Membangun Dosen UTB yang Berkualitas, Berdaya Saing dan Siap Menghadapi Tantangan Akademik di Era Global.", "https://webutama.utb-univ.ac.id/storage/uploads/images/248703094_sdjsjdgshdsdbasjdbsdhsdhsbdhsd.jpg"),
        NewsItem("FEB UTB Perkuat Kolaborasi Internasional", "FEB UTB Perkuat Kolaborasi Internasional bersama Kumpulan Media Karangkraf, Dorong Kemajuan Pendidikan Global.", "https://webutama.utb-univ.ac.id/storage/uploads/images/950719767_gmfgjhfhjhhhhh.jpeg"),
        // Tambah lebih banyak data
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_portal)

        rvBerita = findViewById(R.id.rvBerita)
        beritaAdapter = NewsAdapter(dataBerita)

        rvBerita.adapter = beritaAdapter
        rvBerita.layoutManager = LinearLayoutManager(this)
    }

    // Override onBackPressed untuk menampilkan dialog konfirmasi
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Apakah anda yakin ingin logout?")
            .setCancelable(false) // Agar dialog tidak hilang jika pengguna menekan luar dialog
            .setPositiveButton("Ya") { dialog, id ->
                // Aksi ketika klik "Ya" (Logout atau keluar)
                super.onBackPressed() // Menutup activity
            }
            .setNegativeButton("Tidak") { dialog, id ->
                // Aksi ketika klik "Tidak" (Membatalkan keluar)
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show() // Menampilkan dialog konfirmasi
    }
}
