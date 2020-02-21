package me.coweery.app.models

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
@Table(name = "exercise_plan")
class ExercisePlan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    val exercise: Exercise,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_plan_id")
    val trainingPlan: TrainingPlan,

    @Column(name = "approaches_count")
    val approachesCount: Int,

    @Column(name = "weight")
    val weight: Float,

    @Column(name = "repetitions_count")
    val repetitionsCount: Int
)