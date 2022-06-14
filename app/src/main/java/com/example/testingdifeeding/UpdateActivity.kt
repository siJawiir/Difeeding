package com.example.testingdifeeding

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.Toast
import com.example.testingdifeeding.databinding.ActivityUpdateBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_kalkulator.*
import java.util.*

class UpdateActivity : BaseActivity(), DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0
    var sday =0
    var smonth = 0
    var syear = 0

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_update)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.updateHitungBtn.setOnClickListener{
            val idFCR = database.push().key
            val upJumlahTebar = binding.etJumlahTebarUp.toString()
            val upSizeBenih = binding.etSizeBenihUp.toString()
            val upSizePanen = binding.etSizePanenUp.toString()
            val uplamaPelihara = binding.tvLamaPelihara.toString()
            val upTanggalTebar = binding.tvTanggalTebar.toString()
            val upFRAwal = binding.etFRAwalUp.toString()
            val upFRAkhir = binding.etFRAkhirUp.toString()

            updateData(idFCR!!,upJumlahTebar,upSizeBenih,upSizePanen,uplamaPelihara,upTanggalTebar,upFRAwal,upFRAkhir)
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.menu.findItem(R.id.nav_kalkulator).isChecked = true
        bottomNavigation.setOnNavigationItemSelectedListener(nav)

        pickDate()

    }
    private fun updateData(
        idFCR: String,
        upJumlahTebar: String,
        upSizeBenih: String,
        upSizePanen: String,
        uplamaPelihara: String,
        upTanggalTebar: String,
        upFRAwal: String,
        upFRAkhir: String
    ){
        val idUser = FirebaseAuth.getInstance().currentUser?.uid
        database = FirebaseDatabase.getInstance().getReference(idUser!!)
        val data = mapOf<String,String>(
            "upJumlahTebar" to upJumlahTebar,
            "upSizeBenih" to upSizeBenih,
            "upSizePanen" to upSizePanen,
            "upLamaPelihara" to uplamaPelihara,
            "upTanggalTebar" to upTanggalTebar,
            "upFRAwal" to upFRAwal,
            "upFRAkhir" to upFRAkhir
        )
        database.child(idFCR).updateChildren(data).addOnSuccessListener {
            binding.etJumlahTebarUp.text.clear()
            binding.etSizeBenihUp.text.clear()
            binding.etSizePanenUp.text.clear()
            binding.etLamaPeliharaUp.text.clear()
            binding.etTanggalTebarUp.text
            binding.etFRAwalUp.text.clear()
            binding.etFRAkhirUp.text.clear()
            Toast.makeText(this,"Success Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Failed to Updated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun pickDate(){
        et_tanggalTebar.setOnClickListener{
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        sday = dayOfMonth
        smonth = month
        syear = year
        getDateTimeCalendar()
        et_tanggalTebar.text = "$sday-$smonth-$syear"
    }
}