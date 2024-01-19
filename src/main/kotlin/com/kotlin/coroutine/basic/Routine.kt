package com.kotlin.coroutine.basic

fun main() {
    println("start")
    newRoutine()
    println("end")
}

fun newRoutine() {
    val num1 = 1
    val num2 = 2
    println("${num1 + num2}")
}