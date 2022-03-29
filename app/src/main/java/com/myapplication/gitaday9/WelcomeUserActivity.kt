package com.myapplication.gitaday9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import com.myapplication.gitaday9.database.UserDatabase
import kotlinx.coroutines.runBlocking

class WelcomeUserActivity : AppCompatActivity() {
    private var name: TextView? = null
    private var number: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_user)
        name = findViewById(R.id.welcomeActivityNameTxt)
        number = findViewById(R.id.welcomeActivityMobileTxt)
        getDataFromRoom()
    }

    private fun getDataFromRoom() {
        val mobileNumber = intent.getStringExtra("number")
        val result = runBlocking {
            UserDatabase.getDataBase(this@WelcomeUserActivity).userDao().getUser(mobileNumber)
        }
        name?.text = "Welcome: ${result.name.toString()}"
        number?.text = "Mobile: ${result.mobileNumber.toString()}"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.featureMenu -> {
                mainActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}