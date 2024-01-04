package com.example.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val list = findAll(this)
        bindData(list)

    }
        @SuppressLint("Range")
        fun findAll(context: Context): List<Map<String, String>> {
            val dbHelper = DBHelper(context)
            val db = dbHelper.readableDatabase

            val colums = arrayOf(TABLE_FIELD_DATE, TABLE_FIELD_CONTENT)

            val cursor = db.query(
                TABLE_NAME,
                colums,
                null,
                null,
                null,
                null,
                "$TABLE_FIELD_DATE"
            )

            val data = mutableListOf<Map<String, String>>()
            while (cursor.moveToNext()) {
                val row = mutableMapOf<String, String>()
                val date = cursor.getString(cursor.getColumnIndex(TABLE_FIELD_DATE))
                val content = cursor.getString(cursor.getColumnIndex(TABLE_FIELD_CONTENT))

                row[TABLE_FIELD_DATE] = date
                row[TABLE_FIELD_CONTENT] = content

                data.add(row)
            }

            return data
        }


        fun bindData(listData: List<Map<String, String>>?) {
            val to = intArrayOf(R.id.mydate, R.id.mycontent)

            val from = arrayOf(TABLE_FIELD_DATE, TABLE_FIELD_CONTENT)

            val simpleAdapter = SimpleAdapter(this, listData, R.layout.listview_item_two, from, to)

            val database_shown = findViewById<ListView>(R.id.Database_Shown)
            database_shown?.adapter = simpleAdapter

        }
    }
