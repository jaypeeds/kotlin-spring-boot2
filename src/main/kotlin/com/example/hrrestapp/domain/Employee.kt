package com.example.hrrestapp.domain

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table
data class Employee(
        @PrimaryKey val id: Int,
        val name: String,
        val age: Int,
        var department: String,
        var salary: Double
)