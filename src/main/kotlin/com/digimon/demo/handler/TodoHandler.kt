package com.digimon.demo.handler

import com.digimon.demo.domain.todo.TodoRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Component
class TodoHandler(private val repo: TodoRepository) {

    fun getAll() = Flux.just(repo.findAll())

    fun getById(id: Long) = Mono.justOrEmpty(repo.findById(id))
}