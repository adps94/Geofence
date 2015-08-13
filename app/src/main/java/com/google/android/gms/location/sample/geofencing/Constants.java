/**
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.location.sample.geofencing;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Constants used in this sample.
 */
public final class Constants {

    private Constants() {
    }

    public static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 100; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    public static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<String, LatLng>();
    static {
        // San Francisco International Airport.
        // Googleplex.


        BAY_AREA_LANDMARKS.put("Asset Homes", new LatLng(8.562416,76.856627));
        BAY_AREA_LANDMARKS.put("Menamkulam", new LatLng(8.563676,76.860099));
        BAY_AREA_LANDMARKS.put("Kazhakoottam", new LatLng(8.568439,76.873151));
        BAY_AREA_LANDMARKS.put("Tech-Park Gate", new LatLng(8.557745,76.876370));
        BAY_AREA_LANDMARKS.put("TechPark", new LatLng(8.556277,76.881949));

        BAY_AREA_LANDMARKS.put("Vetturoad", new LatLng(8.582337,76.863720));
        BAY_AREA_LANDMARKS.put("Kinfra", new LatLng(8.586973,76.877030));
        BAY_AREA_LANDMARKS.put("EY", new LatLng(8.580703,76.877254));

       // new GetApiData().execute();
    }
}
