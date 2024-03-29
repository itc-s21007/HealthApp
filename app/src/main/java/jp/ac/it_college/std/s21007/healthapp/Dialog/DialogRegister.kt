package jp.ac.it_college.std.s21007.healthapp.Dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import jp.ac.it_college.std.s21007.healthapp.R
import java.util.Calendar

class DialogRegister : DialogFragment() {

    interface DataCallBack {
        fun onData(data: String)
    }

    private var callback: DataCallBack? = null

    override fun onCreateDialog(savedInstanceStart: Bundle?): Dialog {

        val text = EditText(context)


        val cal = Calendar.getInstance()
        val dialog = activity?.let{
            // 日付のダイアログ
            DatePickerDialog(
                it, {view, year, monthOfYear, dayOfMonth ->
                    val txtDate = text
                    txtDate.setText("${year}/${monthOfYear + 1}/${dayOfMonth}")
                },
                cal[Calendar.YEAR],
                cal[Calendar.MONTH],
                cal[Calendar.DAY_OF_MONTH]
            )
            // ダイアログの表示内容
            AlertDialog.Builder(it).apply {
                setTitle(" 登録")
                setMessage("日付の登録")
                setView(text)
                setIcon(R.drawable.img_1)

                // 「登録」ボタンの設定
                setPositiveButton("OK") {dialog, which ->
                    Toast.makeText(activity, "${text.text.toString()} を登録しました", Toast.LENGTH_SHORT)
                        .show()
                    callback?.onData(text.text.toString())
                }
                setNeutralButton("キャンセル") { dialog, which ->
                    Toast.makeText(activity, "キャンセルしました", Toast.LENGTH_SHORT)
                        .show()
                }
            }.create()


        }
        return dialog ?: throw IllegalStateException(" Activity is null ")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as? DataCallBack
        if (callback == null) {
            throw java.lang.ClassCastException("$callback 実装されていません")
        }
    }
}