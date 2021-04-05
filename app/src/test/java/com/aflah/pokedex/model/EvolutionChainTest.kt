package com.aflah.pokedex.model

import com.aflah.lib_core_test.BaseTest
import com.aflah.lib_core_test.shouldBe
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Before
import org.junit.Test

class EvolutionChainTest : BaseTest() {

    @MockK
    private val fakeIsBaby = fake.aBool()
    private val mockPokemon = mockk<Pokemon>()
    private val mockEvolveTo = listOf(mockk<EvolutionChain>())

    private lateinit var mockModel: EvolutionChain

    @Before
    override fun setup() {
        super.setup()
        mockModel = spyk(
            EvolutionChain(
                isBaby = fakeIsBaby,
                species = mockPokemon,
                evolveTo = mockEvolveTo
            )
        )
    }

    @Test
    fun `verify data is correctly when object is injected`() {
        //then
        with(mockModel) {
            isBaby shouldBe fakeIsBaby
            species shouldBe mockPokemon
            evolveTo shouldBe mockEvolveTo
        }
    }
}