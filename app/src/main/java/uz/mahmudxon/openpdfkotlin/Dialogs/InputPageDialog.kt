package uz.mahmudxon.openpdfkotlin.Dialogs

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.doc_page_dialog.*
import uz.mahmudxon.openpdfkotlin.R

class InputPageDialog : AlertDialog {
    private var listener : ((Int) -> Unit) ?= null

    fun onPageInputListener(f : ((Int) -> Unit)) {
        listener = f
    }
    constructor(context: Context) : super(context) {
        val view : View = LayoutInflater.from(context).inflate(R.layout.doc_page_dialog, null, false)


        setButton(DialogInterface.BUTTON_POSITIVE, "Ok", DialogInterface.OnClickListener { dialog, which ->
            listener?.let { it(Integer.parseInt(da_page.text.toString())) }
        })


        setView(view)
    }
}