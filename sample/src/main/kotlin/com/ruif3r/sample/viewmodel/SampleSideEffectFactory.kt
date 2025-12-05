package com.ruif3r.sample.viewmodel

import com.ruif3r.libmvicommon.MviSideEffect
import com.ruif3r.libmvicommon.MviSideEffectFactory

class SampleSideEffectFactory : MviSideEffectFactory<SampleResult, SampleUiState> {
    override fun create(
        result: SampleResult,
        previousState: SampleUiState,
        currentState: SampleUiState
    ): MviSideEffect? =
        when (result) {
            else -> null
        }
}