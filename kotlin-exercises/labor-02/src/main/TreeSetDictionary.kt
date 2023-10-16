package main

import java.io.File

object TreeSetDictionary : IDictionary {
    private val words = mutableSetOf<String>()

    init {
        File(IDictionary.FILE_PATH).forEachLine {
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