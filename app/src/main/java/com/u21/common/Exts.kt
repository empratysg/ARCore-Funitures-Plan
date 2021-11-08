package com.u21.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.u21.arcore_funituries_plan.R

fun AppCompatActivity.replaceFragment(fragment: Fragment, isAddBackstack: Boolean = true) {
    with(supportFragmentManager.beginTransaction()) {
        replace(R.id.frame_layout, fragment)
        if (isAddBackstack) {
            addToBackStack("")
        }
        commit()
    }
}

fun AppCompatActivity.hasFragmentShowing(): Boolean {
    return supportFragmentManager.findFragmentById(R.id.frame_layout) != null
}