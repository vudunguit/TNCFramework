package vn.tnc.tncframework.ui.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import timber.log.Timber;
import vn.tnc.tncframework.R;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * Created by USER on 5/29/2015.
 */
public final class AspectRatioImageView extends ImageView {
    private float widthRatio;
    private float heightRatio;

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView);
        widthRatio = a.getFloat(R.styleable.AspectRatioImageView_widthRatio, 1);
        heightRatio = a.getFloat(R.styleable.AspectRatioImageView_heightRatio, 1);
        a.recycle();
    }

    public void setWidthRatio(float widthRatio) {
        this.widthRatio = widthRatio;
    }

    public void setHeightRatio(float heightRatio) {
        this.heightRatio = heightRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == EXACTLY) {
            if (heightMode != EXACTLY) {
                heightSize = (int) (widthSize * 1f / widthRatio * heightRatio);
            }
        } else if (heightMode == EXACTLY) {
            widthSize = (int) (heightSize * 1f / heightRatio * widthRatio);
        } else {
            throw new IllegalStateException("Either width or height must be EXACTLY.");
        }

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, EXACTLY);
        Timber.d("height %d for ratio %f", heightMeasureSpec, heightRatio);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
