package com.example.ex_03_camera_share;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Session;


public class MainActivity extends AppCompatActivity {

    Session mSession;

    GLSurfaceView mySurView;

    MainRenderer mRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySurView = (GLSurfaceView) findViewById(R.id.glsurfaceview);



        MainRenderer.RenderCallBack mr = new MainRenderer.RenderCallBack() {
            @Override
            public void preRender() {

                // session 객체와 연결해서 화면 그리기 하기
                mSession.setCameraTextureName(mRenderer.getTextureId());
            }
        };

        mRenderer = new MainRenderer(mr);

        // pause 시 관련 데이터가 사라지는 것을 막는다.
        mySurView.setPreserveEGLContextOnPause(true);
        mySurView.setEGLContextClientVersion(2); // 버전을 2.0 사용

        // 화면을 그리는 Renderer를 지정한다.
        mySurView.setRenderer(mRenderer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraPerm();;

        try {
            if(mSession == null) {


                //Log.d("session requestInstall ? ", ArCoreApk.getInstance().requestInstall(this, true) + "");

                switch (ArCoreApk.getInstance().requestInstall(this, true)) {

                    case INSTALLED: // ARCore 정상설치 되었음
                        // ARCore 가 정상설치 되어서 session 을 생성 가능한 형태임
                        mSession = new Session(this);
                        Log.d("session 인감", "sssession 생성이여!!!");
                        break;
                    case INSTALL_REQUESTED: // AECore 설치 필요
                        Log.d("session 인감", "sssession 생성이여!!!");
                        break;
                }
                //mSession = new Session(this);
                //Log.d("session 인감", "sssession 생성이여!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //카메라 퍼미션 요청
    void cameraPerm(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.CAMERA},
                    0
            );
        }
    }

}