package dev.himanshu.details.data.di

import dev.himanshu.details.data.repository.DetailsRepoImpl
import dev.himanshu.details.domain.repository.DetailsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDataModule(): Module {
    return module {
        single<DetailsRepository> { DetailsRepoImpl(get()) }
    }
}