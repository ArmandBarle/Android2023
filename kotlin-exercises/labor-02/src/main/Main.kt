package main

import java.time.LocalDate

fun main(args: Array<String>) {

    //If not writen as an object, it is a singleton
//    val dict: IDictionary = ListDictionary()
//    println("Number of words: ${dict.size()}")
//    var word: String?
//    while(true){
//        print("What to find? ")
//        word = readLine()
//        if( word.equals("quit")){
//            break
//        }
//        println("Result: ${word?.let { dict.find(it) }}")
//    }

    //test hashset using dictionary provider
//    val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.ARRAY_LIST)
//    println("Number of words: ${dict.size()}")
//    var word: String?
//    while(true){
//        print("What to find? ")
//        word = readLine()
//        if( word.equals("quit")){
//            break
//        }
//        println("Result: ${word?.let { dict.find(it) }}")
//    }

    //test string.monogram
    println("Jhon Smith".monogram())

    //test string.joinToString
    var listOfStrings = listOf("Jhon", "Smith")
    println(listOfStrings.joinWithParameter("#"))

    //test string.longest
    val stringList = listOf("apple", "pear", "strawberry", "melon")
    println(stringList.longest())

    var dates = mutableListOf<Date>()
    while (dates.size < 10) {
        //generate random values
        val year = (1..LocalDate.now().year).random()
        val month = (1..12).random()
        val day = (1..31).random()
        val date = Date(year,month,day)
        if (date.checkValidDate() && !dates.contains(date)) {
            dates.add(date)
        }
        else {
            println(date)
        }
    }
    dates.forEach() {
        println(it)
    }
    println("Sorted:")
    dates.sort()
    dates.forEach() {
        println(it)
    }
    println("Reverse:")
    dates.reverse()
    dates.forEach() {
        println(it)
    }
    println("Custom(days):")
    dates.sortBy { it.day }
    dates.forEach() {
        println(it)
    }
}

//find the monigram of a name using split and map
fun String.monogram(): String {
    return this.split(" ").map { it.first() }.joinToString("")
}

//Define a compact extension function that returns the elements of a strings’ list joined by
//a given separator!
fun List<String>.joinWithParameter(separator: String): String {
    return this.joinToString(separator)
}

//Define a compact extension function for a strings’ list that returns the longest string!
fun List<String>.longest(): String? {
    return this.maxByOrNull { it.length }
}