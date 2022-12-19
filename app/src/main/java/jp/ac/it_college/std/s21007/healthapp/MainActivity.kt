package jp.ac.it_college.std.s21007.healthapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import jp.ac.it_college.std.s21007.healthapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), DialogRegister.DataCallBack {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helper = HealthDataHelper(this)

        helper.writableDatabase.use { _ ->
            Toast.makeText(this, "接続しました", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val dialog = DialogRegister()


        when(item.itemId) {
            R.id.history -> startActivity(Intent(this, HistoryActivity::class.java))
            R.id.register -> dialog.show(supportFragmentManager, "dialog_basic")
        }

        Toast.makeText(
            this, item.title, Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onData(data: String) {
//        Toast.makeText(this, data, Toast.LENGTH_SHORT) .show()
        startActivity(Intent(this, HistoryActivity::class.java).apply {
            putExtra("data_list", data)
        })
    }
}