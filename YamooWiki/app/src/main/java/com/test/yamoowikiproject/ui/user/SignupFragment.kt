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

    private lateinit var database: YamooWikiDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirm.setOnClickListener {
            confirm()?.let {
                signupViewModel.signup(userEntity = it, context = requireContext())
            }
        }
        binding.userProfileImage.setOnClickListener {
            selectGallery()
        }

    }

    private fun confirm(): UserEntity? {
        with(binding) {
            val userId: String = etUserId.text.toString()
            val userNickName: String = userNickNameInput.text.toString()
            val userPassword: String = userPasswordInput.text.toString()
            val userPasswordCheck: String = userPasswordInputCheck.text.toString()


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
                    SignupErrorState.NICKNAME -> binding.userNickNameInput.requestFocus()
                    SignupErrorState.PASSWORD -> binding.userPasswordInput.requestFocus()
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

    private var uri: Uri? = null
}
