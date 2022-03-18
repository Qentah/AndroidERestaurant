package fr.isen.henry.erestaurant

import android.R
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import kotlin.math.max


class CountDrawable(context: Context) : Drawable() {
    private val mBadgePaint: Paint
    private val mTextPaint: Paint
    private val mTxtRect: Rect = Rect()
    private var mCount = ""
    private var mWillDraw = false
    override fun draw(canvas: Canvas) {
        if (!mWillDraw) {
            return
        }
        val bounds: Rect = bounds
        val width: Int = bounds.right - bounds.left
        val height: Int = bounds.bottom - bounds.top

        // Position the badge in the top-right quadrant of the icon.

        /*Using Math.max rather than Math.min */
        val radius = max(width, height) / 2 / 2
        val centerX = width - radius - 1 + 5
        val centerY = radius - 5
        if (mCount.length <= 2) {
            // Draw badge circle.
            canvas.drawCircle(
                centerX.toFloat(),
                centerY.toFloat(), ((radius + 5.5).toFloat()),
                mBadgePaint)
        } else {
            canvas.drawCircle(
                centerX.toFloat(),
                centerY.toFloat(),
                (radius + 6.5).toFloat(),
                mBadgePaint)
        }
        // Draw badge count text inside the circle.
        mTextPaint.getTextBounds(mCount, 0, mCount.length, mTxtRect)
        val textHeight: Int = mTxtRect.bottom - mTxtRect.top
        val textY = centerY + textHeight / 2f
        if (mCount.length > 2) canvas.drawText(
            "99+",
            centerX.toFloat(),
            textY,
            mTextPaint
        ) else canvas.drawText(mCount, centerX.toFloat(), textY, mTextPaint)
    }

    /*
    Sets the count (i.e notifications) to display.
     */
    fun setCount(count: String) {
        mCount = count

        // Only draw a badge if there are notifications.
        mWillDraw = !count.equals("0", ignoreCase = true)
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        // do nothing
    }

    override fun setColorFilter(cf: ColorFilter?) {
        // do nothing
    }

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }

    init {
        val mTextSize: Float = 27.00F
        mBadgePaint = Paint()
        mBadgePaint.color = ContextCompat.getColor(
            context.applicationContext,
            R.color.holo_orange_light
        )
        mBadgePaint.isAntiAlias = true
        mBadgePaint.style = Paint.Style.FILL
        mTextPaint = Paint()
        mTextPaint.color = Color.WHITE
        mTextPaint.typeface = Typeface.DEFAULT
        mTextPaint.textSize = mTextSize
        mTextPaint.isAntiAlias = true
        mTextPaint.textAlign = Paint.Align.CENTER
    }
}