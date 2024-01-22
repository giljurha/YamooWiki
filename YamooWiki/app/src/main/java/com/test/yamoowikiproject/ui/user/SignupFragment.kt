package com.test.yamoowikiproject.ui.user

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.GoogleAuthProvider
import com.test.yamoowikiproject.databinding.FragmentSignupBinding
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.ui.main.MainActivity
import com.test.yamoowikiproject.viewmodel.SignupViewModel


class SignupFragment : Fragment() {

    lateinit var fragmentSignupBinding: FragmentSignupBinding
    lateinit var mainActivity: MainActivity
    lateinit var signupViewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        fragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        signupViewModel = ViewModelProvider(mainActivity)[SignupViewModel::class.java]

        signupViewModel.run {
            userId.observe(mainActivity) {
                fragmentSignupBinding.userIdInput.setText(it)
            }
            userNickName.observe(mainActivity) {
                fragmentSignupBinding.userNickNameInput.setText(it)
            }
            userPw.observe(mainActivity) {
                fragmentSignupBinding.userPwInput.setText(it)
            }
            userPwChk.observe(mainActivity) {
                fragmentSignupBinding.userPwChkInput.setText(it)
            }
            userPhone.observe(mainActivity) {
                fragmentSignupBinding.userPhoneInput.setText(it)
            }
            userEmail.observe(mainActivity) {
                fragmentSignupBinding.userEmailInput.setText(it)
            }
        }

        fragmentSignupBinding.run {
            val id = userIdInput.text.toString()
            val nickName = userNickNameInput.text.toString()
            val pw = userPwInput.text.toString()
            val phoneNumber = userPhoneInput.text.toString()

            nextProfileBtn.setOnClickListener {

                val userModel = User(id, nickName, pw, phoneNumber)
                val bundle = Bundle()
                bundle.putParcelable("signInfo", userModel)

                mainActivity.replaceFragment(MainActivity.PROFILE_FRAGMENT, true, false, bundle)
            }
        }
        return fragmentSignupBinding.root
    }

    fun chkError() {
        fragmentSignupBinding.run {
            val id = userIdInput.text.toString()
            val nickName = userNickNameInput.text.toString()
            val pw = userPwInput.text.toString()
            val pwChk = userPwChkInput.text.toString()
            val phoneNumber = userPhoneInput.text.toString()

            if (pw != pwChk) {
                val builder = MaterialAlertDialogBuilder(mainActivity)
                builder.setTitle("비밀번호 오류")
                builder.setMessage("비밀번호가 일치하지 않습니다.")
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->

                }
                builder.show()
            }
        }

    }

    fun googleLoginResult() {
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                SignupFirebaseAuth.auth.signInWithCredential(credential)
                    .addOnCompleteListener(mainActivity) { task ->
                        if (task.isSuccessful) {
                            SignupFirebaseAuth.email = account.email

                        } else {

                        }
                    }
            } catch (e: ApiException) {

            }
        }
    }

//    fun googleLogin{
//        val gso = GoogleSignInOptions
//            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        val signInIntent = GoogleSignIn.getClient(mainActivity, gso).signInIntent
//        requestLauncher.launch(signInIntent)
//    }

    fun emailSignup() {
        val email = fragmentSignupBinding.userIdInput.text.toString()
        val password = fragmentSignupBinding.userPwInput.text.toString()
        SignupFirebaseAuth.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(mainActivity) { task ->
                if(task.isSuccessful){
                    SignupFirebaseAuth.auth.currentUser?.sendEmailVerification()
                        ?.addOnCompleteListener { sendTask ->
                            if(sendTask.isSuccessful){
//                                Toast.makeText(baseContext, "회원가입에서 성공, 전송된 메일을 확인해주세요", Toast.LENGTH_SHORT).show()
                            } else{
//                                Toast.makeText(baseContext, "메일 발송 실패", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else{
//                    Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }

}

