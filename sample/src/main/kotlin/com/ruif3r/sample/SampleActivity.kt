package com.ruif3r.sample

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.tooling.preview.Preview
import com.ruif3r.mvicommon.DefaultActivity
import com.ruif3r.mvicommon.MviSideEffect
import com.ruif3r.sample.theme.MvicommonprojectTheme
import com.ruif3r.sample.ui.SampleButton
import com.ruif3r.sample.ui.SampleIntent
import com.ruif3r.sample.viewmodel.SampleResult
import com.ruif3r.sample.viewmodel.SampleSideEffect
import com.ruif3r.sample.viewmodel.SampleUiState
import com.ruif3r.sample.viewmodel.SampleViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel

class SampleActivity() : DefaultActivity<SampleIntent, SampleResult, SampleUiState, SampleViewModel>() {

    override val viewModel: SampleViewModel by viewModel()

    @Composable
    override fun SetContentComposable(state: State<SampleUiState>) {
        MvicommonprojectTheme {
            SampleButton(state.value.shouldShow, { emitIntent(SampleIntent.ToggleImageVisibility) }, { emitIntent(SampleIntent.Navigate) })
        }
    }

    override fun handleEffect(sideEffect: MviSideEffect) {
        when(sideEffect) {
            SampleSideEffect.Navigate -> {
                Toast.makeText(this, "Navigate", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun intents(): List<Flow<SampleIntent>> = emptyList()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MvicommonprojectTheme {
    }
}