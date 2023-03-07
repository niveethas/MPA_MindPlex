package com.example.coursework_mindplex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;

//code derived from: https://medium.com/swlh/introduction-to-androids-camerax-with-java-ca384c522c5
public class CameraActivity extends AppCompatActivity {
    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        previewView = findViewById(R.id.previewView);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindImageAnalysis(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));

    }

    private void bindImageAnalysis(@NonNull ProcessCameraProvider cameraProvider) {
        ImageAnalysis imageAnalysis =
                new ImageAnalysis.Builder().setTargetResolution(new Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER).build();
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new ImageAnalysis.Analyzer() {
            @Override
            public void analyze(@NonNull ImageProxy image) {
                //Code derived from: https://stackoverflow.com/questions/56772967/converting-imageproxy-to-bitmap
                @SuppressLint("UnsafeOptInUsageError") Image imageFrame = image.getImage();
                assert imageFrame != null;
                //ensures the image frame is never null, if so there is no need to analyse color
                Bitmap bitmapFrame = YUVtoBitmap(imageFrame);
                //Image analysis returns images in YUV format, Bitmap is required for finding the main colour
                new ColourFinder(new ColourFinder.CallbackInterface() {
                    @Override
                    public void onCompleted(String color) {
                        TextView screenText = findViewById(R.id.colourText);
                        screenText.setText("Colour found: "+color);
                    }
                }).findColour(bitmapFrame);
                image.close();
            }
        });

        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, imageAnalysis, preview);
    }

    private Bitmap YUVtoBitmap(Image image) {
        //Code derived from: https://stackoverflow.com/questions/56772967/converting-imageproxy-to-bitmap
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer y = planes[0].getBuffer();
        ByteBuffer u = planes[1].getBuffer();
        ByteBuffer v = planes[2].getBuffer();

        int ySize = y.remaining();
        int uSize = u.remaining();
        int vSize = v.remaining();

        byte[] nv21 = new byte[ySize + uSize + vSize];
        //U and V are swapped
        y.get(nv21, 0, ySize);
        v.get(nv21, ySize, vSize);
        u.get(nv21, ySize + vSize, uSize);

        YuvImage yuvImage = new YuvImage(nv21, ImageFormat.NV21, image.getWidth(), image.getHeight(), null);
        ByteArrayOutputStream outByteImage = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, yuvImage.getWidth(), yuvImage.getHeight()), 75, outByteImage);
        //the YUV image is outputted as a JPEG, the bytes of the output can be converted to a Bitmap.
        byte[] imageBytes = outByteImage.toByteArray();
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    public void backBtnClick(View view){
        Intent backCG = new Intent(CameraActivity.this, CameraGameActivity.class);
        backCG.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backCG);

    }


}