package com.test.yamoowikiproject.ui.user.model

enum class SignupErrorState(val title: String, val message: String) {
    ID("아이디 오류", "아이디를 입력해주세요"),
    NICKNAME("닉네임 오류", "닉네임을 입력해주세요"),
    PASSWORD("비밀번호 오류", "비밀번호를 입력해주세요"),
    PROFILE("프로필 오류", "프로필 사진을 넣어주세요"),
    NONE("","")
}