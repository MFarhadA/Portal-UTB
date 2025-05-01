package com.example.portalutb

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

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var ivToggle: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var isPasswordVisible = false

        // Inilisasi UI
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        tvRegister = findViewById(R.id.tv_register)
        ivToggle = findViewById(R.id.iv_toggle)

        // Set click listener untuk button login
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Validasi Login
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi semua kolomnya", Toast.LENGTH_SHORT).show()
            } else {
                // Masuk jika sudah memasukkan username dan password
                Toast.makeText(this, "Berhasil masuk", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, NewsPortalActivity::class.java)
                startActivity(intent)
            }
        }

        // Set click listener untuk button register
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Set click listener untuk toggle password visibility
        ivToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            val currentTypeface = etPassword.typeface
            val currentTextSize = etPassword.textSize

            if (isPasswordVisible) {
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                ivToggle.setImageResource(R.drawable.visibility_off_24px)
            } else {
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                ivToggle.setImageResource(R.drawable.visibility_24px)
            }

            // Kembalikan font dan ukuran teks setelah inputType berubah
            etPassword.typeface = currentTypeface
            etPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize)
            etPassword.setSelection(etPassword.text.length)

        }
    }
}