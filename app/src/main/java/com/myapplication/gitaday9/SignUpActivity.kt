package com.myapplication.gitaday9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.myapplication.gitaday9.database.UserDatabase
import com.myapplication.gitaday9.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SignUpActivity : AppCompatActivity() {
    private var number: EditText? = null
    private var password: EditText? = null
    private var name: EditText? = null
    private var signUp: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initialize()

        signUp?.setOnClickListener {
            if (searchNumber()) {
                Toast.makeText(this, "user already exist", Toast.LENGTH_SHORT).show()
            } else {
                addUserInDatabase()
            }
        }

    }

    private fun initialize() {
        number = findViewById(R.id.signUpMobileTxt)
        password = findViewById(R.id.signUpPasswordTxt)
        name = findViewById(R.id.signUpNameTxt)
        signUp = findViewById(R.id.signUpBtn)
    }

    private fun builtUser(): User {
        val number = number?.text.toString() //Integer.parseInt(number?.text.toString())
        val password = password?.text.toString()
        val name = name?.text.toString()
        return User( mobileNumber = number, password = password, name = name)
    }

    private fun addUserInDatabase() {
        GlobalScope.launch(Dispatchers.IO) {

            UserDatabase.getDataBase(this@SignUpActivity).userDao().insert(builtUser())
        }
    }

    private fun searchNumber(): Boolean {
        val result: Boolean = runBlocking {
            UserDatabase.getDataBase(this@SignUpActivity).userDao().findNumber(number?.text.toString())
        }
        if (result) {
            return true
        }
        return false

    }
}