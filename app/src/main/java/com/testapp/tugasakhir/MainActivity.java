package com.testapp.tugasakhir;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;



import com.testapp.tugasakhir.ml.CNNConventionalModel;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class MainActivity extends AppCompatActivity {
    private TextView kalori, protein, karbohidrat, water, vitaminc;
    private Database SQLite = new Database(this);
    Button camera, gallery;
    ImageView imageView;
    TextView result;
    TextView resultDescription;
    int imageSize = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.button);
        gallery = findViewById(R.id.button2);


        kalori = findViewById(R.id.kalori);
        protein = findViewById(R.id.protein);
        karbohidrat = findViewById(R.id.karbohidrat);
        water = findViewById(R.id.water);
        vitaminc = findViewById(R.id.vitaminc);

        result = findViewById(R.id.result);

        imageView = findViewById(R.id.imageView);
        camera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 3);
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent, 1);
            }
        });


    }


    public void classifyImage(Bitmap image){
        try {
            CNNConventionalModel model = CNNConventionalModel.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 255, 255, 3},
                    DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            int pixel = 0;
            //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
            for(int i = 0; i < imageSize; i ++){
                for(int j = 0; j < imageSize; j++){
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);
            // Runs model inference and gets result.
//            ModelUnquant.Outputs outputs = model.process(inputFeature0);
            CNNConventionalModel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            // find the index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }


            String[] classes = {"Pisang","Mangga","Nanas","Jeruk","Kelapa","Manggis","Rambutan","Pepaya","Markisa","Salak","Jambu Biji","Kiwi","Alpukat","Semangka","Leci","Delima","Jeruk Nipis","Jagung"};


            result.setText(classes[maxPos]);


            try {
                String cari = classes[maxPos]; // Menggunakan nilai kelas hasil klasifikasi
                SQLiteDatabase db = SQLite.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM tb_buah WHERE nama = '" + cari + "'", null);
                c.moveToFirst();
                kalori.setText(String.valueOf(c.getString(2))); // Set nilai kalori
                protein.setText(String.valueOf(c.getString(3))); // Set nilai protein
                karbohidrat.setText(String.valueOf(c.getString(4))); // Set nilai karbohidrat
                water.setText(String.valueOf(c.getString(5))); // Set nilai water
                vitaminc.setText(String.valueOf(c.getString(6))); // Set nilai vitaminc
                db.close();
            } catch (Exception e) {
                Log.e("Err : ", e.getMessage());
            }
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }

    }


//    private static List<Buah> getListBuah() {
//        List<Buah> listBuah = new ArrayList<Buah>();
//        listBuah.add(new Buah("Pisang", "Description Pisang"));
//
//        return listBuah;
//    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 3){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }else{
                Uri dat = data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}