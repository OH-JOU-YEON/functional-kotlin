package chapter8

/**
 *컬렉션 처리
 */

/**
 * forEach, onEach
 *
 * forEach는 unit를 반환 > 최종 연산
 *
 * 컬렉션 처리 중 원소 각각을 처리하고 싶을 때 onEach
 *
 * 책의 예제 코드가 시원찮아서 클로드의 도움을 받음
 */

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)

    // forEach - 반환값 없음
    numbers.forEach { println(it) }
    // 출력: 1 2 3 4 5
    // 반환: Unit (void)

    println("---")

    // onEach - 체이닝 가능
    val result = numbers
        .onEach { println("처리 중: $it") }
        .filter { it > 2 }
        .onEach { println("필터 후: $it") }
        .map { it * 2 }

    println("최종 결과: $result")
    // 출력:
    // 처리 중: 1
    // 처리 중: 2
    // 처리 중: 3
    // 필터 후: 3
    // 처리 중: 4
    // 필터 후: 4
    // 처리 중: 5
    // 필터 후: 5
    // 최종 결과: [6, 8, 10]

    println("---")

    // 실용 예제: 로깅하면서 처리
    val students = listOf("오전", "철수", "영희")

    students
        .onEach { println("학생 처리: $it") }  // 로깅
        .map { "$it 님" }
        .onEach { println("변환 완료: $it") }  // 중간 로깅
        .forEach { println("최종: $it") }      // 최종 출력

    // forEach는 체이닝 불가
    // students.forEach { println(it) }.map { it * 2 }  // ❌ 에러!

    // onEach는 체이닝 가능
    students.onEach { println(it) }.map { it.uppercase() }  // ✅ 가능!
}

/**
 * filter
 *
 * 걸러 내는 것이 아니라 남기는 것. 자바 stream의 filter과 동일
 *
 * 만족하지 않는 원소만 남기는 filterNot
 */

/**
 * map
 *
 * stream map 이랑 동일
 *
 * mapNotNull
 *
 * map이랑 같은데 null값을 무시
 */

/**
 * flatMap
 *
 * map + 평탄화(flatten)
 */

fun main2() {
    // 기본 예제: 문자열을 문자 리스트로
    val words = listOf("Hi", "Bye")

    // map - 중첩된 리스트 반환
    val mapped = words.map { it.toList() }
    println("map 결과: $mapped")
    // [[H, i], [B, y, e]]

    // flatMap - 평탄화된 리스트 반환
    val flatMapped = words.flatMap { it.toList() }
    println("flatMap 결과: $flatMapped")
    // [H, i, B, y, e]

    println("---")

    // 실용 예제 1: 학생별 과목 점수
    data class Student(val name: String, val scores: List<Int>)

    val students = listOf(
        Student("오전", listOf(95, 90, 88)),
        Student("철수", listOf(80, 85, 82)),
        Student("영희", listOf(92, 88, 90))
    )

    // map - 점수 리스트의 리스트
    val allScoresNested = students.map { it.scores }
    println("map: $allScoresNested")
    // [[95, 90, 88], [80, 85, 82], [92, 88, 90]]

    // flatMap - 모든 점수를 하나의 리스트로
    val allScoresFlat = students.flatMap { it.scores }
    println("flatMap: $allScoresFlat")
    // [95, 90, 88, 80, 85, 82, 92, 88, 90]

    val averageScore = allScoresFlat.average()
    println("전체 평균: $averageScore")

    println("---")

    // 실용 예제 2: 숫자 범위 생성
    val numbers = listOf(1, 2, 3)

    // map - 범위의 리스트
    val ranges = numbers.map { 1..it }
    println("map: $ranges")
    // [1..1, 1..2, 1..3]

    // flatMap - 모든 숫자를 펼침
    val allNumbers = numbers.flatMap { 1..it }
    println("flatMap: $allNumbers")
    // [1, 1, 2, 1, 2, 3]

    println("---")

    // 실용 예제 3: 문장을 단어로 분리
    val sentences = listOf(
        "코틀린은 재미있다",
        "자바도 좋다",
    )

    // flatMap - 모든 단어를 하나의 리스트로
    val allWords = sentences.flatMap { it.split(" ") }
    println("모든 단어: $allWords")
    // [코틀린은, 재미있다, 자바도, 좋다]

    // 활용: 가장 긴 단어 찾기
    val longestWord = allWords.maxByOrNull { it.length }
    println("가장 긴 단어: $longestWord")
}

/**
 * fold: 누산기
 *
 * 명시한 연산을 수행하여 모든 우너소를 하나의 변수로 취합한다. 초깃값을 지정하는 게
 * reduce와의 차이다
 *
 * 거꾸로 foldRight
 */

fun main3() {
    val numbers = listOf(1, 2, 3, 4, 5)

// fold - 최종 결과
    numbers.fold(0) { acc, n -> acc + n }
// → 15

// runningFold - 전체 과정
    numbers.runningFold(0) { acc, n -> acc + n }
// → [0, 1, 3, 6, 10, 15]
}

/**
 * reduce > 초깃값이 필요하지 않음 첫 번째 원소가 초깃값
 *
 * 컬렉션이 비어 있으면 예외를 던짐
 *
 * reduceOrNull 사용 가능
 *
 * fold보다 근소하게 빠름
 */