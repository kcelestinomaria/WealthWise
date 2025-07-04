package com.wealthwise.data.database

import androidx.room.TypeConverter
import com.wealthwise.data.model.Role
import com.wealthwise.data.model.TransactionType

class Converters {
    
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