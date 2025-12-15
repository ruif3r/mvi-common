package com.ruif3r.sample.viewmodel

import com.ruif3r.mvicommon.MviUiStateFactory

class SampleUiStateFactory : MviUiStateFactory<SampleUiState, SampleResult> {

    override fun create(
        oldState: SampleUiState,
        result: SampleResult
    ): SampleUiState =
        when (result) {
            SampleResult.ToggleImageVisibility -> oldState.copy(shouldShow = !oldState.shouldShow)
            SampleResult.Navigate -> oldState
        }
}