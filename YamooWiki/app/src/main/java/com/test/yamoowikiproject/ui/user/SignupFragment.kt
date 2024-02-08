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
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.ui.main.FragmentType
import com.test.yamoowikiproject.viewmodel.MainViewModel


class SignupFragment : Fragment() {

    lateinit var fragmentSignupBinding: FragmentSignupBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)
        return fragmentSignupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSignupBinding.run{
            nextProfileBtn.setOnClickListener {
                next()
            }
        }
    }

    fun next() {
        fragmentSignupBinding.run {
            val userId = userIdInput.text.toString()
            val userNickName = userNickNameInput.text.toString()
            val userPw = userPwInput.text.toString()
            val userPwChk = userPwChkInput.text.toString()
            val userPhone = userPhoneInput.text.toString()
            val userEmail = userEmailInput.text.toString()

            if (userId.isEmpty()) {
                val builder = MaterialAlertDialogBuilder(requireContext())
                builder.setTitle("로그인 오류")
                builder.setMessage("아이디를 입력해주세요")
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                /* TODO: 동작 */
                }
                builder.show()
                return
            }

            if (userNickName.isEmpty()) {
                val builder = MaterialAlertDialogBuilder(requireContext())
                builder.setTitle("닉네임 오류")
                builder.setMessage("닉네임을 입력해주세요")
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                /* TODO: 동작 */
                }
                builder.show()
                return
            }

            if (userPw.isEmpty() || userPwChk.isEmpty() || userPw != userPwChk) {
                val builder = MaterialAlertDialogBuilder(requireContext())
                builder.setTitle("비밀번호 오류")
                builder.setMessage("비밀번호를 확인해주세요")
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                /* TODO: 동작 */
                }
                builder.show()
                return
            }

            if (userPhone.isEmpty()) {
                val builder = MaterialAlertDialogBuilder(requireContext())
                builder.setTitle("인증번호 오류")
                builder.setMessage("인증번호를 확인해주세요")
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                /* TODO: 동작 */
                }
                builder.show()
                return
            }

            if (userEmail.isEmpty()) {
                val builder = MaterialAlertDialogBuilder(requireContext())
                builder.setTitle("이메일 오류")
                builder.setMessage("이메일을 확인해주세요")
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                /* TODO: 동작 */
                }
                builder.show()
                return
            }

            val userModel = User(userId, userNickName, userPw, userPhone)
            val bundle = Bundle()
            bundle.putParcelable("signInfo", userModel)
            mainViewModel.fragmentDestination.value = FragmentType.PROFILE
        }
    }
}


