package main

class ItemController(private val itemService: ItemService) {

    fun quiz(numberOfItems: Int) {
        val questions: List<Item> = itemService.selectRandomItems(numberOfItems)
        var numberOfCorrectAnsweres: Int = 0

        for ((i, q) in questions.withIndex()) {
            println("${i + 1}. ${q.question}")
            for ((j, a) in q.answers.withIndex()) {
                println("${j + 1}) $a")
            }
            print("Answer: ")
            val answer: String? = readLine()
            if (answer == q.correct.toString()) {
                println("Correct")
                numberOfCorrectAnsweres++
            } else {
                println("Wrong")
            }
        }

        println("You got $numberOfCorrectAnsweres out of $numberOfItems correct")
    }
}