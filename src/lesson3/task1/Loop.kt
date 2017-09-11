@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import java.lang.Math.*
import java.util.Stack
import jdk.nashorn.tools.ShellFunctions.input
import java.lang.StringBuilder


/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var modifier = 0
    if (n == 0 || n == 1)
        return 1
    if (n % 10 == 0)
        modifier++

    return ceil(log10(abs(n).toDouble())).toInt() + modifier
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 1
    var fib2 = 1

    for (i in 3 .. n) {
        var tmp = fib2
        fib2 += fib1
        fib1 = tmp
    }

    return fib2
}

//Not effective, exp difficulty
/*fun fib(n: Int): Int {
    if (n > 2)
        return fib(n - 1) + fib(n - 2)
    else
        return 1
}*/

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {  //very slow, but im lazy
    for (i in max(m, n) .. m * n)
        if (i % m == 0 && i % n == 0)
            return i
    return m * n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2 .. n / 2)
        if (n % i == 0)
            return i
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n / 2 downTo 1)
        if (n % i == 0)
            return i
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var min = min(m, n)

    var maxNOD = 1
    for (i in 2..min)
        if (m % i == 0 && n % i == 0 && i > maxNOD) maxNOD = i
    return maxNOD == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for(i in m .. n)
        if (sqrt(i.toDouble()) == ceil(sqrt(i.toDouble())))
            return true
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var counter = 1
    var sin = 0.0
    var enchX = x
    var modifier = -1

    if (enchX < 0)
        modifier = 1

    while (abs(enchX) > 2 * PI)
        enchX += 2 * PI * modifier

    while (pow(enchX, counter.toDouble()) / factorial(counter) > eps) {
        sin += pow(-1.0, (2 - counter/2 % 2).toDouble()) * pow(enchX, counter.toDouble()) / factorial(counter)
        counter += 2
    }

    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var counter = 0
    var cos = 0.0
    var enchX = x
    var modifier = -1

    if (enchX < 0)
        modifier = 1

    while (abs(enchX) > 2 * PI)
        enchX += 2 * PI * modifier

    while (pow(enchX, counter.toDouble()) / factorial(counter) > eps) {
        cos += pow(-1.0, (2 - counter / 2 % 2).toDouble()) * pow(enchX, counter.toDouble()) / factorial(counter)
        counter += 2
    }

    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var num = n
    var finum: Long
    var exp = digitNumber(n)
    finum = 0

    while (exp != 0) {
        finum += (num % 10) * pow(10.0, exp--.toDouble()).toLong()
        num /= 10
    }

    return (finum / 10).toInt()
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = n
    var digit = n % 10

    while (num != 0) {
        if (num % 10 != digit)
            return true
        num /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var str = StringBuilder("")
    for (i in 1 .. n) {
        str.append((i * i).toString())
    }

    return str[n - 1].toString().toInt()
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var str = StringBuilder("")

    for (i in 1 .. n) {
        str.append(fib(i))
    }

    return str[n - 1].toString().toInt()
}
