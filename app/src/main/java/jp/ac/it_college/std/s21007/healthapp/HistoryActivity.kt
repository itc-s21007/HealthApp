package jp.ac.it_college.std.s21007.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import jp.ac.it_college.std.s21007.healthapp.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = mutableListOf("12月7日","N月N日","N月N日","N月N日","N月N日",)

        // ListView にdataを表示
        binding.historyList.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, data
        )


        // ListView をタップ＆長押ししたときの処理
        binding.historyList.setOnItemClickListener { parent, view, position, id ->
            val itemTextView : TextView = view.findViewById(android.R.id.text1)
            startActivity(Intent(this, HistoryDetail::class.java).apply {
                putExtra("history_list", itemTextView.text.toString() )
            })
        }
    }

    // 戻るボタンの追加
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}