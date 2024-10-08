package com.omerakkoyun.unittestsdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omerakkoyun.unittestsdemo.databinding.ActivityMainBinding
import com.omerakkoyun.unittestsdemo.test2.ILoginView
import com.omerakkoyun.unittestsdemo.test2.LoginPresenter
import com.omerakkoyun.unittestsdemo.test5.UserListViewModel

class MainActivity : AppCompatActivity(), ILoginView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        viewModel.getList()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val presenter = LoginPresenter(this)
        binding.btnLogin.setOnClickListener {
            presenter.login("user","password")
        }

        observeViewModel()


    }

    private fun observeViewModel() {
        viewModel.listLive.observe(this , Observer {
            it?.apply {
                
            }
        })


        viewModel.errorLive.observe(this , Observer {
            it?.apply {

            }
        })


        viewModel.showEmptyLive.observe(this , Observer {
            Log.i("List: ", "List is Empty !!!")
        })
    }

    override fun onLoginSuccess() {
        Log.i("LoginPresenter", "Login success")
    }

    override fun onLoginFail(e: Throwable) {
       Log.i("LoginPresenter", "Login fail")
    }


}