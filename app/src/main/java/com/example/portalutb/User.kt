// Package tempat file berada
package com.example.portalutb

// Data class item berita
data class User(val username: String, val password: String)

// Object Singleton untuk menyimpan dan mengelola data user di memori
object UserStore {

    // Daftar pengguna
    private val userList = mutableListOf<User>()

    // Fungsi untuk mendaftarkan pengguna
    fun register(username: String, password: String): Boolean {
        if (userList.any { it.username == username }) return false // Return false jika username sudah digunakan
        userList.add(User(username, password)) // Tambahkan pengguna ke daftar
        return true
    }

    // Fungsi untuk melakukan login
    fun login(username: String, password: String): Boolean {
        return userList.any { it.username == username && it.password == password } // Return true jika username dan password cocok
    }
}