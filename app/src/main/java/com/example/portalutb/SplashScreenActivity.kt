// Package tempat file berada
package com.example.portalutb

// Import library yang dibutuhkan
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        // Waktu tampilan splash screen dalam milidetik (3000ms = 3 detik)
        private const val SPLASH_TIME_OUT = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menetapkan layout splash screen
        setContentView(R.layout.activity_splash_screen)

        // Handler untuk menunda pindah ke LoginActivity selama 3 detik
        Handler(Looper.getMainLooper()).postDelayed({
            // Intent untuk berpindah dari SplashScreen ke LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)  // Memulai LoginActivity
            finish()               // Menutup SplashScreen agar tidak bisa kembali ke sini
        }, SPLASH_TIME_OUT.toLong())
    }
}