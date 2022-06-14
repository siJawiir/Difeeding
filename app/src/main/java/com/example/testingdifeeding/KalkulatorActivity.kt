package com.example.testingdifeeding

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.DatePicker
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_kalkulator.*
import java.util.*
import kotlin.math.ln

class KalkulatorActivity : BaseActivity(), DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0
    var sday =0
    var smonth = 0
    var syear = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.menu.findItem(R.id.nav_kalkulator).isChecked = true
        bottomNavigation.setOnNavigationItemSelectedListener(nav)

        pickDate()

    }

    fun hitungKalkulator(view: View) {
        if (validateKalkulator()) {
//            showProgressDialog(resources.getString(R.string.pleaseWait))
            //GET DATA
            val jumlahTebar = et_jumlahTebar.text.toString().toInt()
            val sizeBenih = et_sizeBenih.text.toString().toInt()
            val sizePanen = et_sizePanen.text.toString().toInt()
            val lamaPelihara = et_lamaPelihara.text.toString().toInt()
            val tanggalTebar = et_tanggalTebar.text.toString()
            val kelangsunganHidup = et_kelangsunganHidup.text.toString().toInt()
            val FRAwal = et_FRAwal.text.toString().toInt()
            val FRAkhir = et_FRAkhir.text.toString().toInt()
//            val selisihFR = et_selisihFR.text.toString().toInt()

            //CALCULATE
            val selisihFR = FRAwal - FRAkhir
            val rerataAwal1 = 1000 / sizeBenih.toDouble()
            val rerataAwal = String.format("%.2f", rerataAwal1).toDouble()
            val biomasAwal1 = jumlahTebar / sizeBenih.toDouble()
            val biomasAwal = String.format("%.2f", biomasAwal1).toDouble()
            val rerataAkhir1 = 1000 / sizePanen.toDouble()
            val rerataAkhir = String.format("%.2f", rerataAkhir1).toDouble()
            val kematian = 100 - kelangsunganHidup
            val kematianHarian = kematian / lamaPelihara.toDouble()
            val lajuPertumbuhan1 = ((ln(rerataAkhir1) - ln(rerataAwal1)) / lamaPelihara) * 100
            val lajuPertumbuhan = String.format("%.2f", lajuPertumbuhan1).toDouble()

            var hari = 1
            var umur = 1
            var bobot = rerataAwal
            var kelangsunganHidupTabel = 100.0
            var ikanHidup = jumlahTebar.toDouble()
            var feedingRate = FRAwal.toDouble()
            var biomas = biomasAwal
            var pakanHarian = 0.0
            var pakanKumulatif = jumlahTebar * rerataAwal * FRAwal / 100000

            for (i in 2..lamaPelihara) {
                hari += 1
//                umur = hari
                bobot += (bobot * lajuPertumbuhan) / 100
//                val bobot1 = String.format("%.2f", bobot).toDouble()
                kelangsunganHidupTabel -= kematianHarian
//                val kelangsunganHidupTabel1 = String.format("%.2f", kelangsunganHidupTabel).toDouble()
                ikanHidup = kelangsunganHidupTabel * jumlahTebar / 100
//                val ikanHidup1 = ikanHidup.roundToInt()
                biomas = bobot * ikanHidup / 100
//                val biomas1 = String.format("%.2f", biomas).toDouble()
                feedingRate -= selisihFR / (lamaPelihara - 1).toDouble()
//                val feedingRate1 = String.format("%.2f", feedingRate).toDouble()
                pakanHarian = biomas * feedingRate / 1000
//                val pakanHarian1 = String.format("%.2f", pakanHarian).toDouble()
                pakanKumulatif = (pakanKumulatif + pakanHarian)
//                val pakanKumulatif1 = String.format("%.2f", pakanKumulatif).toDouble()
            }
            var foodConversionRatio =
                String.format("%.2f", pakanKumulatif / (biomas - biomasAwal) * 10).toDouble()
            var biomasAkhir = String.format("%.2f", biomas / 10).toDouble()
            var totalPakan = String.format("%.2f", pakanKumulatif).toDouble()

            //START INTENT & SEND DATA
            val intent = Intent(this@KalkulatorActivity, HasilKalkulatorActivity::class.java)
            intent.putExtra("Jumlah Tebar", jumlahTebar)
            intent.putExtra("Size Benih", sizeBenih)
            intent.putExtra("Size Panen", sizePanen)
            intent.putExtra("Lama Pelihara", lamaPelihara)
            intent.putExtra("Tanggal Tebar", tanggalTebar)
            intent.putExtra("Kelangsungan Hidup", kelangsunganHidup)
            intent.putExtra("FRAwal", FRAwal)
            intent.putExtra("FRAkhir", FRAkhir)
            intent.putExtra("Selisih FR", selisihFR)
            intent.putExtra("Rerata Awal", rerataAwal)
            intent.putExtra("Biomas Awal", biomasAwal)
            intent.putExtra("Rerata Akhir", rerataAkhir)
            intent.putExtra("Biomas Akhir", biomasAkhir)
            intent.putExtra("Total Kebutuhan Pakan", totalPakan)
            intent.putExtra("Food Convertion Ratio", foodConversionRatio)
            intent.putExtra("Kematian", kematian)
            intent.putExtra("Kematian Harian", kematianHarian)
            intent.putExtra("Laju Pertumbuhan", lajuPertumbuhan)
            startActivity(intent)
        }
    }

    private fun validateKalkulator(): Boolean {
        return when {
            TextUtils.isEmpty(et_jumlahTebar.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msgJumlahTebar), true)
                false
            }

            TextUtils.isEmpty(et_sizeBenih.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msgSizeBenih), true)
                false
            }

            TextUtils.isEmpty(et_sizePanen.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msgSizePanen), true)
                false
            }

            TextUtils.isEmpty(et_lamaPelihara.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msgLamaPelihara), true)
                false
            }

            TextUtils.isEmpty(et_tanggalTebar.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msgTanggalTebar), true)
                false
            }

            TextUtils.isEmpty(et_kelangsunganHidup.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msgKelangsunganHidup), true)
                false
            }

            TextUtils.isEmpty(et_FRAwal.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msgFRAwal), true)
                false
            }

            TextUtils.isEmpty(et_FRAkhir.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msgFRAkhir), true)
                false
            }

//            TextUtils.isEmpty(et_selisihFR.text.toString().trim { it <= ' ' }) -> {
//                showErrorSnackBar(resources.getString(R.string.err_msgSelisihFR), true)
//                false
//            }

            else -> {
                true
            }
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