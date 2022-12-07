package jp.ac.it_college.std.s21007.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import jp.ac.it_college.std.s21007.healthapp.databinding.ActivityHistoryDetailBinding

class HistoryDetail : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtName = intent.getStringExtra("history_list")
        binding.getDate.text = txtName

    }
}