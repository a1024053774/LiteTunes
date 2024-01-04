package com.example.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.fragment.Http.OkHttpTestActivity
import com.example.fragment.MediaRecorder.MediaRecorderActivity
import com.example.fragment.VideoPlayer.VideoPlayerActivity


class MainActivity : AppCompatActivity() {
    private var firstFragmentbutton: Button ?=null
    private var secondFragmentbutton: Button ?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container,FirstFragment()).commit()

        firstFragmentbutton = findViewById(R.id.fragmentbutton_first)
        secondFragmentbutton = findViewById(R.id.fragmentbutton_second)

        firstFragmentbutton?.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,FirstFragment())
                .commit()
        }

        secondFragmentbutton?.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,SecondFragment())
                .commit()
        }


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_first -> {
                Intent(this, FourActivity::class.java).also { startActivity(it) }
            }
            R.id.menu_second -> {
                var it = Intent()
                it.action= Intent.ACTION_VIEW
                it.data= Uri.parse("tel:119")
                startActivity(it)
            }
            R.id.menu_third -> {
                Intent(this, SecondActivity::class.java).also { startActivity(it) }
            }
            R.id.menu_four -> {
                Intent(this, FifthActivity::class.java).also { startActivity(it) }
            }
            R.id.menu_five -> {
                Intent(this, AudioPlayerActivity::class.java).also { startActivity(it) }
            }
            R.id.menu_six -> {
                Intent(this, LocalMediaPlayerActivity::class.java).also { startActivity(it) }
            }
            R.id.menu_seven -> {
                Intent(this, MediaRecorderActivity::class.java).also { startActivity(it) }
            }
            R.id.menu_eight -> {
                Intent(this, VideoPlayerActivity::class.java).also { startActivity(it) }
            }
            R.id.menu_nine -> {
                Intent(this, OkHttpTestActivity::class.java).also { startActivity(it) }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}