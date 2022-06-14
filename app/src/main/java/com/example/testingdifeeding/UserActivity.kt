package com.example.testingdifeeding

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : BaseActivity() {
//    companion object {
//        const val REQUEST_CAMERA = 100
//    }
//    private lateinit var imageUri : Uri
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.menu.findItem(R.id.nav_user).isChecked = true
        bottomNavigation.setOnNavigationItemSelectedListener(nav)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if(user != null){
            et_nama.setText(user.displayName)
            et_email.setText(user.email)
//
//            if(user.isEmailVerified){
//                iv_verified.visibility = View.VISIBLE
//            }else{
//                iv_unverified.visibility = View.VISIBLE
//            }
        }
//        if (user.phoneNumber.isNullOrEmpty()){
//            et_phone.setText("Masukkan nomor telepon")
//        }else{
//            et_phone.setText(user.phoneNumber)
//        }

//        iv_imageProfile.setOnClickListener {
//            intentCamera()
//        }
        btn_update.setOnClickListener{
            val name = et_nama.text.toString().trim()
            if (name.isEmpty()){
                et_nama.error= "Nama harus diisi"
                et_nama.requestFocus()
                return@setOnClickListener
            }
            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this,"Profile Updated", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }

        tv_logout.setOnClickListener{
            auth.signOut()
            Intent(this,LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        iv_logout.setOnClickListener{
            auth.signOut()
            Intent(this,LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

//    private fun intentCamera() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
//            this.packageManager?.let {
//                intent.resolveActivity(it).also {
//                    startActivityForResult(intent, REQUEST_CAMERA)
//                }
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
//            val imgBitmap = data?.extras?.get("data") as Bitmap
//            uploadImage(imgBitmap)
//        }
//    }
//
//    private fun uploadImage(imgBitmap: Bitmap) {
//        val baos = ByteArrayOutputStream()
//        val ref = FirebaseStorage.getInstance().reference.child("img/${FirebaseAuth.getInstance().currentUser?.uid}")
//
//        imgBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
//        val image = baos.toByteArray()
//
//        ref.putBytes(image)
//            .addOnCompleteListener{
//                if(it.isSuccessful){
//                    ref.downloadUrl.addOnCompleteListener{
//                        it.result?.let {
//                            imageUri = it
//                            iv_imageProfile.setImageBitmap(imgBitmap)
//                        }
//                    }
//                }
//            }
//    }
}
