fun main() {
    // Luodaan satunnainen numero väliltä -10 - 10
    val randomNumber = (-10..10).random()

    // Tulostetaan satunnainen numero
    println("Numero: $randomNumber")

    // Tarkistetaan, onko numero negatiivinen, nolla vai positiivinen
    when {
        randomNumber < 0 -> println("Numero on negatiivinen.")
        randomNumber == 0 -> println("Numero on nolla.")
        else -> println("Numero on positiivinen.")
    }
}
