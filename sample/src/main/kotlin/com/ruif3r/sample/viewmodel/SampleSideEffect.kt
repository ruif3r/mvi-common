package com.ruif3r.sample.viewmodel

import com.ruif3r.mvicommon.MviSideEffect

sealed class SampleSideEffect : MviSideEffect {

    object Navigate : SampleSideEffect()
}