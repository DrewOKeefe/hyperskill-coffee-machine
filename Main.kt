package machine

import kotlin.system.exitProcess

var water = 400
var milk = 540
var beans = 120
var cups = 9
var money = 550

enum class Coffee(val water: Int, val milk: Int, val beans: Int, val cost: Int) {
    ESPRESSO(water = 250, milk = 0, beans = 16, cost = 4),
    LATTE(water = 350, milk = 75, beans = 20, cost = 7),
    CAPPUCCINO(water = 200, milk = 100, beans = 12, cost = 6)
}

fun printContents() {
    println("""
        This coffee machine has:
        $water ml of water
        $milk ml of milk
        $beans g of coffee beans
        $cups disposable cups
        $money of money
        
    """.trimIndent())
}

fun buy() {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
    val input = readln()
    var drink = Coffee.CAPPUCCINO
    when (input) {
        "back" -> return
        "1" -> drink = Coffee.ESPRESSO
        "2" -> drink = Coffee.LATTE
        "3" -> drink = Coffee.CAPPUCCINO
        else -> {
            println("invalid entry")
            buy()
        }
    }
    if (water >= drink.water && milk >= drink.milk && beans >= drink.beans && cups > 0) {
        println("I have enough resources, making you a coffee!")
        water -= drink.water
        milk -= drink.milk
        beans -= drink.beans
        money += drink.cost
        cups -= 1
    } else println("not enough resources")

}

fun fill() {
    println("Write how many ml of water the coffee machine has:")
    water += readln().toInt()
    println("Write how many ml of milk the coffee machine has:")
    milk += readln().toInt()
    println("Write how many g of coffee beans the coffee machine has:")
    beans += readln().toInt()
    println("Write how many cups of coffee you will need:")
    cups += readln().toInt()
}

fun main() {
    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        when (readln()) {
            "buy" -> buy()
            "fill" -> fill()
            "take" -> {
                println("I gave you $money"); money = 0
            }
            "exit" -> exitProcess(0)
            "remaining" -> printContents()
        }
    }
}