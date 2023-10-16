package main

fun main(args: Array<String>) {

    //Test ItemRepository
    val repo : ItemRepository = ItemRepository()
//    println(repo.randomItem())

    val service : ItemService = ItemService(repo)
//    println(service.selectRandomItems(3))

    val itemController : ItemController = ItemController(service)
    itemController.quiz(4)


}