package com.example.hrrestapp.controllers

import com.example.hrrestapp.services.DepartmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DepartmentController {
    @Autowired
    lateinit var departmentService: DepartmentService

    @GetMapping("/departments")
    fun getAllDepartments() = departmentService.getAllDepartments().toStream()
}