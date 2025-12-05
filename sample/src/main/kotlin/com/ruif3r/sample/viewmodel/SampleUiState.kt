package com.ruif3r.sample.viewmodel

import com.ruif3r.libmvicommon.MviUiState

data class SampleUiState(
    val shouldShow: Boolean = false
) : MviUiState