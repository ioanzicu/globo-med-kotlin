package android.example.com.globomed

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // called when the database is created for the first time
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(GloboMedDBContract.EmployeeEntry.SQL_CREATE_ENTRIES)
    }

    // called when the schema or database version changed
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(GloboMedDBContract.EmployeeEntry.SQL_DROP_TABLE)
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "globomed.db"
        const val DATABASE_VERSION = 3
    }
}