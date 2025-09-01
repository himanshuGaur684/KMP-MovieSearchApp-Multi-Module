package dev.himanshu.moviesearchapp.di

import dev.himanshu.coreNework.di.getCoreNetworkModule
import dev.himanshu.details.data.di.getDetailsDataModule
import dev.himanshu.details.domain.di.getDetailsDomainModule
import dev.himanshu.details.ui.di.getDetailsUiModule
import dev.himanshu.search.data.di.getSearchDataModule
import dev.himanshu.search.di.getSearchUiModule
import dev.himanshu.search.domain.di.getSearchDomainModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            getCoreNetworkModule(),

            getSearchDataModule(),
            getSearchDomainModule(),
            getSearchUiModule(),

            getDetailsUiModule(),
            getDetailsDomainModule(),
            getDetailsDataModule()
        )
    }
}