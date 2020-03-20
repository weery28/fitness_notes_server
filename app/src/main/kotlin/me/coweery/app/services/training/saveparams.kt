package me.coweery.app.services.training

import java.util.Date

class SaveTrainingParams(
    val name: String,
    val creationTime: Date,
    val saveExerciseParams: List<SaveExerciseParams>
)

class SaveExerciseParams(
    val name: String,
    val setsCount: Int,
    val weight: Float,
    val repsCount: Int,
    val sets: List<SaveSetParams>
)

class SaveSetParams(
    val repsCount: Int,
    val weight: Float
)