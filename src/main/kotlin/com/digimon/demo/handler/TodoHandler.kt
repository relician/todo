package com.digimon.demo.handler

import com.digimon.demo.domain.todo.Todo
import com.digimon.demo.domain.todo.TodoRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*


@Component
class TodoHandler(private val repo: TodoRepository) {

    fun getAll(req: ServerRequest) = ServerResponse.ok()
            .body<List<Todo>>(Flux.just(repo.findAll()))

    fun getById(req: ServerRequest): Mono<ServerResponse> {
        val id = req.pathVariable("id").toLong()
        return ServerResponse.ok().body<Todo>(Mono.justOrEmpty(repo.findById(id)))
    }

    fun save(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(req.bodyToMono(Todo::class.java)
                        .switchIfEmpty(Mono.empty())
                        .filter(Objects::nonNull)
                        .flatMap { todo ->
                            Mono.fromCallable {
                                repo.save(todo)
                            }.then(Mono.just(todo))
                        }
                )
    }

    fun done(req: ServerRequest): Mono<ServerResponse> {
        val id = req.pathVariable("id").toLong()

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.justOrEmpty(repo.findById(id))
                        .switchIfEmpty(Mono.empty())
                        .filter(Objects::nonNull)
                        .flatMap { todo ->
                            Mono.fromCallable {
                                todo.done = true
                                todo.modifiedAt = LocalDateTime.now()
                                repo.save(todo)
                            }.then(Mono.just(todo))
                        }
                )
    }

    fun delete(req: ServerRequest): Mono<ServerResponse> {
        val id = req.pathVariable("id").toLong()
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.justOrEmpty(repo.findById(id))
                        .switchIfEmpty(Mono.empty())
                        .filter(Objects::nonNull)
                        .flatMap { todo ->
                            Mono.fromCallable {
                                repo.delete(todo)
                            }.then(Mono.just(todo))
                        }
                )
    }


}