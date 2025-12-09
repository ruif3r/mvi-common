package com.ruif3r.sample.ui

import com.ruif3r.mvicommon.MviIntent

sealed class SampleIntent : MviIntent {

    object ToggleImageVisibility : SampleIntent()

}
