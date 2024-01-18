package com.horehoreteam.sewain_hackathon2023.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.gms.maps.SupportMapFragment
import com.horehoreteam.sewain_hackathon2023.R
import com.horehoreteam.sewain_hackathon2023.databinding.FragmentServiceDetailBinding

class ServiceDetailFragment : Fragment() {

    private var binding:FragmentServiceDetailBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServiceDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            acivBack.setOnClickListener {
                view.findNavController().navigateUp()
            }

            acbRent.setOnClickListener {
                view.findNavController().navigate(
                    R.id.action_serviceDetailFragment_to_serviceOrderFragment
                )
            }


        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}