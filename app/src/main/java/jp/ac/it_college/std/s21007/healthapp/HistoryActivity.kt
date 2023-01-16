package jp.ac.it_college.std.s21007.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.TextView
import jp.ac.it_college.std.s21007.healthapp.DB.HealthDataHelper
import jp.ac.it_college.std.s21007.healthapp.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    private val helper = HealthDataHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // MainActivity から来た値を変数day に代入
        val day = intent.getStringExtra("data_list")

        // 日付のテーブルを検索して、listView に表示する
        val tabledate = arrayOf("Date")
        val list = mutableListOf<String>()
        val selectDate = helper.readableDatabase.let { it ->
            it.query(
                "DATE", tabledate , null,
                null, null, null, null
            ).let {
                while (it.moveToNext()){
                    list += listOf( it.getString(it.getColumnIndexOrThrow("Date"))).sorted()
                }
            }
        }

        // DATEテーブルから値を取得して、List表示する

       val data = list.toTypedArray()


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