package dev.himanshu.search.ui.di

import dev.himanshu.search.ui.SearchViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual fun getSearchPresentationModule(): Module {
    return module {
        viewModel { SearchViewModel(get()) }
    }

}