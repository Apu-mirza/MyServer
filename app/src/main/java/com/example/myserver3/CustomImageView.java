package com.example.myserver3;

//import android.content.Context;
//import android.graphics.Matrix;
//import android.graphics.PointF;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.ScaleGestureDetector;
//
//import androidx.appcompat.widget.AppCompatImageView;
//
//public class CustomImageView extends AppCompatImageView {
//
//    private static final float MIN_SCALE_FACTOR = 1.0f;
//    private static final float MAX_SCALE_FACTOR = 3.0f;
//
//    private Matrix matrix = new Matrix();
//    private PointF lastTouch = new PointF();
//    private float scaleFactor = 1f;
//    private ScaleGestureDetector scaleGestureDetector;
//
//    public CustomImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        setup();
//    }
//
//    private void setup() {
//        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
//        setScaleType(ScaleType.MATRIX);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        scaleGestureDetector.onTouchEvent(event);
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastTouch.set(event.getX(), event.getY());
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                // Disable moving the image
//                break;
//        }
//
//        return true;
//    }
//
//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            scaleFactor *= detector.getScaleFactor();
//            scaleFactor = Math.max(MIN_SCALE_FACTOR, Math.min(scaleFactor, MAX_SCALE_FACTOR));
//
//            // Set the scale on the matrix
//            matrix.setScale(scaleFactor, scaleFactor);
//            setImageMatrix(matrix);
//            return true;
//        }
//    }
//}




import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class CustomImageView extends androidx.appcompat.widget.AppCompatImageView {

    private static final float MIN_SCALE_FACTOR = 1.0f;
    private static final float MAX_SCALE_FACTOR = 3.0f;

    private Matrix matrix = new Matrix();
    private PointF lastTouch = new PointF();
    private float scaleFactor = 1f;
    private ScaleGestureDetector scaleGestureDetector;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    private void setup() {
        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);

        PointF currentTouch = new PointF(event.getX(), event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouch.set(currentTouch);
                break;

            case MotionEvent.ACTION_MOVE:
                float dx = currentTouch.x - lastTouch.x;
                float dy = currentTouch.y - lastTouch.y;
                matrix.postTranslate(dx, dy);
                lastTouch.set(currentTouch.x, currentTouch.y);
                break;
        }

        setImageMatrix(matrix);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(MIN_SCALE_FACTOR, Math.min(scaleFactor, MAX_SCALE_FACTOR));

            matrix.setScale(scaleFactor, scaleFactor);
            setImageMatrix(matrix);
            return true;
        }
    }
}
