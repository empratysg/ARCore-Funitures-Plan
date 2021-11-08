package com.u21.arcore_funituries_plan.ui.home

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.ar.core.ArCoreApk
import com.u21.arcore_funituries_plan.R
import com.u21.common.CameraPermissionHelper
import com.u21.common.TasksHandlerHelper
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var tasksHandlerHelper: TasksHandlerHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasksHandlerHelper = TasksHandlerHelper()

        lifecycle.addObserver(tasksHandlerHelper)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!CameraPermissionHelper.hasCameraPermission(requireActivity())) {
            if (CameraPermissionHelper.shouldShowRequestPermissionRationale(requireActivity())) {
                CameraPermissionHelper.launchPermissionSettings(requireActivity())
            } else {
                CameraPermissionHelper.requestCameraPermission(requireActivity())
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        maybeEnableArButton()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CameraPermissionHelper.CAMERA_PERMISSION_CODE) {
            permissions.forEachIndexed { index, permission ->
                if (permission == CameraPermissionHelper.CAMERA_PERMISSION && grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    //todo something
                    tv_text.text = permission
                }
            }
        }
    }

    private fun maybeEnableArButton() {
        val availability = ArCoreApk.getInstance().checkAvailability(requireContext())
        if (availability.isTransient) {
            // Continue to query availability at 5Hz while compatibility is checked in the background.
            tasksHandlerHelper.runTaskWithDelay(this::maybeEnableArButton, 200)
        }
        if (availability.isSupported) {
        } else { // The device is unsupported or unknown.
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}