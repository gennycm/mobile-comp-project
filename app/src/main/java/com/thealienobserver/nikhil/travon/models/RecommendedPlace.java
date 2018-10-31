/*
 * Copyright 2015 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thealienobserver.nikhil.travon.models;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;


public class RecommendedPlace {
    public String name;
    public String description;
    public String longDescription;
    public String imageUrl;
    public String secondaryImageUrl;
    public String city;

    public Bitmap image;
    public Bitmap secondaryImage;
    public String distance;
    public String image_ref;


    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImage_ref(String image_ref) {
        this.image_ref = image_ref;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondaryImage(Bitmap secondaryImage) {
        this.secondaryImage = secondaryImage;
    }

    public void setSecondaryImageUrl(String secondaryImageUrl) {
        this.secondaryImageUrl = secondaryImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImage_ref() {
        return image_ref;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getSecondaryImageUrl() {
        return secondaryImageUrl;
    }
}