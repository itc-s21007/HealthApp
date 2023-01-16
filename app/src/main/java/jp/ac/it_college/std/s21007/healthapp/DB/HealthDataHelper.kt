package jp.ac.it_college.std.s21007.healthapp.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class HealthDataHelper(context: Context?) : SQLiteOpenHelper(context, DBNAME, null, VERSION){

    companion object{
        private const val DBNAME = "HealthData"
        private const val VERSION= 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            it.execSQL("CREATE TABLE DATE("+"IdDate INTEGER PRIMARY KEY, Date TEXT)")


            val data = listOf(
                mapOf("IdDate" to "1", "Date" to "１２月１５日"),
            )


            it.beginTransaction()
            try {
                val sql = it.compileStatement(
                    "INSERT INTO DATE(IdDate, Date) VALUES(?,?)"
                )
                data.forEach { it ->
                    sql.bindString(1, it["IdDate"])
                    sql.bindString(2, it["Date"])
                    sql.executeInsert()
                }
                it.setTransactionSuccessful()
            } catch (e: SQLException) {
                e.printStackTrace()
            }finally {
                it.endTransaction()
            }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let {
            it.execSQL("DROP TABLE IF EXISTS DATE")
            onCreate(it)

        }
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }
}
