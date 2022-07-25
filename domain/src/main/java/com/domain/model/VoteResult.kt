package com.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Thanh Quang on 25/07/2022.
 */
@Parcelize
data class VoteResult(
    val message: String,
    val id: Int
) : Parcelable