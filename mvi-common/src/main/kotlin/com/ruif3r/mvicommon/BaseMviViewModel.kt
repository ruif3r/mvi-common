package com.ruif3r.mvicommon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseMviViewModel<Intent : MviIntent, Result : MviResult, UiState : MviUiState> (
    private val resultFactory: MviResultFactory<Intent, Result>,
    private val uiStateFactory: MviUiStateFactory<UiState, Result>,
    private val sideEffectFactory: MviSideEffectFactory<Result, UiState>,
    private val defaultViewState: UiState
) : MviViewModel<Intent>, ViewModel() {

    private val mutableViewState = MutableStateFlow(defaultViewState)

    private val viewEffectChannel: Channel<MviSideEffect> = Channel()

    val viewEffect = viewEffectChannel.receiveAsFlow()

    internal val viewState: StateFlow<UiState>
        get() = mutableViewState.asStateFlow()

    internal val intentFlow = MutableSharedFlow<Intent>()


    init {
        viewModelScope.launch {
            intentFlow
                .onEach { Log.d(javaClass.canonicalName, "intent received, $it") }
                .map { resultFactory.create(it) }
                .onEach { Log.d(this.javaClass.canonicalName, "result created, $it") }
                .runningFold(defaultViewState) { previous, result ->
                    uiStateFactory.create(previous, result)
                        .let {
                            sideEffectFactory.create(result, previous, it)?.let { sideEffect ->
                                viewEffectChannel.send(sideEffect)
                                Log.d(javaClass.simpleName, "SideEffect created, $sideEffect")
                            }
                            it
                        }
                }
                .onEach { Log.d(javaClass.canonicalName, "viewState created, $it") }
                .onStart { emit(defaultViewState) }
                .collect(mutableViewState)
        }
    }

    override fun process(intent: Intent) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }
}