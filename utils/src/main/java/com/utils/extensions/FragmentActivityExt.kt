package com.utils.extensions

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

/**
 * Created by Thanh Quang on 17/12/2021.
 */

fun FragmentActivity.navController(id: Int): NavController {
    return (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController
}
