package com.techjd.medicinereminder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techjd.medicinereminder.data.medicines.Medicines
import com.techjd.medicinereminder.databinding.ItemMedicineBinding

class MedicineListAdapter() :
    RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>() {

    private var medicineList = emptyList<Medicines>()

    inner class MedicineViewHolder(val binding: ItemMedicineBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val binding = ItemMedicineBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return MedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        with(holder) {
            with(medicineList[position]) {
                binding.medicineName.text = this.title
                binding.medicineDescription.text = this.Description
                binding.medicineTime.text = "${this.hour}:${this.min}"
            }
        }
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    fun setData(medicines: List<Medicines>) {
        this.medicineList = medicines
        notifyDataSetChanged()
    }

}