package com.example.male_impact

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context,"Servicios.db",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE CITAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, APELLIDO TEXT, EDAD TEXT, CORREO TEXT, TELEFONO TEXT, FECHA TEXT)")
        p0?.execSQL("CREATE TABLE citasJ (id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Apellido TEXT, Edad TEXT, Correo TEXT, Telefono TEXT, Motivos TEXT)")
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS CITAS")
        p0?.execSQL("DROP TABLE IF EXISTS citasJ")
    }
    fun anadirDatos(nombre :String, apellido:String,edad:String, correo:String,telefono:String,fecha:String){
        val datos = ContentValues()
        datos.put("NOMBRE ", nombre)
        datos.put("APELLIDO", apellido)
        datos.put("EDAD", edad)
        datos.put("CORREO", correo)
        datos.put("TELEFONO", telefono)
        datos.put("FECHA", fecha)
        val db = this.writableDatabase
        db.insert("CITAS",null,datos)
    }
    fun anadirDatosJ(Nombre: String, Apellido: String, Edad: String, Correo: String, Telefono: String, Motivos: String){
        val datos = ContentValues ()
        datos.put("Nombre",Nombre)
        datos.put("Apellido", Apellido)
        datos.put("Edad", Edad)
        datos.put("Correo", Correo)
        datos.put("Telefono", Telefono)
        datos.put("Motivos", Motivos)

        val db = this.writableDatabase
        db.insert("citasJ",null,datos)
    }
}