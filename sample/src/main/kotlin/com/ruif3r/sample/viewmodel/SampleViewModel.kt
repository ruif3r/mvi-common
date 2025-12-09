package com.ruif3r.sample.viewmodel

import com.ruif3r.mvicommon.BaseMviViewModel
import com.ruif3r.sample.ui.SampleIntent

class SampleViewModel(
    resultFactory: SampleResultFactory,
    uiStateFactory: SampleUiStateFactory,
    sideEffectFactory: SampleSideEffectFactory,
) : BaseMviViewModel<SampleIntent, SampleResult, SampleUiState>(
    resultFactory,
    uiStateFactory,
    sideEffectFactory,
    SampleUiState()
)
