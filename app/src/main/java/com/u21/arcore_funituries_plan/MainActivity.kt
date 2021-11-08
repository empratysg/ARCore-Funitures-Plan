package com.u21.arcore_funituries_plan

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.core.ArCoreApk
import com.u21.arcore_funituries_plan.ui.home.HomeFragment
import com.u21.common.CameraPermissionHelper
import com.u21.common.TasksHandlerHelper
import com.u21.common.hasFragmentShowing
import com.u21.common.replaceFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!hasFragmentShowing()) {
            replaceFragment(HomeFragment(), false)
        }
    }


}