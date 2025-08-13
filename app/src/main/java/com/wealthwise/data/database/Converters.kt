package com.wealthwise.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.wealthwise.data.model.Role
import com.wealthwise.data.model.TransactionType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wealthwise.data.model.Goal
import com.wealthwise.data.model.GoalCategory
import java.util.*

@ProvidedTypeConverter
class Converters {
    private val gson = Gson()

    // Date converters
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // GoalCategory converters
    @TypeConverter
    fun fromGoalCategory(category: GoalCategory): String {
        return category.name
    }

    @TypeConverter
    fun toGoalCategory(categoryString: String): GoalCategory {
        return GoalCategory.valueOf(categoryString)
    }

    // List<String> converters
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    // List<Goal> converters
    @TypeConverter
    fun fromGoalList(goals: List<Goal>): String {
        return gson.toJson(goals)
    }

    @TypeConverter
    fun toGoalList(goalsString: String): List<Goal> {
        val listType = object : TypeToken<List<Goal>>() {}.type
        return gson.fromJson(goalsString, listType)
    }

    // Map<String, Any> converters for flexible data storage
    @TypeConverter
    fun fromStringMap(value: Map<String, Any>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringMap(value: String): Map<String, Any> {
        val mapType = object : TypeToken<Map<String, Any>>() {}.type
        return gson.fromJson(value, mapType)
    }
    
    @TypeConverter
    fun fromRole(role: Role): String {
        return role.name
    }
    
    @TypeConverter
    fun toRole(roleName: String): Role {
        return Role.valueOf(roleName)
    }
    
    @TypeConverter
    fun fromTransactionType(type: TransactionType): String {
        return type.name
    }
    
    @TypeConverter
    fun toTransactionType(typeName: String): TransactionType {
        return TransactionType.valueOf(typeName)
    }
} 