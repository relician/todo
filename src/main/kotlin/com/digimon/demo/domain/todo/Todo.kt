package com.digimon.demo.domain.todo

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "todos")
class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Lob
    @NotNull
    @Column(name = "content")
     var content: String? = null

    @NotNull
    @Column(name = "ordinal")
     var ordinal: Int = 0

    @Column(name = "modified_at")
    var modifiedAt: LocalDateTime? = null

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null
}
