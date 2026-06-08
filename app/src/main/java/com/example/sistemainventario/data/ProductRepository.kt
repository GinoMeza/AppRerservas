package com.example.sistemainventario.data

import android.content.ContentValues
import android.content.Context
import com.example.sistemainventario.model.Product

class ProductRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun addProduct(product: Product): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NOMBRE, product.nombre)
            put(DatabaseHelper.COLUMN_CANTIDAD, product.cantidad)
            put(DatabaseHelper.COLUMN_PRECIO, product.precio)
        }
        return db.insert(DatabaseHelper.TABLE_PRODUCTOS, null, values)
    }

    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_PRODUCTOS,
            null, null, null, null, null, null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
                val nombre = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOMBRE))
                val cantidad = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_CANTIDAD))
                val precio = getDouble(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRECIO))
                productList.add(Product(id, nombre, cantidad, precio))
            }
        }
        cursor.close()
        return productList
    }

    fun deleteProduct(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete(DatabaseHelper.TABLE_PRODUCTOS, "${DatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
    }
}
