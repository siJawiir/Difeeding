package com.example.testingdifeeding

import kotlinx.android.synthetic.main.activity_hasil_kalkulator.*

data class dataFCR(
//    val idUser : String,
    val idData: String,
    val jmlTbr: String,
    val szBnh : String,
    val szPnn : String,
    val lmPlhr : String,
    val tglTbr : String,
    val klHdp : String,
    val frAwal : String,
    val frAkhir : String,
    val slshFR : String,
    val rtAwal : String,
    val bioAwal : String,
    val rtAkhir : String,
    val bioAkhir : String,
    val bthPakan : String,
    val fcr : String,
    val kematian : String,
    val kmtHarian : String,
    val ljTumbuh: String
){
    constructor(): this ("","","","","","","","","","","","","","","","","","",""){

    }
}
