package uz.mahmudxon.openpdfkotlin.Fragment


import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_document.*
import uz.mahmudxon.openpdfkotlin.Dialogs.InputPageDialog
import uz.mahmudxon.openpdfkotlin.Extentions.BaseFragment
import uz.mahmudxon.openpdfkotlin.Extentions.toast

import uz.mahmudxon.openpdfkotlin.R


class DocumentFragment : BaseFragment(R.layout.fragment_document)
{
    lateinit var url : Uri
    lateinit var timer : CountDownTimer
    lateinit var dialog : InputPageDialog
    override fun onCreatedView(senderData: Any?) {
         url  = senderData as Uri
        pdfview.fromUri(url)
            .enableDoubletap(true)
            .onError {
                toast("Error while opening...")
            }
            .onPageScroll { page, positionOffset ->
                doc_page.visibility = View.VISIBLE
                doc_page.text = "${(page + 1)}/${pdfview.pageCount}"

           try {
               timer.cancel()
           }
           catch(x : Exception)
           {

           }
            timer = object :CountDownTimer(3000, 100)
            {
                override fun onFinish() {
                    doc_page.visibility = View.GONE
                }

                override fun onTick(millisUntilFinished: Long) {

                }
            }.start()


            }
            .swipeHorizontal(false)
            .load()


        doc_page.setOnClickListener {

            dialog = InputPageDialog(requireContext())
            dialog.show()
            dialog.onPageInputListener {
                if(it>0 && it<=pdfview.pageCount) {
                    pdfview.jumpTo(it - 1)
                    doc_page.text = "${(pdfview.currentPage + 1)}/${pdfview.pageCount}"
                }
                else
                {
                    toast("invalid page")
                }
            }

        }

    }

//
//
//    override fun onPause() {
//        super.onPause()
//
//    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putInt("page", pdfview.currentPage)
//        outState.putCharSequence("file", url.toString())
//        super.onSaveInstanceState(outState)
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//
//   }


}
