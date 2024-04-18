package com.test.yamoowikiproject.ui.user.model

enum class SignupErrorState(val message: String) {
    ID("아이디를 확인해주세요"),
    NICKNAME("닉네임을 확인해주세요"),
    PASSWORD("비밀번호를 확인해주세요"),
    PROFILE("프로필 사진을 확인해주세요"),
    DUPLICATEDID("중복된 아이디가 있습니다"),
    DUPLICATEDNICKNAME("중복된 닉네임이 있습니다"),
    NOTERROR("가입 가능")
}