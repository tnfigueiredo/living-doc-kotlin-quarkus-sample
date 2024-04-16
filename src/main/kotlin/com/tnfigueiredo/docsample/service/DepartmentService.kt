package com.tnfigueiredo.docsample.service

import com.tnfigueiredo.docsample.model.Department
import com.tnfigueiredo.docsample.model.User
import com.tnfigueiredo.docsample.repository.DepartmentRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class DepartmentService {

    @Inject
    lateinit var departmentRepository: DepartmentRepository

    @Transactional
    fun save(generalUser: User.GeneralUser, department: Department): Department? {
        departmentRepository.persistAndFlush(department)
        return departmentRepository.findById(department.id!!)
    }


    fun delete(department: Department): Unit = departmentRepository.delete(department)
}