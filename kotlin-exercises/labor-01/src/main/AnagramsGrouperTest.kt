package main

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

class AnagramsGrouperTest {
    @Test
    fun threeGroupsAllLowerCase() {
        val anagrams = groupAnagrams(listOf("eat", "tea", "tan", "ate", "nat",
                "bat").toTypedArray())
        assertEquals(3, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat", "tea", "ate")))
        assertTrue(anagrams.contains(listOf("tan", "nat")))
        assertTrue(anagrams.contains(listOf("bat")))
    }

    @Test
    fun threeGroupsSomeUpperCase() {
        val anagrams = groupAnagrams(listOf("eat", "tEa", "Tan", "atE", "NAT",
                "bat").toTypedArray())
        assertEquals(3, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat", "tea", "ate")))
        assertTrue(anagrams.contains(listOf("tan", "nat")))
        assertTrue(anagrams.contains(listOf("bat")))
    }

    @Test
    fun validOneGroup() {
        val anagrams = groupAnagrams(listOf("eat").toTypedArray())
        assertEquals(1, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat")))
    }

    @Test
    fun noGroup() {
        val anagrams = groupAnagrams(emptyList < String > ().toTypedArray())
        assertEquals(0, anagrams.size)
    }

}