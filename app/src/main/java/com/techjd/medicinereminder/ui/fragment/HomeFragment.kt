package com.techjd.medicinereminder.ui.fragment

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.techjd.medicinereminder.R
import com.techjd.medicinereminder.adapters.MedicineListAdapter
import com.techjd.medicinereminder.data.medicines.Medicines
import com.techjd.medicinereminder.data.medicines.MedicinesDatabase
import com.techjd.medicinereminder.databinding.AddFragmentBinding
import com.techjd.medicinereminder.databinding.HomeFragmentBinding
import com.techjd.medicinereminder.viewmodel.MedicinesViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var medicineListAdapter: MedicineListAdapter
    private lateinit var medicinesList: List<Medicines>

    private lateinit var medicinesViewModel: MedicinesViewModel

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: MedicinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        medicinesViewModel =
            ViewModelProvider(this).get(MedicinesViewModel(activity!!.application)::class.java)

        val adapter = MedicineListAdapter()
        binding.rvMedicine.layoutManager = LinearLayoutManager(context)
        binding.rvMedicine.adapter = adapter


        medicinesViewModel.getAllMedicines.observe(viewLifecycleOwner, Observer { medicine ->
            if (medicine.isEmpty()) {
                binding.noMed.visibility = View.VISIBLE
            } else {

                adapter.setData(medicine)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}