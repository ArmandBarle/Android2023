package main

object TreeSetDictionary : IDictionary {
    private val words = mutableSetOf<String>()

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