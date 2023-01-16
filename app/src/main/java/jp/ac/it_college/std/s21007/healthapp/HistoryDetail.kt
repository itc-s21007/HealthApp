package jp.ac.it_college.std.s21007.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import jp.ac.it_college.std.s21007.healthapp.Dialog.DialogFood
import jp.ac.it_college.std.s21007.healthapp.databinding.ActivityHistoryDetailBinding

class HistoryDetail : AppCompatActivity(), DialogFood.FoodCallBack{

    private lateinit var binding: ActivityHistoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val txtName = intent.getStringExtra("history_list")
        binding.getDate.text = txtName


        binding.add.setOnClickListener {
            val dialog = DialogFood()
            dialog.show(supportFragmentManager, "dialog_basic")
        }
        binding.homeback.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        val a = 1000
        val tryining = "スクワット、腕立て、１００回ずつ"
        if (500 <= a) {
            binding.Tryining.text = tryining
        }

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onData(data: String, calorie: Int ) {
        Toast.makeText(this,"${data} データを取得", Toast.LENGTH_SHORT).show()
        Toast.makeText(this,"${calorie} データを取得", Toast.LENGTH_SHORT).show()
    }

}