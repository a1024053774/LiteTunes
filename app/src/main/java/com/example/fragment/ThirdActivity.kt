package com.example.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


val array = arrayOf("Blank Space","传 奇","City Of Stars","Cruel Summer","怪 咖","讲 不 出 再 见","开 始 懂 了","Mystery Of Love","浅 的 堆 叠")
val array2 = arrayOf("Taylor Swift","李 健","Ryan Gosling","Taylor Swift","薛 之 谦","谭 咏 麟","孙 燕 姿","Sufjan Stevens","房 东 的 猫")
var icons= intArrayOf(R.drawable.music_blank_space, R.drawable.music_chuanqi,
                        R.drawable.music_city_of_stars,R.drawable.music_cruel_summer,
                         R.drawable.music_guaika,R.drawable.music_jiangbuchuzaijian,
                            R.drawable.music_kaishidongle,R.drawable.music_mystery_of_love,R.drawable.music_qiandeduidie)



class ThirdActivity: AppCompatActivity(),View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

//        val button = findViewById<Button>(R.id.ComplexDialog)
//        button.setOnClickListener(this)

        val adapter = EfficientAdapter(
            this, R.layout.listview_item, array,array2, icons
        )

        val listview = findViewById<ListView>(R.id.ListView01)
        listview.adapter = adapter

        listview.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
             {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               println("选择了选项："+ array[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("未选择")
            }

        }

        listview.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    val intent = Intent(this, com.example.fragment.MusicActivity.BlankSpaceActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    val intent = Intent(this, com.example.fragment.MusicActivity.ChuanqiActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this, com.example.fragment.MusicActivity.CityOfStarsActivity::class.java)
                    startActivity(intent)
                }
                3 -> {
                    val intent = Intent(this, com.example.fragment.MusicActivity.CruelSummerActivity::class.java)
                    startActivity(intent)
                }
                4 -> {
                    val intent = Intent(this, com.example.fragment.MusicActivity.GuaikaActivity::class.java)
                    startActivity(intent)
                }
                5 -> {
                    val intent = Intent(this, com.example.fragment.MusicActivity.JiangbuchuzaijianActivity::class.java)
                    startActivity(intent)
                }
                6 -> {
                    val intent = Intent(this, com.example.fragment.MusicActivity.KaishidongleActivity::class.java)
                    startActivity(intent)
                }
                7 -> {
                    val intent = Intent(this, com.example.fragment.MusicActivity.MysteryofloveActivity::class.java)
                    startActivity(intent)
                }
                8 -> {
                    val intent = Intent(this, com.example.fragment.MusicActivity.QiandeduidieActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


//    @SuppressLint("MissingInflatedId")
//    override fun onClick(v: View?) {
//        val factory = LayoutInflater.from(this)
//        val textEntryView: View = factory.inflate(R.layout.layoutdialog, null)}
//
//        val dialog = AlertDialog.Builder(this)
//            .setTitle("登录")
//            .setView(textEntryView)
//            .setPositiveButton(
//                "登录"
//            ) {
//                dialog, which ->
//
//                val user = textEntryView
//                    .findViewById<EditText>(R.id.DialogUsername)
//                val pass = textEntryView
//                    .findViewById<EditText>(R.id.Dialogpassword)
//                makeText(this,"用户名: ${user.text}  密码: ${pass.text}", LENGTH_SHORT).show()
//            }
//
//            .setNegativeButton(
//                "取消"
//            ) {
//                dialog, which ->
//                makeText(this, "你点击了取消按钮", LENGTH_SHORT).show()
//            }
//            .create()
//        dialog.show()
//    }


}

class EfficientAdapter (
    context:Context, resource: Int, array: Array<String>,array2: Array<String>, icons: IntArray
) : BaseAdapter() {

    private val myResource = resource
    private val icons = icons
    private val stringArray = array
    private val stringArray2 = array2
    private val mContext = context
    override fun getCount(): Int {
        return stringArray.size
    }
    override fun getItem(position: Int): Any {
        return stringArray[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertview = convertView
        var holder :MyHolder

        if(convertview == null) {
            convertview = View.inflate(mContext, myResource, null)
            val textView: TextView = convertview.findViewById(R.id.textview)
            val textView1: TextView = convertview.findViewById(R.id.textview1)
            val imageView: ImageView = convertview.findViewById(R.id.icon)

            holder = MyHolder(textView,textView1,imageView)
            convertview.tag=holder}
        else {
            holder = convertview.tag as MyHolder
        }
            holder.textView.text = stringArray[position]
            holder.textView1.text = stringArray2[position]
            holder.imageView.setImageResource(icons[position])
            return convertview

    }
    data class MyHolder(
        val textView: TextView,
        val textView1 : TextView,
        val imageView: ImageView
    )
}
