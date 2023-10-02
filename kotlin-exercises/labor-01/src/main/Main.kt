package main

import java.util.*
import kotlin.random.Random
import main.AnagramsGrouperTest
import org.junit.Test

fun isPrime(number: Int): Boolean {
    if (number <= 1) return false
    if (number <= 3) return true
    if (number % 2 == 0 || number % 3 == 0) return false

    for (i in 5 until Math.sqrt(number.toDouble()).toInt() + 1 step 6) {
        if (number % i == 0 || number % (i + 2) == 0) return false
    }

    return true
}


fun main(args: Array<String>) {
    println("Exercise 1:")
    val a = 2
    val b = 5
    var sum1 = 2 + 5
    println("initializing as addition $sum1")
    var sum2 = a + b
    println("adding two values $sum2")
    println("adding together the numbers inside of the print function sum=${2 + 5}")


//     feladat 2
    println("Exercise 2:")
    val arr = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
//    println(arr)

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

    println("Exercise 3:")
    print("List of prime numbers: ")
    for (i in 1..100)
        if (isPrime(i)) {
            print("$i ")
        }

//     feladat 4
    println("\nExercise 4:")
    var string = "hello world"
    println("uncoded string: $string")
    var encoded = encode(string)
    println("coded string: $encoded")
    string = decode(encoded)
    println("original string: $string")

//     feladat 5
    println("Exercise 5:")
    val random = Random
    val randomNumbers = List(10) { random.nextInt(1, 101) }
    println("List of random numbers: $randomNumbers \nEven numbers:")
    printEvenNumbers(randomNumbers)


//     feladat 6
    println("Exercise 6:")
    val daysOfWeek = listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")

    // Double the elements of a list of integers and print it
    val doubledNumbers = listOf(1, 2, 3, 4, 5).map { it * 2 }
    println("Doubled Numbers: $doubledNumbers")

    // Print the days of the week capitalized
    val capitalizedDays = daysOfWeek.map { it.uppercase(Locale.getDefault()) }
    println("Capitalized Days: $capitalizedDays")

    // Print the first character of each day capitalized
    val firstCharCapitalized = daysOfWeek.map { it.first().uppercaseChar() }
    println("First Character Capitalized: $firstCharCapitalized")

    // Print the length of days
    val dayLengths = daysOfWeek.map { it.length }
    println("Length of Days: $dayLengths")

    // Compute the average length of days (in number of characters)
    val averageLength = dayLengths.average()
    println("Average Length of Days: $averageLength")

//     feladat 7
    println("Exercise 7:")
    // Convert the daysOfWeek immutable list into a mutable one.
    val mutableDaysOfWeek = daysOfWeek.toMutableList()

    // Remove all days containing the letter 'n'.
    mutableDaysOfWeek.removeIf { it.contains('n', ignoreCase = true) }

    // Print the mutable list.
    println(mutableDaysOfWeek)

    // Print each element of the list in a new line together with the index.
    for ((index, day) in mutableDaysOfWeek.withIndex()) {
        println("Item at $index is $day")
    }

    // Sort the result list alphabetically.
    mutableDaysOfWeek.sort()

    // Print the sorted list.
    println(mutableDaysOfWeek)

//     feladat 8
    println("Exercise 8:")
    // Generate an array of 10 random integers between 0 and 100
    val randomIntArray = IntArray(10) { Random.nextInt(0, 101) }

    // Use forEach to print each element of the array in a new line
    randomIntArray.forEach { println(it) }

    // Print the array sorted in ascending order
    val sortedArray = randomIntArray.sortedArray()
    println("Sorted Array: ${sortedArray.joinToString(", ")}")

    // Check whether the array contains any even number
    val containsEven = randomIntArray.any { it % 2 == 0 }
    println("Contains Even Number: $containsEven")

    // Check whether all the numbers are even
    val allEven = randomIntArray.all { it % 2 == 0 }
    println("All Numbers Are Even: $allEven")

    // Calculate the average of generated numbers and print it using forEach
    var sum = 0
    randomIntArray.forEach { sum += it }
    val average = sum.toDouble() / randomIntArray.size
    println("Average: $average")

    //Extra
    println("Extra:")
    val list = listOf("eat", "tea", "tan", "ate", "nat", "bat").toTypedArray()
    val groupedAnagrams = groupAnagrams(list)
    println(groupedAnagrams)

    println("Tests:")
    AnagramsGrouperTest()
}

fun groupAnagrams(anagramList: Array<String>): List<List<String>> {
    //make tuple pairs where 1 value is original and second is sorted
    val anagramWithSortedLetters = anagramList.map {
        Pair(it, it.lowercase(Locale.getDefault()).toCharArray().sorted().joinToString(""))
    }
//    println("$anagramList\n$anagramWithSortedLetters")

    //check which elements are sorted the exactly same way
    val groupedAnagrams = anagramWithSortedLetters.groupBy { it.second }
    println(groupedAnagrams)

    //create a list of the same anagrams


    return groupedAnagrams.map { groupedAnagrams -> groupedAnagrams.value.map { it.first } }
}

fun printEvenNumbers(numbers: List<Int>) = numbers.filter { it % 2 == 0 }.forEach(::println)

fun encode(s: String): String {
    return Base64.getEncoder().encodeToString(s.toByteArray())
}

fun decode(s: String): String {
    return String(Base64.getDecoder().decode(s))
}
