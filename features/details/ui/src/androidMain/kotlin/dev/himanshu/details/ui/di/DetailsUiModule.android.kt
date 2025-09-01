package dev.himanshu.details.ui.di

import dev.himanshu.details.ui.DetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual fun getDetailsUiModule(): org.koin.core.module.Module {
   return module {
       viewModel { DetailsViewModel(get()) }
   }
}