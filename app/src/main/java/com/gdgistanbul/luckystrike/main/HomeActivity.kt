package com.gdgistanbul.luckystrike.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gdgistanbul.luckystrike.R

class HomeActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.container, WaitingFragment.newInstance())
                .commit()
    }
}