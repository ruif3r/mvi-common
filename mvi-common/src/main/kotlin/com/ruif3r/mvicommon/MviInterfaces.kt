package com.ruif3r.mvicommon

interface MviIntent
interface MviResult
interface MviSideEffect
interface MviUiState

interface MviView<I: MviIntent, in S: MviUiState> {
    fun render(viewState: S)
}

interface MviResultFactory<I: MviIntent, R: MviResult> {
    fun create(intent: I): R
}

interface MviUiStateFactory<S: MviUiState, R: MviResult> {
    fun create(oldState: S,  result: R): S
}

interface MviViewModel<I: MviIntent> {
    fun process(intent: I)
}

interface MviSideEffectFactory<R: MviResult, S: MviUiState> {
    fun create(
        result: R,
        previousState: S,
        currentState: S
    ): MviSideEffect?
}

interface MviRouter {
    fun route(sideEffect: MviSideEffect)
}