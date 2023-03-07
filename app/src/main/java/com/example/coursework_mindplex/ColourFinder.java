package com.example.coursework_mindplex;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Map;

//this class code has been derived from https://stackoverflow.com/questions/28670901/what-is-the-best-way-to-implement-color-detection-in-android
public class ColourFinder {

    private CallbackInterface callback;

    public ColourFinder(CallbackInterface callback) {
        this.callback = callback;
        //this ensures the async function is complete before the next function is run
    }

    public void findColour(Bitmap bitmap) {
        new DisplayMainColour().execute(bitmap);
        //call the async function
    }

    private int mainColourBitmap(Bitmap bitmap) {
        int [] pixels = new int[
                bitmap.getWidth()*bitmap.getHeight()
                ];
        //creates an array of pixels which is found by the width and height of the screen (bitmap)
        bitmap.getPixels(pixels,0,bitmap.getWidth(),0,0, bitmap.getWidth(), bitmap.getHeight());

        Map<Integer, PixelClass> pixelList = mainPixelList(pixels);
        return mainPixel(pixelList);
    }

    private Map<Integer, PixelClass> mainPixelList(int [] pixels) {
        Map<Integer, PixelClass> pixelList = new HashMap<>();

        for (int aPixel : pixels) {
            //for every pixel in the array of pixels assign the pixel class or increment counter
            if (pixelList.containsKey(aPixel)) {
                pixelList.get(aPixel).pixelCounter++;
            } else {
                pixelList.put(aPixel, new PixelClass(aPixel, 1));
            }
        }

        return pixelList;
    }

    private int mainPixel(Map<Integer, PixelClass> pixelList) {
        int mainColour = 0;
        int largest = 0;
        for (Map.Entry<Integer, PixelClass> entry : pixelList.entrySet()) {
            PixelClass pixelClass = entry.getValue();
            if (pixelClass.pixelCounter > largest) {
                largest = pixelClass.pixelCounter;
                mainColour = pixelClass.pixel;
                //Each pixel in the hashmap is compared to find the largest, which is stored in global class:
                //pixelClass, the pixel that is bigger than the counter is considered the main colour
            }
        }

        return mainColour;
    }

    //async task that can complete concurrently.
    //The maincolor is found for each frame passed and the hex value is returned
    //which is displayed to the user.
    private class DisplayMainColour extends AsyncTask<Bitmap, Integer, Integer> {

        @Override
        protected Integer doInBackground(Bitmap... params) {
            int mainColour = mainColourBitmap(params[0]);
            return mainColour;
        }

        @Override
        protected void onPostExecute(Integer mainColour) {
            String hexColor = colorToHex(mainColour);
            if (callback != null)
                callback.onCompleted(hexColor);
        }

        private String colorToHex(int color) {
            return String.format("#%06X", (0xFFFFFF & color));
        }
    }

    public interface CallbackInterface {
         void onCompleted(String mainColour);
    }
}
