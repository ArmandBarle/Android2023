package main

import java.util.*

fun isPrime(num: Int): Boolean {
    return false
}

fun main(args: Array<String>) {
    println("Hello World!")

    //     feladat 1
    val a = 2
    val b = 5
    var sum1 = 2 + 5
    println(sum1)
    var sum2 = a + b
    println(sum2)
    println("sum3=$sum1")
    println("sum4=${2 + 5}")


//     feladat 2
    val arr = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    println(arr)

    for (day in arr)
        println(day)
//     day: String in arr

//     filter
    println("\nDays starting with T:")
    arr.filter {
        it.startsWith("T")
    }.forEach { println(it) }

    println("\nDays with e:")
    arr.filter {
        it.contains("e")
    }.forEach { println(it) }

    println("\nDays with length 6:")
    arr.filter {
        it.length == 6
    }.forEach { println(it) }

//     feladat 3 !!!to finish!!!
    for (i in 1..100)
        if (isPrime(i)) {
            println("$i is prime\n")
        }

//     feladat 4
    println("\nFeladat4:")
    var string = "hello world"
    println("uncoded string: $string")
    var encoded = encode(string)
    println("coded string: $encoded")
    string = decode(encoded)
    println("original string: $string")
//     feladat 5


//     feladat 6

//     feladat 7

//     feladat 8

}

fun isEvenNumber(a: Int) = a % 2 == 0

fun encode(s: String): String {
    return Base64.getEncoder().encodeToString(s.toByteArray())
}

fun decode(s: String): String {
    return String(Base64.getDecoder().decode(s))
}