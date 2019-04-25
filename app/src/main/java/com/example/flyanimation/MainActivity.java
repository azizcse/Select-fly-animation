package com.example.flyanimation;

import android.animation.Animator;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mButton = findViewById(R.id.button);

        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        List<FileContainer> fileContainers = new ArrayList<>();

        for (ApplicationInfo packageInfo : packages) {

            Drawable drawable = packageInfo.loadIcon(getPackageManager());
            String appName = packageInfo.loadLabel(getPackageManager()).toString();

            fileContainers.add(new FileContainer(drawable, appName));
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductAdapter adapter = new ProductAdapter(this, fileContainers);
        mRecyclerView.setAdapter(adapter);

        adapter.setActionListener(new ProductAdapter.ProductItemActionListener() {
            @Override
            public void onItemTap(ImageView imageView) {
                if (imageView != null)
                    makeFlyAnimation(imageView);
            }
        });


    }
    int selectCount = 0;
    private void makeFlyAnimation(ImageView targetView) {


        new CircleAnimationUtil().attachActivity(this)
                .setTargetView(targetView)
                .setCircleDuration(100)
                .setMoveDuration(500)
                .setDestView(mButton)
                .setAnimationListener(
                        new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                selectCount++;
                                mButton.setText("Count  ="+selectCount);
                                final Animation animShake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                                mButton.startAnimation(animShake);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).startAnimation();


    }
}
