package chapter1

/**
 * 코틀린을 사용한 함수형 프로그래밍
 *
 * 함수형 프로그래밍의 특징
 *
 * - 함수를 객체처럼 다룸
 * - 고차 함수
 * - 데이터 불변성
 * - 구문을 표현식으로 사용함
 * - 지연 연산
 * - 패턴 매칭
 * - 재귀 함수 호출
 */

/**
 * 함수를 객체처럼 다뤄야 하는 이유
 *
 * 다음 두 함수를 객체처럼 다루지 못하면 공통 부분을 추출하기 힘듬!
 *
 * 사소한 함수의 인터페이스까지 정의해야 함
 */

fun sum(a:Int, b:Int): Int {
    var sum = 0
    for (i in a..b) {
        sum += i
    }
    return sum
}

fun product(a: Int, b: Int): Int {
    var product = 1
    for (i in a..b) {
        product *= i
    }
    return product
}


/**
 * 람다 표현식과 함수를 사용해 공통 부분을 추출한 코드
 */

fun sum2(a:Int, b:Int) = fold(a,b,0,{acc, i-> acc + i})

fun product2(a: Int, b: Int) = fold(a,b,1, {acc, i-> acc * i})

fun fold(
    a: Int,
    b:Int,
    initial:Int,
    operation:(Int, Int) -> Int
): Int {
    var acc = initial
    for (i in a..b) {
        acc = operation(acc,i)
    }
    return acc
}

/**
 * 함수 참조 사용 재정의
 */

fun sum3(a: Int, b: Int) = (a..b).fold(0, Int::plus)

fun product3(a: Int,b:Int) = (a..b).fold(1, Int::times)

