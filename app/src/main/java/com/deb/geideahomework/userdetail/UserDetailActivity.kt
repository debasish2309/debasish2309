package com.deb.geideahomework.userdetail

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.deb.geideahomework.R
import com.deb.geideahomework.localservice.LocalService
import com.deb.geideahomework.util.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {

    lateinit var nameText: TextView
    lateinit var idtext : TextView
    lateinit var emailtext : TextView
    lateinit var companytext : TextView
    lateinit var timertext : TextView

    private lateinit var mService: LocalService
    private var mBound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        nameText = findViewById(R.id.user_name)
        idtext = findViewById(R.id.user_id)
        emailtext = findViewById(R.id.user_email)
        companytext = findViewById(R.id.user_company)
        timertext = findViewById(R.id.timer_txt)

        val intent = intent
        val id =  intent.getStringExtra("id")

        initMainViewModel(id.toString())

       /* LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadCastReceiver, IntentFilter(Constants.ACTION_TIMER))*/
        val intentFilter = IntentFilter(Constants.ACTION_TIMER)
        registerReceiver(broadCastReceiver, intentFilter)
    }

    private fun initMainViewModel(id:String) {
        val viewModel  = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        viewModel.makeDetailApiCall(id)
        viewModel.getDetailsFromApi().observe(this, Observer {
               nameText.text = it.first_name + it.last_name
                idtext.text =  it.id.toString()
                emailtext.text =  it.email
                companytext.text =  it.avatar

        })
    }

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as LocalService.LocalBinder
            mService = binder.getService()
            mBound = true
            mService.timer
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        Intent(this, LocalService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }



    val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                Constants.ACTION_TIMER -> UpdateTimer(intent)
            }
        }
    }


    fun UpdateTimer(intent: Intent) {
        var time = intent.getIntExtra(Constants.DATA,0)
        if (time!=0) {
            timertext.text = time.toString()
        }else{
            unregisterReceiver(broadCastReceiver)
            finish()
        }
    }
}