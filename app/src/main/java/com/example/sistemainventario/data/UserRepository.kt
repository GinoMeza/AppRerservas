package com.example.sistemainventario.data

import android.content.Context
import com.example.sistemainventario.model.User

class UserRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun login(email: String, password: String): User? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_USUARIOS,
            null,
            "${DatabaseHelper.COLUMN_USER_EMAIL} = ? AND ${DatabaseHelper.COLUMN_USER_PASSWORD} = ?",
            arrayOf(email, password),
            null, null, null
        )

        var user: User? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ID))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_NAME))
            val userEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_EMAIL))
            val telefono = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_PHONE))
            user = User(id, nombre, userEmail, telefono)
        }
        cursor.close()
        return user
    }
}
