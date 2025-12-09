# mvi-common Library

## Purpose

`mvi-common` is a lightweight, boilerplate-free library designed to provide a foundational structure for implementing the Model-View-Intent (MVI) design pattern in modern Android applications. It offers a set of base classes that streamline the development of MVI components, allowing developers to focus on business logic rather than on the underlying architecture.

The core of the library is the `DefaultActivity`, an abstract class that handles the MVI loop, state management, and effect handling, providing a clean and robust architecture for building UI-centric features.

## Technologies Used

This library is built using a modern Android technology stack:

*   **Kotlin**: The entire library is written in Kotlin.
*   **Jetpack Compose**: The UI layer is designed to be built with Jetpack Compose.
*   **Kotlin Coroutines & Flow**: Used for managing asynchronous operations and data streams within the MVI loop.
*   **Jetpack ViewModel**: The library leverages `ViewModel` for lifecycle-aware state management.

## Design Patterns

### Model-View-Intent (MVI)

The library is architected around the MVI pattern, which promotes a unidirectional data flow and a clear separation of concerns. This results in code that is more predictable, testable, and maintainable.

*   **Model**: Represents the state of the UI (`MviUiState`).
*   **View**: A passive interface that displays the state and emits user actions (`DefaultActivity` and its composables).
*   **Intent**: Represents a user's intention to change the state (`MviIntent`).

### Factory Pattern

The library also leverages the Factory Pattern to decouple the `BaseMviViewModel` from the concrete creation of `Result`, `UiState`, and `SideEffect` objects.

Instead of the `ViewModel` creating these objects directly, it delegates this responsibility to factory classes that are injected into its constructor (e.g., `SampleResultFactory`, `SampleUiStateFactory`). This makes the `ViewModel` more flexible and testable, as the creation logic is centralized and can be easily swapped or mocked.

### Dependency Injection Agnostic

`DefaultActivity` is designed to be completely independent of any specific dependency injection framework. It achieves this by requiring the consuming Activity to provide the `ViewModel` instance.

This makes the library highly flexible, allowing the consumer to use Hilt, Koin, or even manual dependency injection.

## How to Use

Integrating `mvi-common` into your project is straightforward. The `sample` app in this repository provides a clear, working example.

### 1. Define Your MVI Components

First, define the classes that represent your screen's Intent, Result, and State.

```kotlin
// Represents user actions
sealed class SampleIntent : MviIntent {
    object ToggleImageVisibility : SampleIntent()
}

// Represents the outcome of an action
sealed class SampleResult : MviResult {
    object ImageVisibilityToggled() : SampleResult()
}

// Represents the UI state
data class SampleUiState(val shouldShow: Boolean = false) : MviUiState
```

### 2. Create a ViewModel

Create a `ViewModel` that inherits from `BaseMviViewModel` and pass in the classes you define in the previous step to determine the types that `BaseMviViewModel` will use. The `ViewModel` will handle the MVI logic.

```kotlin
class SampleViewModel(
    resultFactory: SampleResultFactory,
    uiStateFactory: SampleUiStateFactory,
    sideEffectFactory: SampleSideEffectFactory
) : BaseMviViewModel<SampleIntent, SampleResult, SampleUiState>(
    resultFactory,
    uiStateFactory,
    sideEffectFactory,
    SampleUiState()
)
```

### 3. Create an Activity

Create an `Activity` that inherits from `DefaultActivity`. This class will be responsible for providing the `ViewModel` and defining the Composable UI.

In the `SetContentComposable` function is where you place all the UI using the state parameter to react accordingly, which holds the current state data of the UI.

The `sample` app uses **Koin** for dependency injection.

```kotlin
class SampleActivity : DefaultActivity<SampleIntent, SampleResult, SampleUiState, SampleViewModel>() {

    // Use Koin, Hilt, or manual dependency injection to provide the ViewModel
    override val viewModel: SampleViewModel by viewModel()

    @Composable
    override fun SetContentComposable(state: State<SampleUiState>) {
        MvicommonprojectTheme {
            SampleButton(
                shouldShow = state.value.shouldShow,
                onShowClick = { emitIntent(SampleIntent.ToggleImageVisibility) },
            )
        }
    }
}
```

And that's it! The `DefaultActivity` base class will handle the rest of the MVI boilerplate for you.
