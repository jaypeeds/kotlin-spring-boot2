package com.example.hrrestapp.repository

import com.example.hrrestapp.domain.Employee
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepo: ReactiveCassandraRepository<Employee, Int>