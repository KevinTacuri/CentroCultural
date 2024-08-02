package com.example.centrocultural;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.fragment.app.FragmentActivity;

import com.example.centrocultural.fragments.SalaFragment;

public class MapView extends View {
    private Paint paint;
    private Paint paintRoom;
    private Paint paintText;
    private Rect sala1Rect, sala2Rect, sala3Rect, sala4Rect, sala5Rect, sala6Rect, sala7Rect, sala8Rect;
    private static final float ORIGINAL_WIDTH = 1000f;
    private static final float ORIGINAL_HEIGHT = 1000f;
    private float scaleX;
    private float scaleY;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        paintRoom = new Paint();
        paintRoom.setColor(Color.BLACK);
        paintRoom.setStyle(Paint.Style.STROKE);
        paintRoom.setStrokeWidth(3);

        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(40);
        paintText.setTextAlign(Paint.Align.CENTER);

        ViewTreeObserver observer = getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Remove the listener to prevent multiple calls
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // Update the rectangles and invalidate the view to redraw
                updateRectangles();
                invalidate();
            }
        });

        setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float x = event.getX();
                float y = event.getY();

                // Verificar en qué sala se hizo clic
                if (sala1Rect.contains((int) x, (int) y)) {
                    handleSalaClick(1);
                } else if (sala2Rect.contains((int) x, (int) y)) {
                    handleSalaClick(2);
                } else if (sala3Rect.contains((int) x, (int) y)) {
                    handleSalaClick(3);
                } else if (sala4Rect.contains((int) x, (int) y)) {
                    handleSalaClick(4);
                } else if (sala5Rect.contains((int) x, (int) y)) {
                    handleSalaClick(5);
                } else if (sala6Rect.contains((int) x, (int) y)) {
                    handleSalaClick(6);
                } else if (sala7Rect.contains((int) x, (int) y)) {
                    handleSalaClick(7);
                } else if (sala8Rect.contains((int) x, (int) y)) {
                    handleSalaClick(8);
                }
            }
            return true;
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        scaleX = w / ORIGINAL_WIDTH;
        scaleY = h / ORIGINAL_HEIGHT;
        updateRectangles();
    }

    private void updateRectangles() {
        sala1Rect = new Rect(
                (int) (100 * scaleX),
                (int) (700 * scaleY),
                (int) (300 * scaleX),
                (int) (900 * scaleY)
        );

        sala2Rect = new Rect(
                (int) (300 * scaleX),
                (int) (700 * scaleY),
                (int) (500 * scaleX),
                (int) (900 * scaleY)
        );

        sala3Rect = new Rect(
                (int) (500 * scaleX),
                (int) (700 * scaleY),
                (int) (700 * scaleX),
                (int) (900 * scaleY)
        );

        sala4Rect = new Rect(
                (int) (700 * scaleX),
                (int) (500 * scaleY),
                (int) (900 * scaleX),
                (int) (900 * scaleY)
        );

        sala5Rect = new Rect(
                (int) (700 * scaleX),
                (int) (100 * scaleY),
                (int) (900 * scaleX),
                (int) (400 * scaleY)
        );

        sala6Rect = new Rect(
                (int) (400 * scaleX),
                (int) (100 * scaleY),
                (int) (700 * scaleX),
                (int) (300 * scaleY)
        );

        sala7Rect = new Rect(
                (int) (100 * scaleX),
                (int) (100 * scaleY),
                (int) (400 * scaleX),
                (int) (300 * scaleY)
        );

        sala8Rect = new Rect(
                (int) (100 * scaleX),
                (int) (300 * scaleY),
                (int) (300 * scaleX),
                (int) (700 * scaleY)
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGallery(canvas);
    }

    private void drawGallery(Canvas canvas) {
        // Dibuja cada sala usando las coordenadas escaladas
        canvas.drawRect(sala1Rect, paintRoom);
        canvas.drawText("Sala 1",
                (sala1Rect.left + sala1Rect.right) / 2f,
                (sala1Rect.top + sala1Rect.bottom) / 2f,
                paintText
        );

        canvas.drawRect(sala2Rect, paintRoom);
        canvas.drawText("Sala 2",
                (sala2Rect.left + sala2Rect.right) / 2f,
                (sala2Rect.top + sala2Rect.bottom) / 2f,
                paintText
        );

        canvas.drawRect(sala3Rect, paintRoom);
        canvas.drawText("Sala 3",
                (sala3Rect.left + sala3Rect.right) / 2f,
                (sala3Rect.top + sala3Rect.bottom) / 2f,
                paintText
        );

        canvas.drawRect(sala4Rect, paintRoom);
        canvas.drawText("Sala 4",
                (sala4Rect.left + sala4Rect.right) / 2f,
                (sala4Rect.top + sala4Rect.bottom) / 2f,
                paintText
        );

        canvas.drawRect(sala5Rect, paintRoom);
        canvas.drawText("Sala 5",
                (sala5Rect.left + sala5Rect.right) / 2f,
                (sala5Rect.top + sala5Rect.bottom) / 2f,
                paintText
        );

        canvas.drawRect(sala6Rect, paintRoom);
        canvas.drawText("Sala 6",
                (sala6Rect.left + sala6Rect.right) / 2f,
                (sala6Rect.top + sala6Rect.bottom) / 2f,
                paintText
        );

        canvas.drawRect(sala7Rect, paintRoom);
        canvas.drawText("Sala 7",
                (sala7Rect.left + sala7Rect.right) / 2f,
                (sala7Rect.top + sala7Rect.bottom) / 2f,
                paintText
        );

        canvas.drawRect(sala8Rect, paintRoom);
        canvas.drawText("Sala 8",
                (sala8Rect.left + sala8Rect.right) / 2f,
                (sala8Rect.top + sala8Rect.bottom) / 2f,
                paintText
        );
    }

    private void handleSalaClick(int salaId) {
        ((FragmentActivity) getContext()).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, SalaFragment.newInstance(salaId))
                .addToBackStack(null)
                .commit();
    }

}
