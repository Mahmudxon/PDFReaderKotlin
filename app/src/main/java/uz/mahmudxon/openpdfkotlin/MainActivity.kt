package uz.mahmudxon.openpdfkotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import uz.mahmudxon.openpdfkotlin.Extentions.log
import uz.mahmudxon.openpdfkotlin.Extentions.startFragment
import uz.mahmudxon.openpdfkotlin.Fragment.DocumentFragment


class MainActivity : AppCompatActivity() {
    val REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_drawer.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.END)
        }
        navigation_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nv_open -> {
                    val intent = Intent()

//                    intent.action = Intent.ACTION_PICK
                    intent.type = "application/pdf"
                    intent.action = Intent.ACTION_GET_CONTENT
                    startActivityForResult(Intent.createChooser(intent, "Select Document"), REQUEST_CODE)
                }
            }

            drawer_layout.closeDrawer(GravityCompat.END)
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                var uri = data.data
                //log(uri.toString())
                 startFragment(DocumentFragment(), uri)
            }
        }
    }
}
