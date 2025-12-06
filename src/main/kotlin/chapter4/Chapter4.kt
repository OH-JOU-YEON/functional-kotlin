package chapter4

/**
 * 람다 표현식
 */

/**
 * 중괄호 안에 정의
 */

fun main() {
    val f: () -> Unit = {}
    f()
    // 또는 f.invoke()
}

/**
 * 코틀린 구조에 해당하지 않는 중괄호는 전부 람다 표현식!!
 */

fun main2() {
    {
        println("이 내용은 출력되지 않습니다")
    }
}

/**
 * 람다 표현식에 매개변수가 있다면 중괄호 안의 내용을 화살표로 분리시켜야 함
 *
 * 람다 표현식은 대체로 어떤 함수의 인수로 정의됨. 일반 함수는 매개변수 타입을 정의해 람다 표현식의 매개변수 타입을 추론할 수 있도록 해주는 게 좋음
 */

// 1. 매개변수가 있는 람다 - 화살표로 분리
val add = { a: Int, b: Int -> a + b }
//          ^^^^^^^^^^^^^^   ^^^^^^
//          매개변수          본문

// 2. 함수의 인수로 람다 사용 - 타입 추론
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

fun main3() {
    // 함수가 매개변수 타입을 정의했으므로 람다에서 타입 생략 가능
    val result = calculate(5, 3) { a, b -> a + b }
    println(result)  // 8

    // 타입을 명시해도 되지만 불필요함
    calculate(5, 3) { a: Int, b: Int -> a * b }
}

/**
 * 무시 되는 매개변수를 보여주는 자리표시자 _
 */

/**
 * 람다식에서의 구조 분해
 */

fun main4(){
    val scores = mapOf("오전" to 95, "철수" to 80, "영희" to 90)

// 방법 1: 일반 람다
    scores.forEach { entry ->
        println("${entry.key}: ${entry.value}")
    }


// 방법 2: 구조 분해 람다 - 더 깔끔!
    scores.forEach { (name, score) ->
        println("$name: $score")
    }
}


/**
 * 후행 람다
 *
 * 마지막 매개변수가 함수 타입인 함수를 호출하면, 람다 표현식을 괄호 바깥에 정의 가능함
 *
 * 함수 타입이 유일한 매개변수라면 매개변수용 괄호를 생략하고 람다 표현식을 바로 정의할 수도 있음
 */

inline fun <R> run(block: () -> R): R = block()

inline fun repeat(times: Int, block: (Int) -> Unit) {
    for (i in 0 until times) {
        block(i)
    }
}

/**
 * 단일 매개변수의 암묵적 이름 it
 */

var printNumber: (Int) -> Unit = {println(it)}