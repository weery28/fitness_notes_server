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
@Table(name = "training")
class Training(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "name")
    val name: String,

    @Column(name = "completed")
    val completed: Boolean,

    @Column(name = "creation_time")
    val creationTime: Date,

    @OneToMany(mappedBy = "training", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val exercises: List<Exercise>
)