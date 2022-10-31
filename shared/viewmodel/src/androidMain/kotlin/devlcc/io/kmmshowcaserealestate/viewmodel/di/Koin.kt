package devlcc.io.kmmshowcaserealestate.viewmodel.di

import devlcc.io.kmmshowcaserealestate.viewmodel.home.ListingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun getViewModelModule(): Module = module {

    viewModel {
        ListingsViewModel(
            propertiesRepository = get(),
        )
    }

}