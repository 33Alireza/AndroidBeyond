package com.example.androidbeyond.viewmodel

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

}

data class UserProfile(
    val name: String,
    val email: String,
    val posts: Int,
    val followers: Int,
    val lastLogin: String,
)