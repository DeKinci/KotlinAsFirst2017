@file:Suppress("UNUSED_PARAMETER")

package lesson5.task1

import java.lang.String.format

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0 .. 9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateStrToDigit(str: String): String {
    val tes = listOf("января", "февраля", "марта", "апреля", "мая",
            "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val date = str.split(" ")

    try {
        val day = date[0].toInt()
        val mon = tes.indexOf(date[1]) + 1
        val year = date[2].toInt()
        if (day in 1 .. 31 && mon in 1 .. 12 && date.size == 3)
            return format("%02d.%02d.%d", day, mon, year)
    } catch (e: Exception) {
        return ""
    }
    return ""
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    val tes = listOf<String>("января", "февраля", "марта", "апреля", "мая",
            "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val date = digital.split(".")

    try {
        val day = date[0].toInt()
        val mon = date[1].toInt()
        val year = date[2].toInt()
        if (day in 1 .. 31 && mon in 1 .. 12 && date.size == 3)
            return "$day ${tes[mon - 1]} $year"
    } catch (e: Exception) {
        return ""
    }
    return ""
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    if (phone.isEmpty())
        return ""

    val hasPlus = phone.first() == '+'
    val enhPhone = phone.replace(Regex("[-+() ]"), "")

    if (!enhPhone.matches(Regex("[0-9]+")))
        return ""

    return (if (hasPlus) "+" else "") + enhPhone

}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    if (!jumps.matches(Regex("[- %0-9]+")))
        return -1

    val jumpsList = jumps.split(" ")
    var maxJump = -1

    for (jumpInfo in jumpsList) {
        if (!jumpInfo.matches(Regex("[0-9]+")))
            continue

        val jump = jumpInfo.toInt()
        if (jump > maxJump)
            maxJump = jump
    }

    return maxJump
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val jumpsArr = jumps.split(" ")
    var maxH = -1

    if (jumpsArr.size % 2 != 0)
        return -1

    for (i in 0 until jumpsArr.size step 2) {
        for (ch in jumpsArr[i])
            if (!Character.isDigit(ch))
                return -1

        for (ch in jumpsArr[i + 1])
            if (ch == '+' || ch == '%' || ch == '-') {
                val jump = jumpsArr[i].toInt()

                if (ch == '+' && jump > maxH)
                    maxH = jump
            } else return -1
    }

    return maxH
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (expression.isEmpty())
        throw IllegalArgumentException()

    val enhExpression = expression.trim().split(" ")

    var expressionResult: Int
    try {
        expressionResult = enhExpression[0].toInt()
    } catch (e: Exception) {
        throw IllegalArgumentException()
    }

    for (i in 2 until enhExpression.size step 2) {
        try {
            expressionResult += when (enhExpression[i - 1]) {
                "+" -> enhExpression[i].toInt()
                "-" -> (-enhExpression[i].toInt())
                else -> throw IllegalArgumentException()
            }
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
    }

    return expressionResult
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    if (str.isEmpty())
        return -1

    val strArr = str.trim().split(" ")
    var index = 0

    for (i in 0 until strArr.size - 1) {
        if (strArr[i].toLowerCase() == strArr[i + 1].toLowerCase())
            return index
        index += strArr[i].length + 1
    }

    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    if (description.isEmpty())
        return ""

    val goods = description.split(";")
    var mostExpensiveStuff = ""
    var mostExpensiveStuffCost = - 1.0

    for (stuff in goods) {
        val pricedStuff = stuff.trim().split(" ")

        try {
            if (mostExpensiveStuffCost < pricedStuff[1].toDouble()) {
                mostExpensiveStuffCost = pricedStuff[1].toDouble()
                mostExpensiveStuff = pricedStuff[0]
            }
        } catch (e: Exception) {
            return ""
        }
    }

    return mostExpensiveStuff
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val romans = listOf<String>("M", "CM", "D", "CD", "C", "XC", "L", "XL",
            "X", "IX", "V", "IV", "I").withIndex()
    val arabs = listOf<Int>(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)

    val romeNum = StringBuilder(roman)
    //Another one bites the string
    var biter = 0
    var arabNum = 0

    for ((romeIndex, romeStr) in romans)
        while (romeNum.isNotEmpty())
            if (romeNum.indexOf(romeStr, biter) == biter) {
                arabNum += arabs[romeIndex]
                biter += romeStr.length

                if (biter == romeNum.length)
                    return arabNum
            } else break

    return -1
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {

    //Commands to use instead of chars
    val mvR = '>'
    val mvL = '<'
    val inc = '+'
    val dec = '-'
    val bgn = '['
    val end = ']'
    val spc = ' '

    //Syntax check
    if (!commands.matches(Regex("[-+><\\[\\] ]*")))
        throw IllegalArgumentException()

    var bktCounter = 0
    for (ch in commands)
        bktCounter += when {
            ch == bgn && bktCounter >= 0 -> 1
            ch == end -> -1
            else -> 0
        }
    if (bktCounter != 0)
        throw IllegalArgumentException()

    //Program execution
    val data = MutableList(cells, { 0 })
    var dataPointer = cells / 2
    var charPointer = 0
    var commandsExecuted = 0

    while (commandsExecuted < limit && charPointer < commands.length) {
        when (commands[charPointer]) {
            mvR -> if (++dataPointer >= cells)
                throw IllegalStateException()
            mvL -> if (--dataPointer < 0)
                throw IllegalStateException()

            inc -> data[dataPointer]++
            dec -> data[dataPointer]--

            bgn -> {
                if (data[dataPointer] == 0) {
                    var i = 1
                    while (i > 0) {
                        val ch = commands[++charPointer]
                        if (ch == bgn)
                            i++
                        if (ch == end)
                            i--
                    }
                }
            }
            end -> {
                if (data[dataPointer] != 0) {
                    var i = 1
                    while (i > 0) {
                        val ch = commands[--charPointer]
                        if (ch == bgn)
                            i--
                        if (ch == end)
                            i++
                    }
                }
            }

            spc -> {
            }
        }
        charPointer++
        commandsExecuted++
    }

    return data.toList()
}