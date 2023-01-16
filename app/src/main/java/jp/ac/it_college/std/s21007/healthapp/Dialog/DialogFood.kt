package jp.ac.it_college.std.s21007.healthapp.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import jp.ac.it_college.std.s21007.healthapp.HistoryDetail
import jp.ac.it_college.std.s21007.healthapp.R
import jp.ac.it_college.std.s21007.healthapp.databinding.CustomDialogBinding


class DialogFood : DialogFragment() {

    // DialogFragmentのコールバック
    interface FoodCallBack {
        fun onData(data: String, calorei: Int )
    }

    // 食べ物名のコールバック
    private var callback: DialogFood.FoodCallBack? = null

        // binding の設定　
    private lateinit var binding: CustomDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val custom = inflater.inflate(R.layout.custom_dialog, null)

            builder.setView(custom)
                .setPositiveButton(R.string.register_item,
                    DialogInterface.OnClickListener { dialog, id ->
                        val historyDetail: HistoryDetail = activity as HistoryDetail
                        Toast.makeText(historyDetail, "登録しました。", Toast.LENGTH_SHORT).show()
                        // ダイアログから、食べ物名を取得する処理
                        val editText : EditText = custom.findViewById(R.id.add_food)
                        val food = editText.text.toString()

                        // カロリーのデータを取得
                        val editText2 : EditText = custom.findViewById(R.id.add_calorie)
                        val calorie = editText2.text.toString()
                        callback?.onData(food, calorie.toInt())

                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as? FoodCallBack
        if (callback == null) {
            throw java.lang.ClassCastException("$callback 実装されていません")
        }
    }
}