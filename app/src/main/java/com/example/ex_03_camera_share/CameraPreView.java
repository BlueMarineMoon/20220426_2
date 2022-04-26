package com.example.ex_03_camera_share;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class CameraPreView {

    // GPU 를 이용하여 고속 계산 하여 화면 처리 하기 위한 코드
    String vertextXhaderCode =
                    "attribute vec2 aTexCoord;" +
                    "varying vec2 vTexCoord;" +
                    "attribute vec4 vPosition;" +   // vec4 -> 3차원 좌표
                    "void main () {" +
                    "gl_Position = vPosition ;" +
                    "vTexCoord = aTexCoord ;" +
                    // gl_Position  " OpenGL 에 있는 변수 ::> 계산식 uMVPMatrix * vPosition
                    "}";
    String fragmentXhaderCode =
            "#extension GL_OES_EGL_image_external : require \n" +
            "precision mediump float;" +
                    "uniform samplerExternalOES sTexture; " +
                    "varying vec2 vTexCoord" +
                    "void main() {" +
                    "   gl_FragColor = texture20(sTexture, vTexCoord); " +
                    "}";


    // 직사각형 점의 좌표
    static float [] QUARD_COORDS = {
            // x,   y,   z
            -1.0f, -1.0f, 0.0f,
            -1.0f, 1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,

    };

    static float [] QUARD_TEXCOORDS = {
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 1.0f,
            1.0f, 0.0f,

    };





    FloatBuffer vertexBuffer;
    ShortBuffer drawBuffer;
    int mProgram;
}
