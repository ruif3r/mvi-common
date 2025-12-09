package com.ruif3r.sample.viewmodel

import com.ruif3r.mvicommon.MviResultFactory
import com.ruif3r.sample.ui.SampleIntent

class SampleResultFactory : MviResultFactory<SampleIntent, SampleResult> {

    override fun create(intent: SampleIntent): SampleResult =
        when (intent) {
            SampleIntent.ToggleImageVisibility ->  SampleResult.ToggleImageVisibility
        }
}