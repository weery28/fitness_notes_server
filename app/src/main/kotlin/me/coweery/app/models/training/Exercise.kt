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
import javax.persistence.OrderBy
import javax.persistence.Table

@Entity
@Table(name = "exercises")
class Exercise(
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

    @OneToMany(mappedBy = "exercise", fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @OrderBy("number")
    val sets: List<Set>
)