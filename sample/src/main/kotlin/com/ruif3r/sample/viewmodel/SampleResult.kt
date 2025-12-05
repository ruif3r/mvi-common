package com.ruif3r.sample.viewmodel

import com.ruif3r.libmvicommon.MviResult

sealed class SampleResult : MviResult {

    object ToggleImageVisibility : SampleResult()
}
