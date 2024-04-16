package com.tnfigueiredo.docsample.model

import jakarta.persistence.*

@Entity(name = "DEPARTMENT")
data class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    @Column(unique = true)
    val name: String = "",
    @Column(unique = true)
    val abbreviation: String = "",
    @Column
    val creatorId: Int? = 1
)

enum class UserProfile{
    DEPARTMENT_ADMINISTRATOR,
    DEPARTMENT_EMPLOYEE,
    STUDENT
}

sealed class User(
    open val id: Int?,
    open val username: String,
    open val profile: UserProfile
){
    data class GeneralUser(
        override val id: Int?,
        override val username: String,
        override val profile: UserProfile
    ): User(id, username, profile)

    data class Student(
        override val id: Int?,
        override val username: String
    ): User(id, username, UserProfile.STUDENT)
}