package main

fun main(args: Array<String>) {

    //Test ItemRepository
    val repo: ItemRepository = ItemRepository()
//    println(repo.randomItem())

    val service: ItemService = ItemService(repo)
//    println(service.selectRandomItems(3))

    val itemController: ItemController = ItemController(service)

    var numberOfQuestions: String? = null
    while (true) {

        println("How many questions do you want to play with? (1-10)\n Type *q* to quit.")
        numberOfQuestions = readLine()

        if (numberOfQuestions == "q") {
            return
        }

        try {
            if (numberOfQuestions != null) {
                if (numberOfQuestions.toInt() <= 0 || numberOfQuestions.toInt() > 10) {
                    println("Not between 1 and 10!\nPlease enter a number between 1 and 10.")
                    continue
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("Not a number!Please enter a number between 1 and 10.")
            continue
        }

        itemController.quiz(numberOfQuestions!!.toInt())

    }
}