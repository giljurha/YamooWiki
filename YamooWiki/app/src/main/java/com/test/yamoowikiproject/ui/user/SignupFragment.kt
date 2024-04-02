package com.test.yamoowikiproject.ui.user

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.FragmentSignupBinding
import com.test.yamoowikiproject.db.YamooWikiDatabase
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.ui.main.FragmentType
import com.test.yamoowikiproject.viewmodel.MainViewModel
import com.test.yamoowikiproject.viewmodel.SignupViewModel


class SignupFragment : Fragment() {

    lateinit var fragmentSignupBinding: FragmentSignupBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val signupViewModel: SignupViewModel by activityViewModels()

    private lateinit var database: YamooWikiDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)
        return fragmentSignupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSignupBinding.confirmButton.setOnClickListener {
            signupViewModel.signup(user = confirm(), context = requireContext())
        }
        fragmentSignupBinding.userProfileImage.setOnClickListener {
            selectGallery()
        }

    }

    private fun confirm(): UserEntity {
        with(fragmentSignupBinding) {
            val userId: String = userIdInput.text.toString()
            val userNickName: String = userNickNameInput.text.toString()
            val userPassword: String = userPasswordInput.text.toString()
            val userPasswordCheck: String = userPasswordInputCheck.text.toString()


            val (title: String, message: String) = when {
                userId.isEmpty() -> ("로그인 오류" to "아이디를 입력해주세요")
                userNickName.isEmpty() -> ("닉네임 오류" to "닉네임을 입력해주세요")
                userPassword.isEmpty() || userPasswordCheck.isEmpty() || userPassword != userPasswordCheck ->
                    ("비밀번호 오류" to "비밀번호를 확인해주세요")
                uri == null -> ("프로필 오류" to "프로필 사진을 넣어주세요")

                else -> ("회원가입" to "회원가입이 완료되었습니다")
            }
            showDialog(title = title, message = message)
            mainViewModel.changeFragmentType(fragmentType = FragmentType.LOGIN)
            // 네임드아규먼츠
            val user = UserEntity(
                userNickName = userNickName,
                userPassword = userPassword,
                userId = userId,
                userImage = uri.toString()
            )
            return user
        }
    }


    private fun showDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(requireContext()).run {
            setTitle(title)
            setIcon(R.drawable.baseline_person_outline_24)
            setMessage(message)
            setPositiveButton("확인", null)
            setNegativeButton("취소", null)
            setNeutralButton("닫기") { dialog, which ->

            }
            setCancelable(true)
            show()
        }.setCanceledOnTouchOutside(true)
    }

    private fun selectGallery() {
        val readPermission: Int = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (readPermission == PackageManager.PERMISSION_GRANTED) {
            setImageResult()
        } else {
            requestPermission()
        }
    }

    private fun setImageResult() {
        val intent  = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            "image/*"
        )
        imageResult.launch(intent)
    }

    private fun requestPermission() {
        permissionDialog.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private val imageResult = registerForActivityResult( //oncreate에서만 정의가능 -> 모듈화 불가능
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            uri = it.data?.data
            Glide.with(this)
                .load(it.data?.data)
                .override(200, 200)
                .into(fragmentSignupBinding.userProfileImage)
        }
    }

    private val permissionDialog = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            setImageResult()
        } else {
            requestPermission()
        }
    }

    private var uri: Uri? = null
}
