package com.tnfigueiredo.docsample.repository

import com.tnfigueiredo.docsample.model.Department
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class DepartmentRepository : PanacheRepository<Department> {

    fun findByName(name: String): Department? = find("name", name).firstResult()

    fun findByAbbreviation(abbreviation: String): Department? = find("abbreviation", abbreviation).firstResult()

}