package com.test.yamoowikiproject.ui.user

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat


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

        // content resolver 와 glide 사용해서 프로필 사진 설정하기

        fragmentSignupBinding.confirmButton.setOnClickListener {
            confirm()
        }
        fragmentSignupBinding.userProfileImage.setOnClickListener{
            selectGallery()
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

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var curPhotoPath: String

//    private fun setPermission() {
//        val permission = object : PermissionListener {
//            override fun onPermissionGranted() {
//                Toast.makeText(requireContext(), "권한이 허용 되었습니다.", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                Toast.makeText(requireContext(), "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show()
//            }
//        }
//        TedPermission.with(requireContext())
//            .setPermissionListener(permission)
//            .setRationaleMessage("카메라 앱을 사용하시려면 권한을 허용해주세요.")
//            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한] 항목에서 허용해주세요.")
//            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
//            .check()
//    }


    private fun selectGallery() {

        val readPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (readPermission == PackageManager.PERMISSION_DENIED) { //권한이 없을 시 권한 요청
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 100
            )
        } else {
            //권한이 있을 경우 갤러리 실행
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            imageResult.launch(intent)
        }
    }




    var imageResult = registerForActivityResult( //oncreate에서만 정의가능 -> 모듈화 불가능
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK){
            Glide.with(this)
                .load(result.data?.data)
                .override(200,200)
                .into(fragmentSignupBinding.userProfileImage)
        }


    }





}
