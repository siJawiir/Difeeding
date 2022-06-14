package com.example.testingdifeeding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.example.testingdifeeding.utils.Constants.RC_SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

//class LoginActivity : BaseActivity(), View.OnClickListener {
class LoginActivity : BaseActivity(){
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()

        btn_loginGoogle.setOnClickListener {
            signIn()
        }

//        tv_forgotPasswordLogin.setOnClickListener(this)
//
//        btn_login.setOnClickListener(this)
//
//        tv_register.setOnClickListener(this)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if(task.isSuccessful){
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("LoginActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    Log.w("LoginActivity", "Google sign in failed", e)
                }
            }else{
                Log.w("LoginActivity", exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("LoginActivity", "signInWithCredential:success")
//                    FirestoreClass().getUserDetails(this@LoginActivity)
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("LoginActivity", "signInWithCredential:failure", task.exception)
                }
            }
    }

//    override fun onClick(view: View?){
//        if (view != null){
//            when (view.id){
//
//                R.id.tv_forgotPasswordLogin -> {
//                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
//                    startActivity(intent)
//                }
//
//                R.id.btn_login -> {
//                    loginRegisteredUser()
//                }
//
//                R.id.tv_register -> {
//                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
//                    startActivity(intent)
//                }
//            }
//        }
//    }
//
//    private fun validateLogin():Boolean{
//        return when {
//            TextUtils.isEmpty(et_emailLogin.text.toString().trim{it <=' '}) -> {
//                showErrorSnackBar(resources.getString(R.string.err_msgEnterEmail),true)
//                false
//            }
//            TextUtils.isEmpty(et_passwordLogin.text.toString().trim{it <=' '}) -> {
//                showErrorSnackBar(resources.getString(R.string.err_msgEnterPassword),true)
//                false
//            }
//            else -> {
//                showErrorSnackBar("Anda berhasil Login",false)
//                true
//            }
//        }
//    }
//
//    private fun loginRegisteredUser(){
//        if(validateLogin()) {
//            showProgressDialog(resources.getString(R.string.pleaseWait))
//
//            val email = et_emailLogin.text.toString().trim{ it <= ' '}
//            val password = et_passwordLogin.text.toString().trim{ it <= ' '}
//
//            //LOGIN FIREBASE
//            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
//                .addOnCompleteListener { task ->
//                    if(task.isSuccessful){
//                        FirestoreClass().getUserDetails(this@LoginActivity)
//                        } else {
//                        showErrorSnackBar(task.exception!!.message.toString(),true)
//                    }
//                }
//        }
//    }
//
//    fun userLoggedInSuccess(user: User){
//        hideProgressDialog()
//
//        Log.i("First Name: ",user.firstName)
//        Log.i("Last Name: ",user.lastName)
//        Log.i("Email: ",user.email)

//        if(user.profileComplete == 0 ){
//            val intent = Intent(this@LoginActivity, UserActivity::class.java)
//            intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
//            startActivity(intent)
//        } else {
//            startActivity(Intent(this@LoginActivity, UserActivity::class.java))
//        }
//        finish()
//    }
}