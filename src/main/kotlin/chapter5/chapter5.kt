package chapter5

/**
 * 함수 참조
 *
 * 객체로 사용 가능한 함수가 필요할 때
 *
 * 1. 람다로 새로운 객체 생성하기
 * 2. 기존의 함수 참조하기
 */

/**
 * 최상위 함수 참조
 *
 * kotlin- reflect 의존성 추가 시
 *
 * 함수 참조 이용, 참조된 함수 정보 확인 가능
 */

/**
 * 함수 참조는 함수 타입을 구현. 실제 리터럴로 사용될 수 있음.
 *
 * 실제 리플렉션이 아니기 때문에 람다 표현식과 비교해 성능 부하도 적음
 */

fun add(a: Int, b: Int) = a + b

fun main() {
    val f: (Int, Int) -> Int = ::add
}

/**
 * 메서드 참조
 *
 * 리시버의 타입 명시, ::메서드 이름
 */

data class Number(val num: Int) {
    fun toFloat(): Float = num.toFloat()
    fun times(n: Int): Number = Number(num * n)
}

fun main2() {
    val numberObject = Number(10)

    val float: (Number) -> Float = Number::toFloat
}

/**
 * 제네릭의 경우 인수 명시
 */
