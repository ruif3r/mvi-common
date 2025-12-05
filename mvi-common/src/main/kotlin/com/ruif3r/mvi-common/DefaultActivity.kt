package com.ruif3r.libmvicommon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch

abstract class DefaultActivity<I : MviIntent, R : MviResult, S : MviUiState, VM : BaseMviViewModel<I, R, S>> :
    ComponentActivity() {

    // The Activity requires a ViewModel, but doesn't know how to create it.
    // The subclass in the app module will be responsible for providing it.
    abstract val viewModel: VM

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewEffect.collect { sideEffect -> handleEffect(sideEffect) }
            }
        }
        setContent { SetContentComposable(viewModel.viewState.collectAsState()) }
    }

    @Composable
    abstract fun SetContentComposable(state: State<S>)

    abstract fun handleEffect(sideEffect: MviSideEffect)

    abstract fun intents(): List<Flow<I>>

    private fun mergeIntents(): Flow<I> =
        merge(viewModel.intentFlow, intents().merge())

    fun emitIntent(intent: I) {
        viewModel.process(intent)
    }
}