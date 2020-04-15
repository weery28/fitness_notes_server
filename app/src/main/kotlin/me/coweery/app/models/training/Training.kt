package me.coweery.app.models.training

import java.util.Date
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "trainings")
open class Training(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "name")
    val name: String,

    @Column(name = "is_complete")
    val isComplete: Boolean,

    @Column(name = "creation_date")
    val creationDate: Date,

    @Column(name = "date")
    val date: Date
)

@Entity
@Table(name = "trainings")
open class FullTraining(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    @Column(name = "user_id")
    open val userId: Long,

    @Column(name = "name")
    open val name: String,

    @Column(name = "is_complete")
    open val isComplete: Boolean,

    @Column(name = "creation_date")
    open val creationDate: Date,

    @Column(name = "date")
    open val date: Date
) {

    @OneToMany(
        mappedBy = "training",
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE],
        orphanRemoval = true,
        targetEntity = FullExercise::class
    )
    open var exercises: List<FullExercise> = emptyList()
        set(value) {
            value.forEach { it.training = this }
            field = value
        }
}