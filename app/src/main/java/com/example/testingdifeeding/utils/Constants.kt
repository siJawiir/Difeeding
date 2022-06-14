package com.example.testingdifeeding.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {
    const val USERS: String = "users"
    const val AF_PREFERENCES: String = "AutoFeedPrefs"
    const val LOGGED_IN_USERNAME: String = "loggedInUsername"
    const val EXTRA_USER_DETAILS: String ="extraUserDetails"

    const val PICK_IMAGE_REQUEST_CODE = 1
    const val READ_STORAGE_PERMISSION_CODE = 2

    const val MALE: String = "male"
    const val FEMALE: String = "female"

    const val noHP: String = "noHP"
    const val GENDER: String = "gender"
    const val IMAGE: String = "image"
    
    const val USER_PROFILE_IMAGE: String = "userProfileImage"
    const val RC_SIGN_IN: Int = 120


    fun showImageChooser(activity : Activity){
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }
}