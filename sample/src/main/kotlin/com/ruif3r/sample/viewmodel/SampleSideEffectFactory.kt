package com.ruif3r.sample.viewmodel

import com.ruif3r.mvicommon.MviSideEffect
import com.ruif3r.mvicommon.MviSideEffectFactory

class SampleSideEffectFactory : MviSideEffectFactory<SampleResult, SampleUiState> {
    override fun create(
        result: SampleResult,
        previousState: SampleUiState,
        currentState: SampleUiState
    ): MviSideEffect? =
        when (result) {
            SampleResult.Navigate -> SampleSideEffect.Navigate
            else -> null
        }
}