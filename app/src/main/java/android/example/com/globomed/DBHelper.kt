package android.example.com.globomed

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // called when the database is created for the first time
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(GloboMedDBContract.SQL_CREATE_ENTRIES)
    }

    // called when the schema or database version changed
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(GloboMedDBContract.SQL_DROP_TABLE)
        onCreate(db)
    }

    // called when we need to downgrade the downgrade the database, when old version is newer than the new database
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    // triggered when database schema was created, upgraded or downgraded
    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    companion object {
        const val DATABASE_NAME = "globomed.db"
        const val DATABASE_VERSION = 1
    }
}