package com.example

enum class QuestionType {
    mcq, case, tf;
    
    fun label(): String = when (this) {
        mcq -> "Multiple Choice"
        case -> "Clinical Case"
        tf -> "True / False"
    }
}

enum class Difficulty {
    Easy, Moderate, Advanced
}

data class Question(
    val id: String,
    val type: QuestionType,
    val topic: String,
    val difficulty: Difficulty,
    val question: String,
    val options: List<String>? = null,
    val correctAnswer: String,
    val explanation: String
)
