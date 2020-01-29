package android.example.com.globomed

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.example.com.globomed.GloboMedDBContract.EmployeeEntry
import android.util.Log

object DataManager {

    fun fetchAllEmployees(databaseHelper: DBHelper): ArrayList<Employee> {
        val employees = ArrayList<Employee>()

        val db = databaseHelper.readableDatabase

        val columns = arrayOf(
            EmployeeEntry.COLUMN_ID,
            EmployeeEntry.COLUMN_NAME,
            EmployeeEntry.COLUMN_DOB,
            EmployeeEntry.COLUMN_DESIGNATION
        )

        var cursor = db.query(
            EmployeeEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        val idPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_ID)
        val namePos = cursor.getColumnIndex(EmployeeEntry.COLUMN_NAME)
        val dobPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_DOB)
        val designationPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_DESIGNATION)

        while (cursor.moveToNext()) {

            val id = cursor.getString(idPos)
            val name = cursor.getString(namePos)
            val dob = cursor.getLong(dobPos)
            val designation = cursor.getString(designationPos)

            employees.add(Employee(id, name, dob, designation))
        }

        cursor.close()
        return employees
    }

    fun fetchEmployee(databaseHelper: DBHelper, empId: String): Employee? {

        val db = databaseHelper.readableDatabase
        var employee: Employee? = null

        val columns = arrayOf(
            EmployeeEntry.COLUMN_NAME,
            EmployeeEntry.COLUMN_DOB,
            EmployeeEntry.COLUMN_DESIGNATION
        )

        val selection = EmployeeEntry.COLUMN_ID + " LIKE ? "

        val selectionArgs = arrayOf(empId)

        val cursor: Cursor = db.query(
            EmployeeEntry.TABLE_NAME,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val namePos = cursor.getColumnIndex(EmployeeEntry.COLUMN_NAME)
        val dobPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_DOB)
        val designationPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_DESIGNATION)

        while (cursor.moveToNext()) {

            val name = cursor.getString(namePos)
            val dob = cursor.getLong(dobPos)
            val designation = cursor.getString(designationPos)

            employee = Employee(empId, name, dob, designation)
        }

        cursor.close()
        return employee
    }

    fun updateEmployee(databaseHelper: DBHelper, employee: Employee) {

        var db = databaseHelper.writableDatabase

        val values = ContentValues()
        values.put(EmployeeEntry.COLUMN_NAME, employee.name)
        values.put(EmployeeEntry.COLUMN_DESIGNATION, employee.designation)
        values.put(EmployeeEntry.COLUMN_DOB, employee.dob)

        val selection = EmployeeEntry.COLUMN_ID + " LIKE ? "

        val selectionArgs = arrayOf(employee.id)

        db.update(EmployeeEntry.TABLE_NAME, values, selection, selectionArgs)
    }

    fun deleteEmployee(databaseHelper: DBHelper, empId: String): Int {

        val db = databaseHelper.writableDatabase

        val selection = EmployeeEntry.COLUMN_ID + " LIKE ? "

        val selectionArgs = arrayOf(empId)

        return db.delete(EmployeeEntry.TABLE_NAME, selection, selectionArgs)
    }

    fun deleteAllEmployee(dbHelper: DBHelper): Int {

        val db = dbHelper.writableDatabase
        return db.delete(EmployeeEntry.TABLE_NAME, "1", null)
    }
}