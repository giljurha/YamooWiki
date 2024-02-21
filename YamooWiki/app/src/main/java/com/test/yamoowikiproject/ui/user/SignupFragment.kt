package com.test.yamoowikiproject.ui.user

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.yamoowikiproject.databinding.FragmentSignupBinding
import com.test.yamoowikiproject.db.YamooWikiDatabase
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.ui.main.FragmentType
import com.test.yamoowikiproject.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignupFragment : Fragment() {

    lateinit var fragmentSignupBinding: FragmentSignupBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var database: YamooWikiDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)
        return fragmentSignupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSignupBinding.confirmButton.setOnClickListener {
            confirm()
        }
    }

    private fun confirm() {
        with(fragmentSignupBinding) {
            val userId: String = userIdInput.text.toString()
            val userNickName: String = userNickNameInput.text.toString()
            val userPassword: String = userPasswordInput.text.toString()
            val userPasswordCheck: String = userPasswordInputCheck.text.toString()


            if (userId.isEmpty()) {
                showDialog("로그인 오류", "아이디를 입력해주세요")
                return
            }

            if (userNickName.isEmpty()) {
                showDialog("닉네임 오류", "닉네임을 입력해주세요")
                return
            }

            if (userPassword.isEmpty() || userPasswordCheck.isEmpty() || userPassword != userPasswordCheck) {
                showDialog("비밀번호 오류", "비밀번호를 확인해주세요")
                return
            }


            val userModel = UserEntity(
                userId = userId,
                userNickName = userNickName,
                userPassword = userPassword
            )
            CoroutineScope(Dispatchers.IO).launch {
                YamooWikiDatabase.getInstance(requireContext()).getUserDao().insertUser(userModel)
            }
            mainViewModel.fragmentDestination.value = FragmentType.LOGIN
        }
    }

    private fun showDialog(title: String, message: String) {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
            /* TODO: 동작 */
        }
        builder.show()
    }
}


