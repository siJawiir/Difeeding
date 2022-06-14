package com.example.testingdifeeding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private val dataFCR: ArrayList<dataFCR>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.data_fcr,parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = dataFCR[position]

        holder.dataTanggal.text = currentItem.tglTbr
        holder.jmlTbr.text = currentItem.jmlTbr
        holder.szBnh.text = currentItem.szBnh
        holder.szPnn.text = currentItem.szPnn
        holder.lmPlhr.text = currentItem.lmPlhr
        holder.tglTbr.text = currentItem.tglTbr
        holder.klHdp.text = currentItem.klHdp
        holder.FRAwal.text = currentItem.frAwal
        holder.FRAkhir.text = currentItem.frAkhir
        holder.slshFR.text = currentItem.slshFR
        holder.rtAwal.text = currentItem.rtAwal
        holder.rtAkhir.text = currentItem.rtAkhir
        holder.bioAwal.text = currentItem.bioAwal
        holder.bioAkhir.text = currentItem.bioAkhir
        holder.bthPakan.text = currentItem.bthPakan
        holder.fcr.text = currentItem.fcr
        holder.kematian.text = currentItem.kematian
        holder.kmtHarian.text = currentItem.kmtHarian
        holder.ljTumbuh.text = currentItem.ljTumbuh
    }

    override fun getItemCount(): Int {
        return dataFCR.size
    }


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val jmlTbr: TextView = itemView.findViewById(R.id.tv_datajumlahTebarHasil)
        val szBnh : TextView = itemView.findViewById(R.id.tv_datasizeBenihHasil)
        val szPnn : TextView = itemView.findViewById(R.id.tv_datasizePanenHasil)
        val lmPlhr : TextView = itemView.findViewById(R.id.tv_datalamaPeliharaHasil)
        val tglTbr : TextView = itemView.findViewById(R.id.tv_datatanggalTebarHasil)
        val klHdp : TextView = itemView.findViewById(R.id.tv_datakelangsunganHidupHasil)
        val FRAwal : TextView = itemView.findViewById(R.id.tv_dataFRAwalHasil)
        val FRAkhir : TextView = itemView.findViewById(R.id.tv_dataFRAkhirHasil)
        val slshFR : TextView = itemView.findViewById(R.id.tv_dataselisihFRHasil)
        val rtAwal : TextView = itemView.findViewById(R.id.tv_datarerataAwalHasil)
        val bioAwal : TextView = itemView.findViewById(R.id.tv_databiomasAwalHasil)
        val rtAkhir : TextView = itemView.findViewById(R.id.tv_datarerataAkhirHasil)
        val bioAkhir : TextView = itemView.findViewById(R.id.tv_databiomasAkhirHasil)
        val bthPakan : TextView = itemView.findViewById(R.id.tv_datakebutuhanPakanHasil)
        val fcr : TextView = itemView.findViewById(R.id.tv_dataFCRHasil)
        val kematian : TextView = itemView.findViewById(R.id.tv_datakematianHasil)
        val kmtHarian : TextView = itemView.findViewById(R.id.tv_datakematianHarianHasil)
        val ljTumbuh: TextView = itemView.findViewById(R.id.tv_datalajuPertumbuhanHasil)
        val dataTanggal : TextView = itemView.findViewById(R.id.tv_dataTanggal)

    }
}