package com.example.fragment.Http


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


//Web服务网址
const val URL_STR = "http://10.130.179.63:8890/user"

class OkHttpTestActivity : AppCompatActivity()  {
    private var mTextViewText: TextView? = null



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttptest)
        mTextViewText = findViewById(R.id.textView_text)
        val mButtonGO = findViewById<Button>(R.id.button_go)

        mButtonGO.setOnClickListener {
            // IO调度器
            val bgDispatcher: CoroutineDispatcher = Dispatchers.IO
            // 启动协程
            GlobalScope.launch(bgDispatcher) {  // 后台线程调度器
                //调用Notes Web服务
                requestNotes()
            }
        }
    }

    //调用Notes Web服务
    private fun requestNotes() {
        val reqURL = URL(URL_STR)
        //打开网络通信输入流
        reqURL.openStream().use { input ->
            //通过is创建InputStreamReader对象
            val isr = InputStreamReader(input, "utf-8")
            //通过isr创建BufferedReader对象
            val br = BufferedReader(isr)
            val resultString = br.readText()




            val uiDispatcher = Dispatchers.Main
            // 启动协程
            GlobalScope.launch(uiDispatcher) { // 主线程调度器

                mTextViewText?.text = resultString
            }
        }
    }
}

//data class Equipment(
//    val id: Int,
//    val name: String,
//    val password: String,
//    val equstatu: String,
//    val equid: String,
//    val need: String,
//    val address: String,
//    val createTime: List<Int>
//)
//
//class EquipmentAdapter(private val context: Context, private val dataSource: ArrayList<Equipment>) : BaseAdapter() {
//
//    @SuppressLint("ServiceCast")
//    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//
//    override fun getCount(): Int {
//        return dataSource.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return dataSource[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    @SuppressLint("ViewHolder", "InflateParams")
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val rowView = inflater.inflate(R.layout.list_item_equipment, null, true)
//        val nameTextView = rowView.findViewById(R.id.equipment_name) as TextView
//        val statusTextView = rowView.findViewById(R.id.equipment_status) as TextView
//
//        val equipment = getItem(position) as Equipment
//        nameTextView.text = equipment.name
//        statusTextView.text = equipment.equstatu
//
//        return rowView
//    }
//}