package me.coweery.app.models.training

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "exercises")
open class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long?,

    @ManyToOne
    @JoinColumn(name = "exercise_description_id")
    val exerciseDescription: ExerciseDescription,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id")
    var training: Training?,

    @Column(name = "sets_count")
    val setsCount: Int,

    @Column(name = "weight")
    val weight: Float,

    @Column(name = "reps_count")
    val repsCount: Int,

    @Column(name = "index")
    val index: Int
)

@Entity
@Table(name = "exercises")
open class FullExercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long?,

    @ManyToOne
    @JoinColumn(name = "exercise_description_id")
    open val exerciseDescription: ExerciseDescription,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id")
    open var training: FullTraining?,

    @Column(name = "sets_count")
    open val setsCount: Int,

    @Column(name = "weight")
    open val weight: Float,

    @Column(name = "reps_count")
    open val repsCount: Int,

    @Column(name = "index")
    open val index: Int
) {

    @OneToMany(
        mappedBy = "exercise",
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE],
        orphanRemoval = true,
        targetEntity = Set::class
    )
    open var sets: List<Set> = emptyList()
        set(value) {
            value.forEach { it.exercise = this }
            field = value
        }
}