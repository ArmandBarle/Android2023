package main

class ItemController(private val itemService: ItemService) {

    fun quiz(numberOfItems: Int){
        val questions : List<Item> =itemService.selectRandomItems(numberOfItems)
        var numberOfCorrectAnsweres : Int = 0

        for (q in questions){
            println("${q.question}  ${q.answers}")
            val answer : String? = readLine()
            if (answer == q.correct.toString()){
                println("Correct")
                numberOfCorrectAnsweres++
            }
            else {
                println("Wrong")
            }
        }

        println("You got $numberOfCorrectAnsweres out of $numberOfItems correct")
    }
}