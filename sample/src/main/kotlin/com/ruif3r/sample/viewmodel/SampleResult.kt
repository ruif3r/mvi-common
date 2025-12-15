package com.ruif3r.sample.viewmodel

import com.ruif3r.mvicommon.MviResult

sealed class SampleResult : MviResult {

    object ToggleImageVisibility : SampleResult()

    object Navigate : SampleResult()
}
