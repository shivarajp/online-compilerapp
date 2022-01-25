package com.masai.onlinecompiler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.masai.onlinecompiler.R
import com.masai.onlinecompiler.data.remote.RetrofitGenerator
import com.masai.onlinecompiler.data.remote.Status
import com.masai.onlinecompiler.models.ExecuteCodeRequestModel
import com.masai.onlinecompiler.repository.MyRespository
import com.masai.onlinecompiler.viewmodels.MyViewModel
import com.masai.onlinecompiler.viewmodels.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dataRepository: MyRespository
    lateinit var viewMode: MyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        dataRepository = MyRespository()
        val factory = MyViewModelFactory(dataRepository)

        viewMode = ViewModelProviders.of(this, factory).get(MyViewModel::class.java)


        button_tab.setOnClickListener {
            text_input_code.text?.insert(text_input_code.selectionStart, "    ")
        }

        button_println.setOnClickListener {
            text_input_code.text?.insert(text_input_code.selectionStart, "print(  )")
        }

        button_quote.setOnClickListener {
            text_input_code.text?.insert(text_input_code.selectionStart, "\"")
        }

        button_semi.setOnClickListener {
            text_input_code.text?.insert(text_input_code.selectionStart, ";")
        }

        button_compile.setOnClickListener {
            txt_output.text = " Output \n"
            val executeCodeRequestModel  = ExecuteCodeRequestModel(
                null,
                RetrofitGenerator.CLIENT_ID,
                RetrofitGenerator.CLIENT_SECRET,
                false,
                "python2",
                null,
                1001,
                text_input_code.text.toString(),
                null,
                3
            )

            viewMode.compileMyCode(executeCodeRequestModel).observe(this, Observer {

                when (it.status){

                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Network error", Toast.LENGTH_LONG).show()

                    }

                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        txt_output.text = " Output \n"
                        txt_output.text = it.data?.output
                    }

                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }

            })
        }

    }
}




