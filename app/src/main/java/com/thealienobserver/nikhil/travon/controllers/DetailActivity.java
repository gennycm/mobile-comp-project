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

package com.thealienobserver.nikhil.travon.controllers;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thealienobserver.nikhil.travon.R;
import com.thealienobserver.nikhil.travon.adapters.RecommendedPlacesAdapter;


/**
 * The tourist attraction detail activity screen which contains the details of
 * a single attraction.
 */
public class DetailActivity extends AppCompatActivity {


  private ImageView imageView;
  private TextView tv_title;
  private TextView tv_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView=(ImageView)findViewById(R.id.iv_recommended_place);
        tv_description=(TextView)findViewById(R.id.tv_description);
        tv_title=(TextView)findViewById(R.id.tv_title);

        Glide.with(this).load(getIntent().getStringExtra(RecommendedPlacesAdapter.PLACE_IMAGE)).into(imageView);
        tv_title.setText(getIntent().getStringExtra(RecommendedPlacesAdapter.PLACE_TITLE));
        tv_description.setText(getIntent().getStringExtra(RecommendedPlacesAdapter.PLACE_DESCRIPTION)!=null?getIntent().getStringExtra(RecommendedPlacesAdapter.PLACE_DESCRIPTION):"");





    }
}
