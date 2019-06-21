package uz.mahmudxon.openpdfkotlin.Fragment


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_document.*
import uz.mahmudxon.openpdfkotlin.Extentions.BaseFragment
import uz.mahmudxon.openpdfkotlin.Extentions.toast

import uz.mahmudxon.openpdfkotlin.R


class DocumentFragment : BaseFragment(R.layout.fragment_document)
{
    override fun onCreatedView(senderData: Any?) {
        val url : Uri = senderData as Uri
        pdfview.fromUri(url)
            .enableDoubletap(true)
            .onError {
                toast("Error while opening...")
            }
            .swipeHorizontal(false)
            .load()
    }
}
