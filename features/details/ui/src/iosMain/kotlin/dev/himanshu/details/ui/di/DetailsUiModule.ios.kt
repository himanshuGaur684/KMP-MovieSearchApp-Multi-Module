package dev.himanshu.details.ui.di

import dev.himanshu.details.ui.DetailsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

private val viewModelModule = module {
    factory { DetailsViewModel(get()) }
}

actual fun getDetailsUiModule(): org.koin.core.module.Module {
    return viewModelModule
}

class DetailsViewModelProvider : KoinComponent {

    fun provideDetailsViewModel(): DetailsViewModel = get()

}