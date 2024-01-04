package com.example.fragment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

const val DATABASE_NAME = "MYNOTE.sqlite"

const val TABLE_NAME = "NOTE"

const val DATABASE_VERSION = 1

const val TABLE_FIELD_DATE = "name"

const val TABLE_FIELD_CONTENT = "password"

const val TAG = "MyDb"

class DBHelper(context: Context?) :
        SQLiteOpenHelper(
            context, DATABASE_NAME, null, DATABASE_VERSION ) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """CREATE TABLE IF NOT EXISTS Note(
           _id TEXT PRIMARY KEY,name TEXT, password TEXT)"""
        Log.i(TAG, sql)

        try {
            db?.execSQL(sql)

            db?.execSQL(
                "insert into Note (_id,name,password) values('1','张三','123')"
            )
            db?.execSQL(
                "insert into Note (_id,name,password) values('2','李四','456')"
            )
        } catch (e: Exception){

            Log.e(TAG, "数据库初始化异常")
            e.printStackTrace()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Note")
        onCreate(db)
    }

}
