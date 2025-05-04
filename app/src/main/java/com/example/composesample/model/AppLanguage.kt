package com.example.composesample.model

enum class AppLanguage(val languageCode: String) {
    ENGLISH("en"),
    HINDI("hi");

    companion object {
        fun getAppLanguageOrNull(languageCode: String): AppLanguage? {
            return entries.firstOrNull { it.languageCode == languageCode }
        }
    }
}