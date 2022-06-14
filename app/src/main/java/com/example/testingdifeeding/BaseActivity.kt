package com.example.testingdifeeding

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity : AppCompatActivity() {

    private lateinit var mProgressDialog: Dialog

    fun showErrorSnackBar(message: String, errorMessage: Boolean){
        val snackBar= Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if(errorMessage){
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorSnackBarError
                )
            )
        }else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }

    fun showProgressDialog(text:String){
        mProgressDialog = Dialog(this)
        mProgressDialog.setContentView(R.layout.dialog_progress)
//        mProgressDialog.tv_progressText.text = text
        mProgressDialog.setCancelable(true)
        mProgressDialog.setCanceledOnTouchOutside(true)
        mProgressDialog.show()
    }


    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }

    val nav = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0,0)
                finish()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_kalkulator -> {
                val intent = Intent(this, KalkulatorActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0,0)
                finish()
                return@OnNavigationItemSelectedListener true
            }
//            R.id.nav_shop -> {
//                val intent = Intent(this, ShopActivity::class.java)
//                startActivity(intent)
//                overridePendingTransition(0,0)
//                finish()
//                return@OnNavigationItemSelectedListener true
//            }
            R.id.nav_user -> {
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0,0)
                finish()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}