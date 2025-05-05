// Package tempat file berada
package com.example.portalutb

// Import library yang diperlukan
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // Deklarasi variabel untuk elemen UI
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var ivToggle: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Memanggil fungsi onCreate dari kelas induk
        setContentView(R.layout.activity_login) // Mengatur layout yang digunakan

        var isPasswordVisible = false // Status awal password tidak terlihat

        // Inisialisasi elemen UI dari layout
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        tvRegister = findViewById(R.id.tv_register)
        ivToggle = findViewById(R.id.iv_toggle)

        // Ketika tombol login ditekan
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString() // Ambil input username dari EditText
            val password = etPassword.text.toString() // Ambil input password dari EditText

            if (username.isEmpty() || password.isEmpty()) { // Jika username atau password kosong
                Toast.makeText(this, "Harap isi semua kolomnya", Toast.LENGTH_SHORT).show() // Tampilkan toast
            } else {
                val success = UserStore.login(username, password) // Panggil fungsi login
                if (success) { // Jika login berhasil
                    Toast.makeText(this, "Berhasil masuk", Toast.LENGTH_SHORT).show() // Tampilkan toast
                    val intent = Intent(this, NewsPortalActivity::class.java) // Intent ke halaman berita
                    startActivity(intent) // Mulai halaman berita
                } else { // Jika login gagal
                    Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show() // Tampilkan toast
                }
            }
        }

        // Ketika teks "Belum punya akun? Daftar disini" ditekan
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java) // Intent ke halaman register
            startActivity(intent) // Mulai halaman register
        }

        // Ketika ikon toggle password ditekan
        ivToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible // Toggle status visibility password

            // Simpan font dan ukuran teks saat ini (karena akan berubah setelah inputType diubah)
            val currentTypeface = etPassword.typeface
            val currentTextSize = etPassword.textSize

            if (isPasswordVisible) {
                // Tampilkan password sebagai teks biasa
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                ivToggle.setImageResource(R.drawable.visibility_off_24px) // Ganti ikon ke 'mata tertutup'
            } else {
                // Sembunyikan password (dalam bentuk bulatan)
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                ivToggle.setImageResource(R.drawable.visibility_24px) // Ganti ikon ke 'mata terbuka'
            }

            // Kembalikan font dan ukuran teks agar tetap konsisten setelah perubahan inputType
            etPassword.typeface = currentTypeface // Set font
            etPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize) // Set ukuran

            // Letakkan kursor di akhir teks
            etPassword.setSelection(etPassword.text.length)
        }
    }
}