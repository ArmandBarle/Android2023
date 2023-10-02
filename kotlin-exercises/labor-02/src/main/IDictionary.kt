package main

interface IDictionary {
    fun add(word: String):Boolean
    fun find(word: String):Boolean
    fun size():Int

    companion object {
        const val FILE_PATH = "C:\\Egyetem\\3.1felev\\Android\\Android2023\\kotlin-exercises\\labor-02\\src\\resources\\dict.txt"
    }
}