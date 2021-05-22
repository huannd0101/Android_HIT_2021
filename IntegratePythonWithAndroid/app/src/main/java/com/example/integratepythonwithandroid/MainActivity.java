package com.example.integratepythonwithandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final Object MEDIA_TYPE_IMAGE = null;
    ImageButton btn_cancel;
    private Camera mCamera;
    private CameraPreview mPreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_cancel = findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });

// Create an instance of Camera
        mCamera = getCameraInstance();
//
//        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);





//        Camera.PictureCallback mPicture = new Camera.PictureCallback() {
//
//            @Override
//            public void onPictureTaken(byte[] data, Camera camera) {
//
//                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
//                if (pictureFile == null){
//                    Log.d("TAG", "Error creating media file, check storage permissions");
//                    return;
//                }
//
//                try {
//                    FileOutputStream fos = new FileOutputStream(pictureFile);
//                    fos.write(data);
//                    fos.close();
//                } catch (FileNotFoundException e) {
//                    Log.d("TAG", "File not found: " + e.getMessage());
//                } catch (IOException e) {
//                    Log.d("TAG", "Error accessing file: " + e.getMessage());
//                }
//            }
//        };


    //code khỏi động python

        Button captureButton = (Button) findViewById(R.id.button_capture);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(MainActivity.this));

        Python py = Python.getInstance();
        PyObject pyObject = py.getModule("test");
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pyObject.callAttr("Face_Detection");
            }
        });



//test
//        if(!Python.isStarted())
//            Python.start(new AndroidPlatform(this));
//
//        Python py = Python.getInstance();
//        PyObject pyobj = py.getModule("sumOfNumber");
//
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PyObject obj = pyobj.callAttr("main", edt1.getText().toString(), edt2.getText().toString());
//
//                tv.setText(obj.toString());
//            }
//        });


//        if(!Python.isStarted())
//            Python.start(new AndroidPlatform(MainActivity.this));
//
//        Python py = Python.getInstance();
//        PyObject pyObject = py.getModule("test");
//
//
//
//        Button button_capture = findViewById(R.id.button_capture);
//        button_capture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//            }
//        });
//        button_capture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pyObject.callAttr("Face_Detection");
//            }
//        });





    }



    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    private int countCam (){
        return Camera.getNumberOfCameras();
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
//
//
//    /** A basic Camera preview class */
    class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder mHolder;
        private Camera mCamera;

        public CameraPreview(Context context, Camera camera) {
            super(context);
            mCamera = camera;

            // Install a SurfaceHolder.Callback so we get notified when the
            // underlying surface is created and destroyed.
            mHolder = getHolder();
            mHolder.addCallback(this);
            // deprecated setting, but required on Android versions prior to 3.0
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        public void surfaceCreated(SurfaceHolder holder) {
            // The Surface has been created, now tell the camera where to draw the preview.
            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            } catch (IOException e) {
                Log.d("TAG", "Error setting camera preview: " + e.getMessage());
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // empty. Take care of releasing the Camera preview in your activity.
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            // If your preview can change or rotate, take care of those events here.
            // Make sure to stop the preview before resizing or reformatting it.

            if (mHolder.getSurface() == null){
                // preview surface does not exist
                return;
            }

            // stop preview before making changes
            try {
                mCamera.stopPreview();
            } catch (Exception e){
                // ignore: tried to stop a non-existent preview
            }

            // set preview size and make any resize, rotate or
            // reformatting changes here

            // start preview with new settings
            try {
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();

            } catch (Exception e){
                Log.d("TAG", "Error starting camera preview: " + e.getMessage());
            }
        }
    }
}