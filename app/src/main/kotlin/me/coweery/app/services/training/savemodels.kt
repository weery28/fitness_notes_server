package me.coweery.app.services.training

import java.util.Date

class TrainingSaveModel(
    val id: Long?,
    val name: String,
    val creationTime: Date,
    val exercises: List<ExerciseSaveModel>,
    val isComplete: Boolean,
    val date: Date
)

class ExerciseSaveModel(
    val id: Long?,
    val name: String,
    val setsCount: Int,
    val weight: Float,
    val repsCount: Int,
    val index: Int,
    val sets: List<SetSaveModel>
)

class SetSaveModel(
    val id: Long?,
    val repsCount: Int,
    val weight: Float,
    val index: Int
)