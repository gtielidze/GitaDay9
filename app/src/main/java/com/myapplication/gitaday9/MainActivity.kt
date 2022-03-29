package com.myapplication.gitaday9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.myapplication.gitaday9.database.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private var number: EditText? = null
    private var password: EditText? = null
    private var signIn: Button? = null
    private var signUp: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()

        signUp?.setOnClickListener {
            nextActivity()
        }

        signIn?.setOnClickListener {
            if(searchNumber()) {
                Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
                welcomeScreen()
            } else {
                Toast.makeText(this, "user doesn't exist, please register", Toast.LENGTH_SHORT).show()
                nextActivity()
            }
        }
    }

    private fun initialize() {
        number = findViewById(R.id.mobileTxt)
        password = findViewById(R.id.passwordTxt)
        signIn = findViewById(R.id.startActivitySignInBtn)
        signUp = findViewById(R.id.startActivitySignUpBtn)
    }

    private fun nextActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun searchNumber(): Boolean {
        val result: Boolean = runBlocking {
            UserDatabase.getDataBase(this@MainActivity).userDao()
                .findNumber(number?.text.toString())
        }
        if (result) {
            return true
        }
        return false

    }

    private fun welcomeScreen() {
        val intent = Intent(this, WelcomeUserActivity::class.java)
        intent.putExtra("number", number?.text.toString())
        startActivity(intent)
    }
}