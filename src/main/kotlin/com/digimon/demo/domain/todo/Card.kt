package com.digimon.demo.domain.todo

import com.digimon.demo.domain.user.User
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "cards")
class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        set(id) {
            field = this.id
        }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
     var user: User? = null

    @Lob
    @NotNull
    @Column(name = "content")
     var content: String? = null

    @NotNull
    @Column(name = "ordinal")
     var ordinal: Int = 0

    @Column(name = "modified_at")
    var modifiedAt: LocalDateTime? = null
        set(modifiedAt) {
            field = this.modifiedAt
        }

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null
        set(createdAt) {
            field = this.createdAt
        }

    constructor(content: String, ordinal: Int) {
        this.content = content
        this.ordinal = ordinal
    }

    constructor(user: User, content: String, ordinal: Int) {
        this.user = user
        this.content = content
        this.ordinal = ordinal
    }
}
