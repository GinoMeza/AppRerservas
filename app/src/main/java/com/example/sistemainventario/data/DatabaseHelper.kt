package com.example.sistemainventario.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Inventario.db"
        private const val DATABASE_VERSION = 1

        // Tabla Productos
        const val TABLE_PRODUCTOS = "productos"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_CANTIDAD = "cantidad"
        const val COLUMN_PRECIO = "precio"

        // Tabla Usuarios
        const val TABLE_USUARIOS = "usuarios"
        const val COLUMN_USER_ID = "id"
        const val COLUMN_USER_NAME = "nombre"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_PASSWORD = "password"
        const val COLUMN_USER_PHONE = "telefono"

        private const val CREATE_TABLE_PRODUCTOS = """
            CREATE TABLE $TABLE_PRODUCTOS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_CANTIDAD INTEGER DEFAULT 0,
                $COLUMN_PRECIO REAL DEFAULT 0.0
            )
        """

        private const val CREATE_TABLE_USUARIOS = """
            CREATE TABLE $TABLE_USUARIOS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_NAME TEXT NOT NULL,
                $COLUMN_USER_EMAIL TEXT UNIQUE NOT NULL,
                $COLUMN_USER_PASSWORD TEXT NOT NULL,
                $COLUMN_USER_PHONE TEXT
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_PRODUCTOS)
        db?.execSQL(CREATE_TABLE_USUARIOS)
        
        // Insertar admin por defecto
        db?.execSQL("""
            INSERT INTO $TABLE_USUARIOS ($COLUMN_USER_NAME, $COLUMN_USER_EMAIL, $COLUMN_USER_PASSWORD, $COLUMN_USER_PHONE)
            VALUES ('Marco Mezco B..', 'admin@gmail.com', 'admin123', '77203282')
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTOS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }
}
