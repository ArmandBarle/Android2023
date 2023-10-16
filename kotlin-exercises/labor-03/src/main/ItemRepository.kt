package main

import java.io.File

class ItemRepository {
    private val items = mutableListOf<Item>()

    init {
        val lines = File("C:\\Egyetem\\3.1felev\\Android\\Android2023\\kotlin-exercises\\labor-03\\src\\resources\\quiz.txt").useLines { it.toList() }

        for (i in lines.indices step 6) {
            val question = lines[i]
            val answers1 = lines[i + 1]
            val answers2 = lines[i + 2]
            val answers3 = lines[i + 3]
            val answers4 = lines[i + 4]
            val correct = lines[i + 5].toInt()

            save(Item(question, listOf(answers1, answers2, answers3, answers4), correct))
        }
    }

    fun save(item: Item) {
        items.add(item)
    }

    fun randomItem(): Item {
        return items.random()
    }

    fun size(): Int {
        return items.size
    }
}