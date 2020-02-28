package me.coweery.app.models.training

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "exercises")
class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_description_id")
    val exerciseDescription: ExerciseDescription,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id")
    val training: Training,

    @Column(name = "approaches_count")
    val approachesCount: Int,

    @Column(name = "weight")
    val weight: Float,

    @Column(name = "repetitions_count")
    val repetitionsCount: Int
)