package devlcc.io.kmmshowcaserealestate.viewmodel.di

import devlcc.io.kmmshowcaserealestate.viewmodel.home.ListingsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


/**
 * Helper class to resolve ViewModel instances
 */
object ViewModelResolver: KoinComponent {

    fun getListingsViewModel() = get<ListingsViewModel>()

}