package example.kotlin.com.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

/**
 * Created by Guest User on 3/30/2018.
 */
class StartService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("====onCreate","==onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("====onStartCommand","==onStartCommand")
        Toast.makeText(this@StartService, "Service Start",Toast.LENGTH_LONG).show()
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this@StartService, "Service Stop",Toast.LENGTH_LONG).show()
    }
    override fun onBind(intent: Intent?): IBinder? {
        println("====onBind-------StartService--")
        return null;
    }
}