package com.digimon.demo.web.router

import com.digimon.demo.domain.todo.Card
import com.digimon.demo.web.handler.TodoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router


@Configuration
class TodoRouter(private val handler: TodoHandler) {

    @Bean
    fun routerFunction() = nest(path("/todo-list"),
            router {
                listOf(
                        GET("/", ::all),
                        GET("/{id}", ::byId)
                )
            })

    fun all(req: ServerRequest) = ServerResponse.ok()
            .body<Card>(handler.getAll())

    fun byId(req: ServerRequest) = ServerResponse.ok()
            .body<Card>(handler.getById(req.pathVariable("id").toLong()))

}
