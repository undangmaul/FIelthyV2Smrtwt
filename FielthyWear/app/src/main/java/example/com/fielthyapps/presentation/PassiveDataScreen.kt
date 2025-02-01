/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.com.fielthyapps.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.health.services.client.data.IntervalDataPoint
import androidx.health.services.client.data.SampleDataPoint
import example.com.fielthyapps.data.MeasureMessage
import example.com.fielthyapps.data.latestHeartRate
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import example.com.fielthyapps.data.latestDailyStep

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PassiveDataScreen(
    hrValue: List<SampleDataPoint<Double>>,
    stepValue: Double,
    recordingEnabled: Boolean,
    onEnableClick: (Boolean) -> Unit,
    permissionState: MultiplePermissionsState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeartRateToggle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            checked = recordingEnabled,
            onCheckedChange = onEnableClick,
            permissionState = permissionState
        )
        HeartRateCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            heartRate = hrValue.latestHeartRate() ?: 0.0
        )
        StepCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            steps = stepValue.toLong()
        )
    }
}

//@ExperimentalPermissionsApi
//@Preview(
//    device = Devices.WEAR_OS_SMALL_ROUND,
//    showBackground = false,
//    showSystemUi = true
//)
//@Composable
//fun PassiveDataScreenPreview() {
//    val permissionState = object : PermissionState {
//        override val permission = PERMISSION
//        override val status: PermissionStatus = PermissionStatus.Granted
//        override fun launchPermissionRequest() {}
//    }
//    PassiveDataTheme {
//        PassiveDataScreen(
//            hrValue = 65.6,
//            recordingEnabled = true,
//            onEnableClick = {},
//            permissionState = permissionState
//        )
//    }
//}
