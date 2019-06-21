package uz.mahmudxon.openpdfkotlin.Extentions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import uz.mahmudxon.openpdfkotlin.R


fun BaseFragment.toast(text : String)
{
    Toast.makeText(context, text, Toast.LENGTH_SHORT)
}


fun AppCompatActivity.log(msg:String)
{
    Log.i("myLog", msg)

}

//toast
fun AppCompatActivity.toast(text : String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

//inflate adapter view
fun ViewGroup.inflate(@LayoutRes resId : Int) = LayoutInflater.from(context).inflate(resId, this, false)

//isChecked for string
fun String.isPhone(): Boolean {
    val p = "^1([34578])\\d{9}\$".toRegex()
    return matches(p)
}

fun String.isEmail(): Boolean {
    val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
    return matches(p)
}

fun String.isNumeric(): Boolean {
    val p = "^[0-9]+$".toRegex()
    return matches(p)
}

fun String.isPassword() : Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,16}$".toRegex()
    return matches(passwordPattern)
}

//get date
fun String.date() = this.substring(0, 10)

//get time
fun String.time() = this.substring(11,16)



//Fragments
abstract class BaseFragment(@LayoutRes val resId: Int) : Fragment() {
    internal var senderData: Any? = null

    override fun onCreateView(inflater: LayoutInflater , container: ViewGroup? , savedInstanceState: Bundle?): View? {
        return inflater.inflate(resId, container, false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        onCreatedView(senderData)
    }

    abstract fun onCreatedView(senderData: Any?)

    fun startFragment(fragment: BaseFragment, senderData: Any? = null) {
        fragment.senderData = senderData
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content, fragment)
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.addToBackStack(fragment.hashCode().toString())
            ?.commit()
    }

    fun addFragment(fragment: BaseFragment, senderData: Any? = null) {
        fragment.senderData = senderData
        fragmentManager?.beginTransaction()
            ?.add(R.id.content, fragment)
            ?.addToBackStack(fragment.hashCode().toString())
            ?.commit()
    }

    fun finish() {
        fragmentManager?.popBackStack()
    }
}

fun AppCompatActivity.startFragment(fragment: BaseFragment, senderData: Any? = null) {
    val resId = ViewModelProviders.of(this)[BaseFragmentViewModel::class.java].resId
    fragment.senderData = senderData
    supportFragmentManager.beginTransaction().replace(resId, fragment).commit()
}

fun AppCompatActivity.initialFragment(@IdRes resId: Int) {
    ViewModelProviders.of(this)[BaseFragmentViewModel::class.java].resId = resId
}

fun BaseFragment.initialFragment(@IdRes resId: Int) {
    ViewModelProviders.of(this)[BaseFragmentViewModel::class.java].resId = resId
}

class BaseFragmentViewModel : ViewModel() {
    internal var resId: Int = R.id.content
}