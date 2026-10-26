package com.example.data

data class User(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String? = null,
    val isOnline: Boolean = false,
    val lastSeen: String? = null,
    val lastMessage: String? = null,
    val unreadCount: Int = 0,
    val bio: String = "مرحباً! أنا أستخدم تطبيق الحواري",
    val isAiAssistant: Boolean = false
)
