package dev.himanshu.search.di

import dev.himanshu.search.ui.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual fun getSearchUiModule(): org.koin.core.module.Module {
    return module {
        viewModel { SearchViewModel(get()) }
    }
}