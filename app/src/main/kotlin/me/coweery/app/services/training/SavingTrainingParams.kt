package me.coweery.app.services.training

import java.util.Date

class SavingTrainingParams(
    val name: String,
    val creationTime: Date,
    val exercises: List<Exercise>
) {

    class Exercise(
        val name: String,
        val setsCount: Int,
        val weight: Float,
        val repsCount: Int
    )
}