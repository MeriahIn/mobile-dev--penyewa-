package com.horehoreteam.sewain_hackathon2023.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.horehoreteam.sewain_hackathon2023.DataDummy
import com.horehoreteam.sewain_hackathon2023.R
import com.horehoreteam.sewain_hackathon2023.databinding.FragmentCateringListBinding
import com.horehoreteam.sewain_hackathon2023.mainpage.adapter.CateringPreviewAdapter
import com.horehoreteam.sewain_hackathon2023.mainpage.adapter.ServiceListItemDecoration
import com.horehoreteam.sewain_hackathon2023.model.CateringPreview


class ServiceListFragment : Fragment() {

    private var binding: FragmentCateringListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCateringListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CateringPreviewAdapter(
            DataDummy.generateCateringPreview(),
            ::onItemClicked
        )

        binding?.apply {
            rvService.adapter = adapter
            rvService.addItemDecoration(
                ServiceListItemDecoration(
                        resources.displayMetrics,
                        paddingStart = 10,
                        paddingBottom = 16
                    )
            )
            rvService.layoutManager = GridLayoutManager(requireActivity(), 2)

            acivExplorePackageBackButton.setOnClickListener {
                requireActivity().finish()
            }
        }
    }

    private fun onItemClicked(item:CateringPreview){
        view?.findNavController()?.navigate(
            R.id.action_cateringListFragment_to_serviceDetailFragment
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}