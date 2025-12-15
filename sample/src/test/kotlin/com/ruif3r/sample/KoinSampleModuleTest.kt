package com.ruif3r.sample

import com.ruif3r.sample.di.sampleModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class KoinSampleModuleTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun `test Koin module`() {
        sampleModule.verify()
    }
}