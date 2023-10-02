package main

import main.IDictionary.Companion.FILE_PATH
import java.io.File

object ListDictionary : IDictionary {
    private val words = mutableListOf<String>()

    init {
        File(FILE_PATH).forEachLine {
            words.add(it)
        }
    }
    override fun add(word: String): Boolean {
        return words.add(word)
    }

    override fun find(word: String): Boolean {
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }
}