package com.aflah.lib_core_test

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import fr.xgouchet.elmyr.junit.JUnitForger
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseTest {
    @get:Rule
    val fake = JUnitForger()

    lateinit var mockContext: Context
    lateinit var mockActivity: Activity
    lateinit var mockFragment: Fragment

    @Before
    open fun setup() {
        mockContext = mockk(relaxed = true)
        mockActivity = mockk(relaxed = true)
        mockFragment = mockk(relaxed = true)
    }

    @After
    open fun tearAndDown() {
        unmockkAll()
    }
}