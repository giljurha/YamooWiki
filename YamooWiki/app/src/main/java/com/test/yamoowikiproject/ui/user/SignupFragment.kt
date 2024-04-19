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
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.yamoowikiproject.databinding.DialogErrorBinding
import com.test.yamoowikiproject.databinding.FragmentSignupBinding
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
            val signupErrorState =
                if (it) SignupErrorState.DUPLICATEDID else SignupErrorState.NOTERROR
            showDialog(signupErrorState)
            isValidId = it.not()
        }
        signupViewModel.isDuplicatedNickName.observe(viewLifecycleOwner) {
            val signupErrorState: SignupErrorState =
                if (it) SignupErrorState.DUPLICATEDNICKNAME else SignupErrorState.NOTERROR
            showDialog(signupErrorState)
            isValidNickName = it.not()
        }
    }

    private fun initViews() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }

            etUserId.addTextChangedListener {
                isValidId = false
            }

            etUserNickName.addTextChangedListener {
                isValidNickName = false
            }

            btnIdCheck.setOnClickListener {
                val userId: String = binding.etUserId.text.toString()
                if (userId.isEmpty()) {
                    showDialog(signupErrorState = SignupErrorState.ID)
                } else {
                    signupViewModel.checkId(
                        userId = userId,
                        context = requireContext()
                    )
                }
            }

            btnUserNickName.setOnClickListener {
                val userNickName: String = binding.etUserNickName.text.toString()
                if (userNickName.isEmpty()) {
                    showDialog(signupErrorState = SignupErrorState.NICKNAME)
                } else {
                    signupViewModel.checkNickName(
                        userNickName = userNickName,
                        context = requireContext()
                    )
                }
            }

            userProfileImage.setOnClickListener {
                selectGallery()
            }

            btnConfirm.setOnClickListener {
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
            val isUserPasswordCheck: Boolean = userPassword.isEmpty().not()
                    && userPasswordCheck.isEmpty().not()
                    && userPassword == userPasswordCheck

            if (!isValidId) {
                showDialog(SignupErrorState.ID)
                return null
            } else if (!isValidNickName) {
                showDialog(SignupErrorState.NICKNAME)
                return null
            } else if (!isUserPasswordCheck) {
                showDialog(SignupErrorState.PASSWORD)
                return null
            } else if (uri == null) {
                showDialog(SignupErrorState.PROFILE)
                return null
            }

            mainViewModel.changeFragmentType(fragmentType = FragmentType.LOGIN)

            val user = UserEntity(
                userId = userId,
                userNickName = userNickName,
                userPassword = userPassword,
                userImageUri = uri.toString()
            )
            return user
        }
    }


    private fun showDialog(signupErrorState: SignupErrorState) {

        val focusView: View? = when (signupErrorState) {
            SignupErrorState.ID -> binding.etUserId
            SignupErrorState.NICKNAME -> binding.etUserNickName
            SignupErrorState.PASSWORD -> binding.etUserPassword
            SignupErrorState.PROFILE -> binding.userProfileImage
            SignupErrorState.DUPLICATEDID -> binding.etUserId
            SignupErrorState.DUPLICATEDNICKNAME -> binding.etUserNickName
            SignupErrorState.NOTERROR -> null
        }

        MaterialAlertDialogBuilder(requireContext()).run {
            val dialogErrorBinding: DialogErrorBinding = DialogErrorBinding.inflate(layoutInflater)
            dialogErrorBinding.textView.text = signupErrorState.message
            setView(dialogErrorBinding.root)
            setPositiveButton("확인") { dialog, which ->
                focusView?.requestFocus()
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
    private val imageResult = registerForActivityResult( //oncreate에서만 정의가능 -> 모듈화 불가능
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            uri = it.data?.data
            Glide.with(this)
                .load(it.data?.data)
                .override(1000, 1000)
                .centerCrop()
                .into(binding.userProfileImage)
        }
    }

    private fun requestPermission() {
        permissionDialog.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
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
