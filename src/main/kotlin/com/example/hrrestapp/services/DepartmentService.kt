package com.example.hrrestapp.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DepartmentService {
    @Autowired
    lateinit var employeeService: EmployeeService

    fun getAllDepartments() = employeeService.findAllEmployees().map{it.department}.distinct()
}