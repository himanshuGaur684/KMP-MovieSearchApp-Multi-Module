package dev.himanshu.search.di

import dev.himanshu.search.ui.SearchViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

private val viewModelModule = module {
    factory { SearchViewModel(get()) }
}

actual fun getSearchUiModule(): org.koin.core.module.Module {
    return viewModelModule
}


class SearchViewModelProvider : KoinComponent {

    fun provideSearchViewModel(): SearchViewModel = get()

}
