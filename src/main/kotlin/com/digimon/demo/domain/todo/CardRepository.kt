package com.digimon.demo.domain.todo

import com.digimon.demo.domain.todo.Card

import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Long>
