// Package tempat file berada
package com.example.portalutb

// Import library yang diperlukan
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    // Companion object untuk log debugging
    companion object {
        private const val TAG = "RegisterActivity"
    }

    // Deklarasi komponen UI
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var ivToggle: ImageView
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // Menghubungkan ke layout XML

        // Variabel kontrol untuk melihat status visibilitas password
        var isPasswordVisible = false

        // Inisialisasi komponen UI dengan ID dari layout
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etConfirmPassword = findViewById(R.id.et_confirm_password)
        ivToggle = findViewById(R.id.iv_toggle)
        btnRegister = findViewById(R.id.btn_register)

        // Listener untuk tombol "Daftar"
        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirm_password = etConfirmPassword.text.toString()

            // Buat log proses registrasi
            Log.d(TAG, "Percobaan registrasi: $username")

            // Cek password dan konfirmasi password jika tidak sama
            if (password != confirm_password) {
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validasi input kosong
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Tolong isi semua kolom", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Dialog konfirmasi
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah anda yakin ingin mendaftar?")
                .setCancelable(false) // Dialog tidak bisa ditutup dengan klik di luar
                .setPositiveButton("Ya") { dialog, id ->
                    // Jika semua validasi lolos
                    Toast.makeText(this, "Daftar berhasil", Toast.LENGTH_SHORT).show()
                    finish() // Menutup activity dan kembali ke sebelumnya
                }
                .setNegativeButton("Tidak") { dialog, id ->
                    // Aksi jika pengguna memilih "Tidak"
                    dialog.dismiss() // Tutup dialog
                }

            // Buat dan tampilkan dialog konfirmasi
            val alert = builder.create()
            alert.show()
        }

        // Listener untuk icon toggle visibilitas password
        ivToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible // Ubah status visibilitas

            val currentTypeface = etPassword.typeface
            val currentTextSize = etPassword.textSize

            if (isPasswordVisible) {
                // Tampilkan password secara jelas
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                ivToggle.setImageResource(R.drawable.visibility_off_24px) // Gambar mata terbuka
            } else {
                // Sembunyikan password
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                ivToggle.setImageResource(R.drawable.visibility_24px) // Gambar mata tertutup
            }

            // Kembalikan style dan posisi kursor setelah mengubah inputType
            etPassword.typeface = currentTypeface
            etPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize)
            etPassword.setSelection(etPassword.text.length)
        }
    }
}
