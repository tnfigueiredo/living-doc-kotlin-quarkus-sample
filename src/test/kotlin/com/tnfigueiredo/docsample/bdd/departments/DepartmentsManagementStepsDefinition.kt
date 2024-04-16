package com.tnfigueiredo.docsample.app.bdd.departments

import com.tnfigueiredo.docsample.model.Department
import com.tnfigueiredo.docsample.model.User.GeneralUser
import com.tnfigueiredo.docsample.model.UserProfile
import com.tnfigueiredo.docsample.repository.DepartmentRepository
import com.tnfigueiredo.docsample.service.DepartmentService
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.hibernate.exception.ConstraintViolationException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue


class DepartmentsManagementStepsDefinition {

    @Inject
    private lateinit var departmentService: DepartmentService

    @Inject
    private lateinit var departmentRepository: DepartmentRepository

    private var oldDepartmentEntity: Department? = null
    private var departmentEntity: Department? = null
    private lateinit var creator: GeneralUser
    private lateinit var exception: Throwable

    @Given("A department administrator needs a new department")
    fun givenDepartmentAdministratorNeedsNewDepartment(){
        creator = GeneralUser(1, "dptoAdmin", UserProfile.DEPARTMENT_ADMINISTRATOR)
    }

    @Given("A department administrator needs a new department subject")
    fun givenDepartmentAdministratorNeedsNewDepartmentSubject(){
        creator = GeneralUser(1, "dptoAdmin", UserProfile.DEPARTMENT_ADMINISTRATOR)
    }

    @And("There is an existing department already registered: {string}, {string}")
    @Transactional
    fun givenOldDepartmentAlreadyRegistered(departmentName: String, departmentAbbreviation: String){
        departmentRepository.delete("abbreviation", "OLDDPTO")
        oldDepartmentEntity = departmentService.save(creator, Department(name = departmentName, abbreviation = departmentAbbreviation))
    }

    @And("There is an existing department subject already registered: {string}, {string}")
    fun givenOldDepartmentSubjectAlreadyRegistered(departmentSubjectName: String, departmentSubjectAcronym: String){
        //TODO
    }

    @When("department data informed is not duplicated: {string}, {string}")
    fun whenDepartmentInformationIsNotDuplicated(departmentName: String, departmentAbbreviation: String){
        departmentEntity = departmentService.save(creator, Department(name = departmentName, abbreviation = departmentAbbreviation))
    }

    @When("department information is duplicated: {string}, {string}")
    fun whenDepartmentInformationIsDuplicated(departmentName: String, departmentAbbreviation: String){
        try {
            departmentService.save(creator, Department(name =  departmentName, abbreviation = departmentAbbreviation, creatorId = creator.id))
        } catch (e: ConstraintViolationException){
            exception = e
        }
    }

    @When("department subject data informed is not duplicated: {string}, {string}, {string}")
    fun whenDepartmentSubjectInformationIsNotDuplicated(departmentAbbreviation: String, departmentSubjectName: String, departmentSubjectAcronym: String){
        //TODO
    }

    @When("department subject acronym informed is duplicated: {string}, {string}, {string}")
    fun whenDepartmentSubjectInformationIsDuplicated(departmentAbbreviation: String, departmentSubjectName: String, departmentSubjectAcronym: String){
        //TODO
    }

    @When("department subject acronym in a department is requested to be deactivated: {string}, {string}")
    fun whenDepartmentSubjectInformationIsDeactivated(departmentAbbreviation: String, departmentSubjectAcronym: String){
        //TODO
    }

    @Then("a department is created successfully")
    fun thenDepartmentCreated(){
        assertEquals(departmentEntity?.name, "New Department")
        assertEquals(departmentEntity?.abbreviation,  "NDPTO")
        assertEquals(departmentEntity?.creatorId, creator.id)
    }

    @Then("department creation operation fails with duplicated information informed")
    fun thenDepartmentFailsToBeCreated(){
        assertTrue(exception is ConstraintViolationException)
    }

    @Then("a department subject is created successfully")
    fun thenDepartmentSubjectCreated(){
        //TODO
    }

    @Then("a department subject creation fails")
    fun thenDepartmentSubjectCreationFails(){
        //TODO
    }

    @Then("a department subject change its status to deactivated")
    fun thenDepartmentSubjectIsDeactivated(){
        //TODO
    }
}
