package com.example.portalutb

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

    companion object {
        private const val TAG = "RegisterActivity"
    }

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var ivToggle: ImageView
    private lateinit var btnRegister: Button
    private lateinit var ivProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var isPasswordVisible = false

        // Initialize UI components
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etConfirmPassword = findViewById(R.id.et_confirm_password)
        ivToggle = findViewById(R.id.iv_toggle)
        btnRegister = findViewById(R.id.btn_register)
        ivProfile = findViewById(R.id.iv_profile)

        // Set click listener for register button
        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirm_password = etConfirmPassword.text.toString()

            // Log the registration attempt
            Log.d(TAG, "Registration attempt: $username")
            if (password != confirm_password) {
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simple validation
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Daftar berhasil", Toast.LENGTH_SHORT).show()
            finish()
        }

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