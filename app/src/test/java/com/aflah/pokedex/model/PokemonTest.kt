package com.aflah.pokedex.model

import com.aflah.lib_core_test.BaseTest
import com.aflah.lib_core_test.shouldBe
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Before
import org.junit.Test

class PokemonTest : BaseTest() {

    @MockK
    private val fakeId = fake.anInt()
    private val fakeHeight = 2
    private val fakeWeight = 50
    private val fakeName = fake.aString()
    private val fakeUrl = "https://pokeapi.co/api/v2/pokemon/1/"
    private val fakeTypes = listOf(mockk<Pokemon.TypeResponse>())
    private val fakeHp = fake.anInt()
    private val fakeAttack = fake.anInt()
    private val fakeDefense = fake.anInt()
    private val fakeSpeed = fake.anInt()
    private val fakeExp = fake.anInt()

    private lateinit var mockModel: Pokemon

    @Before
    override fun setup() {
        super.setup()
        mockModel = spyk(
            Pokemon(
                id = fakeId,
                height = fakeHeight,
                weight = fakeWeight,
                name = fakeName,
                url = fakeUrl,
                types = fakeTypes,
                hp = fakeHp,
                attack = fakeAttack,
                defense = fakeDefense,
                speed = fakeSpeed,
                exp = fakeExp
        ))
    }

    @Test
    fun `verify data is correctly when object is injected`() {
        //then
        with(mockModel) {
            id shouldBe fakeId
            height shouldBe fakeHeight
            weight shouldBe fakeWeight
            name shouldBe fakeName
            url shouldBe fakeUrl
            types shouldBe fakeTypes
            hp shouldBe fakeHp
            attack shouldBe fakeAttack
            defense shouldBe fakeDefense
            speed shouldBe fakeSpeed
            exp shouldBe fakeExp
        }
    }

    @Test
    fun `verify getPokemonId returns correctly when called`() {
        //inject
        val expectedResult = 1

        //when
        val actualResult = mockModel.getPokemonId()

        //then
        actualResult shouldBe expectedResult
    }

    @Test
    fun `verify getImageUrl returns correctly when called`() {
        //inject
        val expectedResult = "https://pokeres.bastionbot.org/images/pokemon/1.png"

        //when
        val actualResult = mockModel.getImageUrl()

        //then
        actualResult shouldBe expectedResult
    }

    @Test
    fun `verify getPokemonUrl returns correctly when called`() {
        //inject
        val fakePokemonId = fake.anInt()
        val expectedResult = "https://pokeapi.co/api/v2/pokemon/$fakePokemonId/"

        //when
        val actualResult = mockModel.getPokemonUrl(fakePokemonId)

        //then
        actualResult shouldBe expectedResult
    }
}