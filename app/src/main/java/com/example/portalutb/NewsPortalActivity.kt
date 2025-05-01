// Package tempat file berada
package com.example.portalutb

// Import library yang dibutuhkan
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog // Untuk menampilkan dialog konfirmasi

class NewsPortalActivity : AppCompatActivity() {

    // Deklarasi variabel untuk RecyclerView dan Adapter
    private lateinit var rvBerita: RecyclerView
    private lateinit var beritaAdapter: NewsAdapter

    // Data dummy daftar berita (title, deskripsi, dan URL gambar)
    private val dataBerita = listOf(
        NewsItem(
            "UTB Hadiri Halal Bihalal Perguruan Tinggi Jawa Barat",
            "Pererat Silaturahmi, Perkuat Kolaborasi, dan Dorong Sinergi Menuju Transformasi Pendidikan Tinggi.",
            "https://webutama.utb-univ.ac.id/storage/uploads/images/2049336133_halbi_(1).jpeg"
        ),
        NewsItem(
            "Universitas Teknologi Bandung dan ISBI Bandung",
            "Universitas Teknologi Bandung dan ISBI Bandung Tandatangani Kerja Sama untuk Pelaksanaan UTBK 2025 sebagai Mitra Seleksi Nasional Perguruan Tinggi.",
            "https://webutama.utb-univ.ac.id/storage/uploads/images/652728324_fewf.jpeg"
        ),
        NewsItem(
            "Kepala LLDIKTI Wilayah IV Jabar dan Banten Berikan Pengarahan untuk Dosen UTB",
            "Kepala LLDIKTI Wilayah IV Jabar dan Banten Berikan Pengarahan Untuk Membangun Dosen UTB yang Berkualitas, Berdaya Saing dan Siap Menghadapi Tantangan Akademik di Era Global.",
            "https://webutama.utb-univ.ac.id/storage/uploads/images/248703094_sdjsjdgshdsdbasjdbsdhsdhsbdhsd.jpg"
        ),
        NewsItem(
            "FEB UTB Perkuat Kolaborasi Internasional",
            "FEB UTB Perkuat Kolaborasi Internasional bersama Kumpulan Media Karangkraf, Dorong Kemajuan Pendidikan Global.",
            "https://webutama.utb-univ.ac.id/storage/uploads/images/950719767_gmfgjhfhjhhhhh.jpeg"
        )
        // Tambahkan lebih banyak data berita jika perlu
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_portal) // Menghubungkan ke layout XML

        // Inisialisasi RecyclerView dan adapter
        rvBerita = findViewById(R.id.rvBerita)
        beritaAdapter = NewsAdapter(dataBerita)

        // Mengatur adapter dan layout manager RecyclerView
        rvBerita.adapter = beritaAdapter
        rvBerita.layoutManager = LinearLayoutManager(this)
    }

    // Menangani tombol back untuk menampilkan dialog konfirmasi logout
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Apakah anda yakin ingin logout?")
            .setCancelable(false) // Dialog tidak bisa ditutup dengan klik di luar
            .setPositiveButton("Ya") { dialog, id ->
                // Aksi jika pengguna memilih "Ya"
                super.onBackPressedDispatcher.onBackPressed() // Kembali ke halaman sebelumnya (halaman login)
            }
            .setNegativeButton("Tidak") { dialog, id ->
                // Aksi jika pengguna memilih "Tidak"
                dialog.dismiss() // Tutup dialog
            }

        // Buat dan tampilkan dialog
        val alert = builder.create()
        alert.show()
    }
}
