package com.test.yamoowikiproject.ui.user

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.yamoowikiproject.databinding.FragmentSignupBinding
import com.test.yamoowikiproject.db.YamooWikiDatabase
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.ui.main.FragmentType
import com.test.yamoowikiproject.ui.user.model.SignupErrorState
import com.test.yamoowikiproject.viewmodel.MainViewModel
import com.test.yamoowikiproject.viewmodel.SignupViewModel


class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val signupViewModel: SignupViewModel by activityViewModels()
    private var uri: Uri? = null


    private var isValidId = false
    private var isValidNickName = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeData()
        initViews()
    }

    private fun observeData() {
        signupViewModel.isDuplicatedId.observe(viewLifecycleOwner) {
            val toastText = if (it) "중복된 아이디가 있습니다" else "가입가능"
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
            isValidId = it.not()
        }
        signupViewModel.isDuplicatedNickName.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "가입가능", Toast.LENGTH_SHORT).show()
                isValidNickName = true
            } else {
                Toast.makeText(context, "중복된 닉네임이 있습니다", Toast.LENGTH_SHORT).show()
                isValidNickName = false
            }
        }
    }


    private fun initViews() {

        binding.toolbar.setNavigationOnClickListener {
            Log.d("백버튼", "백버튼")
            parentFragmentManager.popBackStack()
        }

        binding.btnIdCheck.setOnClickListener {
            val userId: String = binding.etUserId.text.toString()
            signupViewModel.checkId(userId = userId, context = requireContext())
        }
        binding.etUserId.addTextChangedListener {
            isValidId = false
        }

        binding.btnUserNickName.setOnClickListener {
            val userNickName: String = binding.etUserNickName.text.toString()
            signupViewModel.checkNickName(userNickName = userNickName, context = requireContext())
        }

        binding.userProfileImage.setOnClickListener {
            selectGallery()
        }

        binding.btnConfirm.setOnClickListener {

            if (!isValidId) {
                Toast.makeText(context, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else if (!isValidNickName) {
                Toast.makeText(context, "닉네임을 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                confirm()?.let {
                    signupViewModel.signup(userEntity = it, context = requireContext())
                }
            }
        }
    }

    private fun confirm(): UserEntity? {
        with(binding) {
            val userId: String = binding.etUserId.text.toString()
            val userNickName: String = etUserNickName.text.toString()
            val userPassword: String = etUserPassword.text.toString()
            val userPasswordCheck: String = etUserPasswordCheck.text.toString()


            val errorState: SignupErrorState = when {
                userId.isEmpty() -> SignupErrorState.ID
                userNickName.isEmpty() -> SignupErrorState.NICKNAME
                userPassword.isEmpty() || userPasswordCheck.isEmpty()
                        || userPassword != userPasswordCheck ->
                    SignupErrorState.PASSWORD

                uri == null -> SignupErrorState.PROFILE

                else -> SignupErrorState.NONE
            }

            if (errorState != SignupErrorState.NONE) {
                showDialog(errorState)
                return null
            }

            mainViewModel.changeFragmentType(fragmentType = FragmentType.LOGIN)

            val user = UserEntity(
                userNickName = userNickName,
                userPassword = userPassword,
                userId = userId,
                userImage = uri.toString()
            )
            return user
        }
    }


    private fun showDialog(errorState: SignupErrorState) {
        MaterialAlertDialogBuilder(requireContext()).run {
            setTitle(errorState.title)
            setMessage(errorState.message)
            setPositiveButton("확인") { dialog, which ->
                when (errorState) {
                    SignupErrorState.ID -> binding.etUserId.requestFocus()
                    SignupErrorState.NICKNAME -> binding.etUserNickName.requestFocus()
                    SignupErrorState.PASSWORD -> binding.etUserPassword.requestFocus()
                    SignupErrorState.PROFILE -> binding.userProfileImage.requestFocus()
                    SignupErrorState.NONE -> Unit
                }
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
        val intent = Intent(Intent.ACTION_PICK)
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
                .into(binding.userProfileImage)
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


}
