package uz.mahmudxon.openpdfkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_start_up.*
import uz.mahmudxon.openpdfkotlin.Adapters.PagerAdapter
import uz.mahmudxon.openpdfkotlin.Cache.MyCache
import uz.mahmudxon.openpdfkotlin.Fragment.Setup.Page1Fragment
import uz.mahmudxon.openpdfkotlin.Fragment.Setup.Page2Fragment
import uz.mahmudxon.openpdfkotlin.Fragment.Setup.Page3Fragment

class StartUpActivity : AppCompatActivity() {
lateinit var timer : CountDownTimer
    lateinit var adapter : PagerAdapter
    lateinit var data : ArrayList<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up)


    }


    override fun onResume() {
        super.onResume()
        timer = object : CountDownTimer(2000, 1000) {
            override fun onFinish() = if(!MyCache.default.isSetup)
            {
                setup()
            }
            else
            {
               startActivity(Intent(this@StartUpActivity, MainActivity::class.java))
                this@StartUpActivity.finish()
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }.start()
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }

    private fun setup() {
        splash_layout.visibility = View.GONE
        setup_layout.visibility = View.VISIBLE
        data = ArrayList()
        data.add(Page1Fragment())
        data.add(Page2Fragment())
        data.add(Page3Fragment())
        adapter = PagerAdapter(supportFragmentManager, data)
        setup_pager.adapter = adapter
        setup_rg.check(0+1)

        next_setup.setOnClickListener {
            if((it as Button).text.trim().toString().toLowerCase() == "next")
            {
                setup_pager.currentItem ++
            }
            else
            {
                MyCache.default.isSetup = true
                startActivity(Intent(this, MainActivity::class.java))
            finish()
            }
        }

        prev_setup.setOnClickListener {
            setup_pager.currentItem --
        }

        setup_pager.addOnPageChangeListener(object  : ViewPager.OnPageChangeListener
        {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setup_rg.clearCheck()
                setup_rg.check(position + 1)
                if(position == 0)
                {
                    prev_setup.visibility = View.GONE
                }
                else
                {
                    prev_setup.visibility = View.VISIBLE
                }

                if(position == 2)
                {
                    next_setup.text = "finish"
                }
                else
                {
                    next_setup.text = "next"
                }

            }
        })
    }
}
