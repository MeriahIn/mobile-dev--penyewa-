package com.horehoreteam.sewain_hackathon2023.service

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.horehoreteam.sewain_hackathon2023.JAKARTA_LAT
import com.horehoreteam.sewain_hackathon2023.JAKARTA_LON
import com.horehoreteam.sewain_hackathon2023.R
import com.horehoreteam.sewain_hackathon2023.databinding.FragmentServiceOrderBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ServiceOrderFragment : Fragment(), OnMapReadyCallback, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    private var binding:FragmentServiceOrderBinding?=null

    private lateinit var mMap: GoogleMap
    private var saveState = AddressState()

    private var isStateRetrieved = false

    private val mapActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == ACTIVITY_MAP_RESULT_CODE){
            result.data?.also {
                mMap.clear()
                val selectedLat = it.getDoubleExtra(LAT_KEY, JAKARTA_LAT)
                val selectedLon = it.getDoubleExtra(LON_KEY, JAKARTA_LON)
                val mapLocName = it.getStringExtra(LOC_NAME_KEY) ?: return@also
                val position = LatLng(selectedLat, selectedLon)

                binding?.acetAddress?.text = mapLocName

                mMap.addMarker(
                    MarkerOptions()
                        .position(position)
                )
                mMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        position,
                        20f
                    )
                )
                saveState = saveState.copy(
                    position = position,
                    mapLocName = mapLocName
                )
            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServiceOrderBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            retrieveState(savedInstanceState)
            initMap()

            acetDate.setOnClickListener {
                showDatePicker()
            }

            acetTime.setOnClickListener {
                showTimePicker()
            }

            acivBack.setOnClickListener {
                view.findNavController().navigateUp()
            }
            btnCheckout.setOnClickListener {
                view.findNavController().navigate(R.id.action_serviceOrderFragment_to_paymentMethodFragment)
            }

            mcvOuterContainer.setOnClickListener {
                toMapsActivity()
            }

        }
    }

    private fun showDatePicker() {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(childFragmentManager, "datePicker")
    }

    private fun initMap(){
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.container_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun retrieveState(savedInstanceState: Bundle?){
        savedInstanceState?.let {
            saveState = it.getDataParcel(SAVE_STATE_KEY, AddressState::class.java) ?: return

            //flag to restore map state when map is ready
            isStateRetrieved = true
        }
    }

    @Suppress("DEPRECATION")
    private fun <T>Bundle.getDataParcel(
        key:String,
        clazz:Class<T>
    ): T?{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            getParcelable(key, clazz)
        }else{
            getParcelable(key)
        }
    }

    override fun onMapReady(gmap: GoogleMap) {
        mMap = gmap
        mMap.apply {
            uiSettings.isMapToolbarEnabled = false
            uiSettings.setAllGesturesEnabled(false)

            if (!isStateRetrieved){
                val jakarta = LatLng(JAKARTA_LAT, JAKARTA_LON)
                addMarker(
                    MarkerOptions()
                        .position(jakarta)
                )
                animateCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 5f))
                return
            }

            val state = saveState
            clear()
            addMarker(
                MarkerOptions()
                    .position(state.position)
            )
            animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    state.position,
                    20f
                )
            )
        }


    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding?.acetDate?.setText(dateFormat.format(calendar.time))
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val mHour = if (hour > 9) hour.toString() else "0$hour"
        val mMinute = if (minute > 9) minute.toString() else "0$minute"

        val time = "$mHour:$mMinute"
        binding?.acetTime?.setText(time)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun toMapsActivity(){
        val intent = Intent(requireActivity(), MapActivity::class.java)
        mapActivityLauncher.launch(intent)
    }

    private fun showTimePicker(){
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(childFragmentManager, "timePicker")
    }

    companion object{
        const val ACTIVITY_MAP_RESULT_CODE = 100
        const val LAT_KEY = "lat_key"
        const val LON_KEY = "lon_key"
        const val LOC_NAME_KEY = "loc_key"

        private const val SAVE_STATE_KEY = "save"
    }

}