package dev.himanshu.moviesearchapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform