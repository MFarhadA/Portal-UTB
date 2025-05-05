> **Disclaimer** ⚠️  
> This README was generated with the assistance of AI to summarize the project components for my project on Github.  
> For detailed, line-by-line explanations of the code—written entirely by me without AI involvement—please refer to the midterm exam assignment document, which was submitted for grading purposes.

# Portal UTB 📱

![ss](https://github.com/user-attachments/assets/ed247a08-890d-4901-a2fb-125d2e85a203)

A simple news portal Android application with splash screen, authentication, and news viewing features.

## Components 🧩

### 1. Splash Screen Activity ⏱️

Activity to display splash screen when opening the application.

```kotlin
package com.example.portalutb

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_TIME_OUT = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}
```

### 2. User Item 🗃️

Data class representing a user item in the application.

```kotlin
package com.example.portalutb

data class User(val username: String, val password: String)

object UserStore {
    private val userList = mutableListOf<User>()

    fun register(username: String, password: String): Boolean {
        if (userList.any { it.username == username }) return false
        userList.add(User(username, password))
        return true
    }

    fun login(username: String, password: String): Boolean {
        return userList.any { it.username == username && it.password == password }
    }
}
```

### 3. Login Activity 🔐

Activity to display login form after splash screen finishes.

```kotlin
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

        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        tvRegister = findViewById(R.id.tv_register)
        ivToggle = findViewById(R.id.iv_toggle)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi semua kolomnya", Toast.LENGTH_SHORT).show()
            } else {
                val success = UserStore.login(username, password)
                if (success) {
                    Toast.makeText(this, "Berhasil masuk", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, NewsPortalActivity::class.java)
                    startActivity(intent) // Mulai halaman berita
                } else {
                    Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
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

            etPassword.typeface = currentTypeface
            etPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize)
            etPassword.setSelection(etPassword.text.length)
        }
    }
}
```

### 4. Register Activity 📝

Activity to display registration form.

```kotlin
package com.example.portalutb

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
    companion object {
        private const val TAG = "RegisterActivity"
    }

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var ivToggle: ImageView
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var isPasswordVisible = false

        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etConfirmPassword = findViewById(R.id.et_confirm_password)
        ivToggle = findViewById(R.id.iv_toggle)
        btnRegister = findViewById(R.id.btn_register)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirm_password = etConfirmPassword.text.toString()

            Log.d(TAG, "Percobaan registrasi: $username")

            if (password != confirm_password) {
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Tolong isi semua kolom", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah anda yakin ingin mendaftar?")
                .setCancelable(false)
                .setPositiveButton("Ya") { dialog, id ->
                    val success = UserStore.register(username, password)
                    if (success) {
                        Toast.makeText(this, "Daftar berhasil", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Username sudah terdaftar", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Tidak") { dialog, id ->
                    dialog.dismiss()
                }

            val alert = builder.create()
            alert.show()
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

            etPassword.typeface = currentTypeface
            etPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize)
            etPassword.setSelection(etPassword.text.length)
        }
    }
}
```

### 5. News Item 📰

Data class representing a news item in the application.

```kotlin
package com.example.portalutb

data class NewsItem(
    val title: String,
    val description: String,
    val imageURL: String
)
```

### 6. News Adapter 🔄

Adapter for displaying news items.

```kotlin
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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return BeritaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = listBerita[position]
        Glide.with(holder.itemView.context)
            .load(berita.imageURL)
            .into(holder.imgThumbnail)

        holder.tvJudul.text = berita.title
        holder.tvDeskripsi.text = berita.description
    }

    override fun getItemCount(): Int = listBerita.size
}
```

### 7. News Portal Activity 📱

Activity to display news list in the news portal page.

```kotlin
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
        NewsItem("Judul", "Deskripsi", "URL Gambar"),
        // ... more news items
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_portal)

        rvBerita = findViewById(R.id.rvBerita)
        beritaAdapter = NewsAdapter(dataBerita)

        rvBerita.adapter = beritaAdapter
        rvBerita.layoutManager = LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Apakah anda yakin ingin logout?")
            .setCancelable(false)
            .setPositiveButton("Ya") { dialog, id ->
                super.onBackPressedDispatcher.onBackPressed()
            }
            .setNegativeButton("Tidak") { dialog, id ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }
}
```

## Features ✨

- Splash screen on app launch
- User authentication (Login/Registration)
- Password visibility toggle
- News feed display using RecyclerView
- Confirmation dialogs for important actions
- Back press handling for logout functionality

## Libraries Used 📚

- AndroidX AppCompat
- RecyclerView
- Glide (for image loading)
- Android Handler (for splash screen timing)

## How It Works 🔍

1. App starts with SplashScreenActivity displaying for 3 seconds
2. LoginActivity is shown for user authentication
3. New users can register via RegisterActivity
4. After successful login, NewsPortalActivity displays news items
5. Back button prompts for logout confirmation
