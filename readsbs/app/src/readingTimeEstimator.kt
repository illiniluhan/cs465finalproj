package com.example.readsbs.utils

object ReadingTimeEstimator {

    fun estimateTime(text: String, userSpeed: Double = 200.0): Double {
        val wordCount = text.split("\\s+".toRegex()).size
        val estimatedTime = wordCount / userSpeed
        return estimatedTime
    }

    // Function for Flesch-Kincaid score
    fun fleschKincaidScore(text: String): Double {
        val sentences = text.split(". ", "! ", "? ").size
        val words = text.split("\\s+".toRegex()).size
        val syllables = text.map { if ("aeiouAEIOU".contains(it)) 1 else 0 }.sum()

        return 0.39 * (words / sentences) + 11.8 * (syllables / words) - 15.59
    }
}
