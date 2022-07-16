package com.example.crud_sqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edCarnet: EditText
    private lateinit var edCarrera: EditText
    private lateinit var edAnio: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button

    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        sqLiteHelper = SQLiteHelper(this)
        btnAdd.setOnClickListener{ addStudent()}
    }

    private fun addStudent(){
        val name = edName.text.toString()
        val carnet = edCarnet.text.toString()
        val carrera = edCarrera.text.toString()
        val anio = edAnio.text.toString()

        if(name.isEmpty() || carnet.isEmpty() || carrera.isEmpty() || anio.isEmpty()){
            Toast.makeText(this, "Please enter required fields", Toast.LENGTH_SHORT).show()
        }else{
            val std = StudentModel(name = name, carnet = carnet, carrera = carrera, anio = anio )
            val status = sqLiteHelper.insertStudent(std)
            //check insert success or not success
            if( status > -1){
                Toast.makeText(this, "Student Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
            }
        }

    }

    private fun clearEditText() {
        TODO("Not yet implemented")
    }

    private fun initView(){
        edName = findViewById(R.id.edName)
        edCarnet = findViewById(R.id.edCarnet)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
    }
}