package chapter6

/**
 * 코틀린에서 SAM 인터페이스 지원
 *
 * 함수 타입을 지원하지 ㅇ낳는 언어의 메서드 하나짜리 인터페이스(단일 추상 메서드 인터페이스)
 */

/**
 * 코틀린은 자바에 의존하고 있기 때문에 자바의 단일 추상 메서드 인터페이스는 특별취급한다.
 *
 * 인수로 자바 SAM 인터페이스를 받아야 한다면 대응하는 함수 타입을 대신 사용 가능하다
 *
 * 자바 SAM 인터페이스는 람다 표현식으로 만들 수 있는 가짜 생성자를 가지고 있다.
 */

/**
 * 코틀린 -> 자바 변환의 경우 함수형 인터페이스를 도입함
 *
 * fun 제어자가 추가로 붙는 추상 메서드 하나짜리 인터페이스
 */

/**
 * // Java - 함수형 인터페이스 정의
 * @FunctionalInterface
 * public interface UserValidator {
 *     boolean validate(String username);
 * }
 *
 * // Java - 이 인터페이스를 사용하는 클래스
 * public class UserService {
 *     public void registerUser(String username, UserValidator validator) {
 *         if (validator.validate(username)) {
 *             System.out.println("사용자 등록 성공: " + username);
 *         } else {
 *             System.out.println("사용자 등록 실패: " + username);
 *         }
 *     }
 * }
 *
 * // Kotlin에서 Java 함수형 인터페이스 사용
 *
 * fun main() {
 *     val userService = UserService()
 *
 *     // 방법 1: 람다로 바로 전달 (SAM 변환!)
 *     userService.registerUser("오전") { username ->
 *         username.length >= 2 && username.length <= 10
 *     }
 *
 *     // 방법 2: 명시적 인터페이스 생성
 *     userService.registerUser("철수", UserValidator { username ->
 *         username.length >= 2
 *     })
 *
 *     // 방법 3: 익명 객체 (Java 스타일)
 *     userService.registerUser("영희", object : UserValidator {
 *         override fun validate(username: String): Boolean {
 *             return username.isNotEmpty()
 *         }
 *     })
 * }
 */