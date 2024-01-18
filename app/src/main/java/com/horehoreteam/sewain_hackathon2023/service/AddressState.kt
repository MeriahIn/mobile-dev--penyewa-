package com.horehoreteam.sewain_hackathon2023.service

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.horehoreteam.sewain_hackathon2023.JAKARTA_LAT
import com.horehoreteam.sewain_hackathon2023.JAKARTA_LON
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressState (
    val position: LatLng = LatLng(JAKARTA_LAT, JAKARTA_LON),
    val mapLocName:String = "Jakarta"
):Parcelable