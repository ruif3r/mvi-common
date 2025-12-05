package com.ruif3r.sample.di

import com.ruif3r.sample.viewmodel.SampleResultFactory
import com.ruif3r.sample.viewmodel.SampleSideEffectFactory
import com.ruif3r.sample.viewmodel.SampleUiStateFactory
import com.ruif3r.sample.viewmodel.SampleViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sampleModule = module {

    singleOf(::SampleResultFactory)
    singleOf(::SampleUiStateFactory)
    singleOf(::SampleSideEffectFactory)
    viewModelOf(::SampleViewModel)
}