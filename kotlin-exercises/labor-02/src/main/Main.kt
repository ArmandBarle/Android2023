package main

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
}

//find the monigram of a name using split and map
fun String.monogram(): String {
    return this.split(" ").map { it.first() }.joinToString("")
}
