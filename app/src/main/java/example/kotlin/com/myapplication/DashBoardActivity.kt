package example.kotlin.com.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {
    var myService: LocalService? = null
    var isBound = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
       //var toolbar =  as Toolbar
        setSupportActionBar(findViewById<Toolbar>(R.id.my_toolbar))

        val intent = Intent(this, LocalService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.default_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onStart() {
        super.onStart()
    }


    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName,
                                        service: IBinder) {
            val binder = service as LocalService.MyLocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.boundService -> {
                val currentTime = myService?.getCurrentTime()
                text_view.text = currentTime
                return true
            }
            R.id.stopBoundService -> {
                if (isBound) {
                    unbindService(myConnection);
                    isBound = false;
                    text_view.text = "Stop Service"
                }
                return true
            }
            R.id.startService -> {
                val serviceintent = Intent(this@DashBoardActivity,StartService::class.java)
                startService(serviceintent)
                return true
            }
            R.id.stopService -> {
                val serviceintent = Intent(this@DashBoardActivity,StartService::class.java)
                stopService(serviceintent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
