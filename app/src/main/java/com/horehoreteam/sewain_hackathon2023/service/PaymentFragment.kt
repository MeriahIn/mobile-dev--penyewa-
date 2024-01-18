package com.horehoreteam.sewain_hackathon2023.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.horehoreteam.sewain_hackathon2023.R
import com.horehoreteam.sewain_hackathon2023.databinding.FragmentPaymentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PaymentFragment : Fragment() {

    private var binding:FragmentPaymentBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            acivBack.setOnClickListener {
                view.findNavController().navigateUp()
            }

            actvTransfer.setOnClickListener {
                tlMbanking.isVisible = !tlMbanking.isVisible
            }
        }

        lifecycleScope.launch {
            delay(3000L)
            showDialogSuccess()
        }


    }

    private fun showDialogSuccess() {
        val fragment = PaymentSuccessDialogFragment()
        fragment.show(childFragmentManager, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}