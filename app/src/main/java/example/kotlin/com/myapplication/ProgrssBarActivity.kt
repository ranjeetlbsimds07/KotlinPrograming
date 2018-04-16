package example.kotlin.com.myapplication

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
//import example.kotlin.com.myapplication.R.id.fab
//import example.kotlin.com.myapplication.R.id.toolbar

import kotlinx.android.synthetic.main.activity_progrss_bar.*

class ProgrssBarActivity : AppCompatActivity() {
    internal var pStatus = 0
    private val handler = Handler()
    internal lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progrss_bar)

        val res = resources
        val drawable = res.getDrawable(R.drawable.circular)
        val mProgress = findViewById<View>(R.id.circularProgressbar) as ProgressBar
        mProgress.progress = 0   // Main Progress
        mProgress.secondaryProgress = 100 // Secondary Progress
        mProgress.max = 100 // Maximum Progress
        mProgress.progressDrawable = drawable

        /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/

        tv = findViewById<View>(R.id.tv) as TextView
        Thread(Runnable {
            // TODO Auto-generated method stub
            while (pStatus < 100) {
                pStatus += 1

                handler.post {
                    // TODO Auto-generated method stub
                    mProgress.progress = pStatus
                    tv.text = pStatus.toString() + "%"
                }
                try {
                    // Sleep for 200 milliseconds.
                    // Just to display the progress slowly
                    Thread.sleep(16) //thread will take approx 3 seconds to finish
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }).start()
    }

}
