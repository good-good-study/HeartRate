package com.neusoft.heart.rate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class ShowOOMExceptionActivity extends AppCompatActivity {

    private GridView mPhotoWall;
    private PhotoWallAdapter adapter;
    private String[] datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_oomexception);
        initView();
        intiData();
        intiListener();
    }

    private void initView() {
        mPhotoWall = (GridView) findViewById(R.id.photo_wall);
    }

    private void intiData() {
        datas = new String[]{
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0B5-3BCtasWxEZ0xYdG1UeTBpM0k/style_icons_product_human_dimension2.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7OFZFR1ZOUmFWTEE/style_icons_system_intro.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7emgtQk5rdEE3bW8/style_icons_system_intro_principles_simple.png",

                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7MG80dmpHT0RidGs/style_icons_system_intro_principles_actionable.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7blViQzF0azNqZU0/style_icons_system_intro_principles_consistent.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7em9MVTRJRlQ2Yjg/style_icons_system_grid_geometry1.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7VXlvR05VSWhPZTg/style_icons_system_grid_geometry2.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7eHZJS0tIbGRPYVk/style_icons_system_anatomy.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7alFGeDJYR1RlRDQ/style_icons_system_anatomy_corner_exterior.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7Z0JuRk9LaVhjazA/style_icons_system_anatomy_corner_interior.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0BxFyKV4eeNjDYUpUeDlPeS0zdzg/components.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0BxFyKV4eeNjDX0RnOUVRcjh2VzQ/accessibility.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0BxFyKV4eeNjDdERRNDc0bkh4UDQ/UI.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0B7WCemMG6e0VdXFKUnkyVjVVNjQ/style_icons.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7bHMzZkNZekcxRVE/style_logos_product_intro_material_physical.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7YXp3Q1RIZ1BfNEk/style_logos_product_intro_material_lighting.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7dzR3OXp5U1UtOEE/style_logos_product_intro_material_material.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7YTRHMzFLb3FFOEk/style_logos_product_intro_material_color.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7RGQxNmZlUWdBTWc/style_logos_product_grid_logo_grid.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7QklSbXpmcjRSTlU/style_logos_product_grid_shapes_circle.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7eDY4dkFETnIwTHM/style_logos_product_anatomy_components.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7S3h0VFVRVFZwRnM/style_logos_product_anatomy_components_perspective.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7Uy1Vc2c5TGEtOGs/style_logos_product_anatomy_color_spot.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0B6Okdz75tqQsS0FEQzA5eWxoT0U/style_imagery_principles1.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0B2AMilo_nIVFOGxHa0xJbmkyVU0/style_imagery_principles2.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0B6Okdz75tqQsa2ZxRE53dUlvcWM/style_imagery_principles3.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7d00tRHpROG9vcHc/style_logos_product_anatomy_color_flooding.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7UzdCZ0MtOEpybGM/style_logos_product_anatomy_edges_tinted.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7RW5GbWN2Z2pNcGc/style_logos_product_anatomy_edges_shaded.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7RW5GbWN2Z2pNcGc/style_logos_product_anatomy_edges_shaded.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7RVNWUUVZQmpwQVE/style_logos_product_anatomy_finish.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7ZlJqRXpSeEJoSjQ/style_logos_product_lighting_lighting_top.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7MEtzZUxkM0tzeGs/style_logos_product_lighting_shadow.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7T21HZ3M4N3IwTDg/style_logos_product_lighting_edge_tinted.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7d0JqZl9BOUVYcHc/style_logos_product_lighting_edge_shaded.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0By1W60wM4zekd0NONzFVdEI3WVk/style_logos_product_lighting_finish.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7c2QtTjZJcEhQNWc/style_logos_product_lighting_values_cool.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7VnJwcWFWNy1Ed0E/style_logos_product_lighting_values_fresh.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7V0s3TGdGRVlXM0k/style_logos_product_lighting_values_warm.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7ZVVyVnhNcG9pVkk/style_logos_product_lighting_values_hot.png",



                "http://www.jcodecraeer.com/uploads/170606/1-1F606222923130.jpg",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7ZGVnX1FaLTlYWVk/style_logos_product_lighting_values_neutral.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7aTFQSWlWdGQ4M0E/style_logos_product_patterns_color_do.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7ZE5PWEtObkRaTU0/style_logos_product_patterns_color_dont.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7THNBbDd1MUFobHM/style_logos_product_patterns_layer_do.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7UDhwbVphM01JcTg/style_logos_product_patterns_layer_dont.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7QU54RENNaGdOR0E/style_logos_product_patterns_elevate_do.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7TFlxRkZkdWd4bEk/style_logos_product_patterns_elevate_dont.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7REh5NnRCWUFkYVU/style_logos_product_patterns_score_do.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7eDRXRkxQV1I4bWM/style_logos_product_patterns_score_dont.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7NUdvcXNfcW85QnM/style_logos_product_patterns_fold_do.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7THM3eXZHa19wSFk/style_logos_product_patterns_fold_dont.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7ZGdQTk9uMlRIRnM/style_logos_product_patterns_overlap_do.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7QWFrNllZRXVuU3c/style_logos_product_patterns_overlap_dont.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7S2FaUk9sSjVGMWs/style_logos_product_patterns_accordian_do.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bx4BSt6jniD7S1ZDOGRwQTlmdXc/style_logos_product_patterns_accordian_dont.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0B5-3BCtasWxEaFJwdzYzV19NODg/style_icons_product_human_keyline1.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0B5-3BCtasWxENDhMRno5UmNmODg/style_icons_product_human_keyline2.png",
                "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0B5-3BCtasWxEakZDejI4a1VUVlE/style_icons_product_human_dimension1.png",
                "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png",
                "http://www.jcodecraeer.com/uploads/170516/1-1F516103A6352.png",
                "http://www.jcodecraeer.com/uploads/allimg/170215/135524I11-0.jpg",
                "http://www.jcodecraeer.com/uploads/20170330/1490865016182928.jpeg",
                "http://www.jcodecraeer.com/uploads/allimg/170215/1H0121I1-0.jpg",
                "http://www.jcodecraeer.com/uploads/170606/1-1F606222I4E5.jpg",
                "http://www.jcodecraeer.com/uploads/170606/1-1F606222K3631.jpg",
                "http://www.jcodecraeer.com/uploads/170606/1-1F606222A93J.jpg",
                "http://www.jcodecraeer.com/uploads/170607/1-1F60FS30H29.jpg"

        };
    }

    private void intiListener() {
        adapter = new PhotoWallAdapter(this, 0, datas, mPhotoWall);
        mPhotoWall.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        if (adapter != null) {
            adapter.cancleAllTasks();
        }
        super.onDestroy();
    }
}
