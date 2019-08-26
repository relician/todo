package com.digimon.demo.web.handler

import com.digimon.demo.domain.todo.CardRepository
import org.springframework.stereotype.Component


@Component
class TodoHandler(private val repo: CardRepository) {

    fun getAll() = repo.findAll()

    fun getById(id: Long) = repo.findById(id)
}