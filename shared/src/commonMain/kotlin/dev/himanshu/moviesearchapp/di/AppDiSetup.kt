package dev.himanshu.moviesearchapp.di

import dev.himanshu.coreNetwork.di.getCoreNetworkModule
import dev.himanshu.details.data.di.getDetailsDataModule
import dev.himanshu.details.domain.di.getDetailsDomainModule
import dev.himanshu.details.ui.di.getDetailsUiModule
import dev.himanshu.search.data.di.getSearchDataModule
import dev.himanshu.search.domain.di.getSearchDomainModule
import dev.himanshu.search.ui.di.getSearchPresentationModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            getCoreNetworkModule(),
            getSearchDataModule(),
            getSearchDomainModule(),
            getSearchPresentationModule(),

            getDetailsDataModule(),
            getDetailsDomainModule(),
            getDetailsUiModule()
        )
    }
}
