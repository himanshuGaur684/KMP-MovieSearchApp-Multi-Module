package dev.himanshu.search.ui.di

import dev.himanshu.search.ui.SearchViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

private val searchViewModelModule = module {
    single { SearchViewModel(get()) }
}

actual fun getSearchPresentationModule(): Module {
    return searchViewModelModule
}

class SearchViewModelProvider : KoinComponent {
    fun provideSearchViewModel() = get<SearchViewModel>()
}
