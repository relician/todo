package com.digimon.demo.domain.user

import com.digimon.demo.domain.todo.Card
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = ["password"])
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        set(id) {
            field = this.id
        }

    @OneToMany
    var cards: List<Card>? = null
        set(cards) {
            field = this.cards
        }

    @NotNull
    @Column(name = "name")
     val name: String

    @NotNull
    @Column(name = "password")
     val password: String

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null
        set(createdAt) {
            field = this.createdAt
        }

    constructor(name: String, password: String) {
        this.name = name
        this.password = password
    }

}
