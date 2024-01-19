package com.kotlin.coroutine.domain

import kotlinx.coroutines.delay

class User(
    val userId: Long,
)

class UserDto(
    val profile: Profile,
    val image: Image
)

class Profile

class Image
class UserProfileRepository {
    suspend fun findProfile(userId: Long, continuation: Continuation) {
        delay(100)

        // resumeWith 시 profile 을 직접 리턴하는게 아니라 resumeWith 의 파라미터인 data 에 넘겨버린다.
        // 때문에 실제 profile 을 반환할 필요가 없다.
        continuation.resumeWith(Profile())
    }
}

class UserImageRepository {
    suspend fun findImage(profile: Profile, continuation: Continuation) {
        delay(100)

        continuation.resumeWith(Image())
    }
}

// label 기능을 갖고 있는 인터페이스
interface Continuation {
    suspend fun resumeWith(data: Any?) // findUser 를 다시 실행시키기 위한 함수
}

class UserService {
    private val userProfileRepository = UserProfileRepository()
    private val userImageRepository = UserImageRepository()

    // Continuation 을 상속 받은 추상 클래스
    private abstract class FindUserContinuation() : Continuation {
        var label = 0
        var profile: Profile? = null
        var image: Image? = null
    }

    suspend fun findUser(userId: Long, continuation: Continuation?): UserDto {
        // findUser 가 계속 호출되면 label 이 0 으로 계속 초기화되는 일종의 무한루프에 빠진다.
        // 이를 해결하기 위해 elvis 연산자를 활용해 continuation 이 null 이 아닐 때만 새로만들고,
        // 이미 있는 경우 기존에 있는 객체를 활용한다.
        val sm = continuation as? FindUserContinuation ?: object : FindUserContinuation() {
            override suspend fun resumeWith(data: Any?) {
                when (label) {
                    0 -> {
                        profile = data as Profile // 파라미터로 넘어온 data 를 Profile 로 변환
                        label = 1
                    }
                    1 -> {
                        image = data as Image // 파라미터로 넘어온 data 를 Image 로 변환
                        label = 2
                    }
                }
                findUser(userId, this)
            }
        }

        when (sm.label) {
            0 -> {
                // 0단계 - 초기 시작
                println("프로필을 가져오겠습니다.")
                userProfileRepository.findProfile(userId, sm)
            }
            1 -> {
                // 1단계 - 1차 중단 후 재시작
                println("이미지를 가져오겠습니다.")
                userImageRepository.findImage(sm.profile!!, sm)
            }
        }
        return UserDto(sm.profile!!, sm.image!!)
    }
}

suspend fun main() {
    val service = UserService()
    println(service.findUser(1L, null))
}
