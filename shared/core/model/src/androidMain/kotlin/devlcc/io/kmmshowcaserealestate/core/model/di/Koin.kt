@file:JvmName("KoinJvmCoreModel")

package devlcc.io.kmmshowcaserealestate.core.model.di

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * shared implementation of parcelable
 */
actual typealias CommonParcelize = Parcelize

actual typealias CommonParcelable = Parcelable