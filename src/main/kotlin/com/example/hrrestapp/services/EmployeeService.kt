package com.example.hrrestapp.services

import com.example.hrrestapp.domain.Employee
import com.example.hrrestapp.domain.EmployeeUpdateRequest
import com.example.hrrestapp.repository.EmployeeRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono


@Service
class EmployeeService {

    @Autowired
    lateinit var employeeDb: EmployeeRepo

    companion object {
        val nobody = Employee(id= 0, name="nobody", age=0, department="none", salary=0.0).toMono()
    }

    fun createEmployee(employee: Employee) = employeeDb.save(employee)

    fun loadEmployees(employees: List<Employee>) = employeeDb.saveAll(employees)

    fun findEmployee(id: Int) = employeeDb.findById(id)

    fun findAllEmployees(minAge: Int? = null, minSalary: Double? = null) =
            employeeDb.findAll()
                    .filter { it.age >= (minAge ?: Int.MIN_VALUE) }
                    .filter { it.salary >= (minSalary ?: Double.MIN_VALUE) }

    fun updateEmployee(id: Int, updateRequest: EmployeeUpdateRequest) =
        employeeDb.findById(id)
                .flatMap {
                    it.department = updateRequest.department ?: it.department
                    it.salary = updateRequest.salary ?: it.salary
                    employeeDb.save(it)
                }

    fun removeEmployee(id: Int) = employeeDb.deleteById(id)

    fun isNobody(employee: Mono<Employee> ): Boolean = ( employee == nobody)

}