package com.wealthwise.data.model

data class Career(
    val id: String,
    val title: String,
    val salary: Int,
    val description: String,
    val icon: String
)

object CareersData {
    val careers = listOf(
        Career(
            id = "teacher",
            title = "Teacher",
            salary = 45000,
            description = "Shape young minds and build the future",
            icon = "school"
        ),
        Career(
            id = "engineer",
            title = "Engineer",
            salary = 85000,
            description = "Design and build innovative solutions",
            icon = "engineering"
        ),
        Career(
            id = "nurse",
            title = "Nurse",
            salary = 55000,
            description = "Care for others and save lives",
            icon = "local_hospital"
        ),
        Career(
            id = "accountant",
            title = "Accountant",
            salary = 65000,
            description = "Manage finances and ensure accuracy",
            icon = "account_balance"
        ),
        Career(
            id = "sales_rep",
            title = "Sales Representative",
            salary = 50000,
            description = "Connect products with customers",
            icon = "trending_up"
        ),
        Career(
            id = "developer",
            title = "Software Developer",
            salary = 95000,
            description = "Code the digital future",
            icon = "computer"
        )
    )
}