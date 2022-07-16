package com.example.crud_sqlite

import java.util.*

data class StudentModel(
    var id: Int = getAutoId(),
    var name: String = "",
    var carnet: String = "",
    var carrera: String = "",
    var anio: String = "",
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}

