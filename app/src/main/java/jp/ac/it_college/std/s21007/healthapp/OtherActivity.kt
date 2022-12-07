package jp.ac.it_college.std.s21007.healthapp

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import jp.ac.it_college.std.s21007.healthapp.databinding.ActivityOtherBinding

class OtherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}