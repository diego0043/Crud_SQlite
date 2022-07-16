package com.example.crud_sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "student.db"
        private const val TBL_STUDENT = "tbl_student"
        private const val ID = "id"
        private const val NAME = "name"
        private const val CARNET = "carnet"
        private const val CARRERA = "carrera"
        private const val ANIO = "anio"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent = (" CREATE TABLE " + TBL_STUDENT + "("
                + ID + "INTEGER PRIMARY KEY," + NAME + "TEXT," + CARNET + "TEXT," +
                CARRERA + "TEXT," + ANIO + "TEXT" + ")")
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE TBL_STUDEN$TBL_STUDENT")
        onCreate(db)
    }

    //Method for insert student
    fun insertStudent(std: StudentModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(CARNET, std.carnet)
        contentValues.put(CARRERA, std.carrera)
        contentValues.put(ANIO, std.anio)

        val success = db.insert(TBL_STUDENT, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllStudent(): ArrayList<StudentModel>{
        val stdList: ArrayList<StudentModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_STUDENT"
        val db = this.readableDatabase

        val cursor: Cursor?
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var carnet: String
        var carrera: String
        var anio: String


        if(cursor.moveToFirst()){
            do {

                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                carnet = cursor.getString(cursor.getColumnIndex("carnet"))
                carrera = cursor.getString(cursor.getColumnIndex("carrera"))
                anio = cursor.getString(cursor.getColumnIndex("anio"))

                val std = StudentModel(id = id, name = name, carnet = carnet, carrera = carrera, anio = anio)
                stdList.add(std)
            }while (cursor.moveToNext())
        }

        return stdList
    }
}