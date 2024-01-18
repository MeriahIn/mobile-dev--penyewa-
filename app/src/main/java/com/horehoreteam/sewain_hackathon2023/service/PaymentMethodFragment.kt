package com.horehoreteam.sewain_hackathon2023.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.horehoreteam.sewain_hackathon2023.R
import com.horehoreteam.sewain_hackathon2023.databinding.FragmentPaymentMethodBinding

class PaymentMethodFragment : Fragment() {

    private var binding:FragmentPaymentMethodBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            acivBack.setOnClickListener {
                view.findNavController().navigateUp()
            }
            btnCheckout.setOnClickListener {
                view.findNavController().navigate(R.id.action_paymentMethodFragment_to_paymentFragment)
            }
            actvTransfer.setOnClickListener {
                rgTransfer.isVisible = !rgTransfer.isVisible
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}