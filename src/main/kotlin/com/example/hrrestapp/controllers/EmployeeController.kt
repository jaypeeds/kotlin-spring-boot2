package com.example.hrrestapp.controllers

import com.example.hrrestapp.domain.Employee
import com.example.hrrestapp.domain.EmployeeUpdateRequest
import com.example.hrrestapp.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class EmployeeController {
    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping("/employees")
    fun getAllEmployees(
            @RequestParam("minAge", required = false) minAge: Int?,
            @RequestParam("minSalary", required = false) minSalary: Double?) =
            employeeService.findAllEmployees(minAge, minSalary)

    @GetMapping("/employees/{id}")
    fun getEmployee(@PathVariable("id") id: Int) = employeeService.findEmployee(id)

    @PostMapping("/employees")
    fun createEmployee(@RequestBody employee: Employee) = employeeService.createEmployee(employee)
            .map { _ -> ResponseEntity.status(HttpStatus.OK).build<String>() }

    @PostMapping("/employees/bulkload")
    fun loadEmployees(@RequestBody employees: List<Employee>) = employeeService.loadEmployees(employees)
            .map { _ -> ResponseEntity.status(HttpStatus.OK).build<String>() }


    @PutMapping("/employees/{id}")
    fun updateEmployee(
            @PathVariable("id") id: Int,
            @RequestBody updateRequest: EmployeeUpdateRequest) = employeeService.updateEmployee(id, updateRequest)
            .map{ _ -> ResponseEntity.status(HttpStatus.OK).build<String>() }


    @DeleteMapping("/employees/{id}")
    fun removeEmployee(@PathVariable("id") id: Int) = employeeService.removeEmployee(id)

}