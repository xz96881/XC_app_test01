public class JoystickView extends View {
    // 控件属性
    private int outerColor;
    private int innerColor;

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.JoystickView);
        outerColor = a.getColor(R.styleable.JoystickView_outerCircleColor, Color.GRAY);
        innerColor = a.getColor(R.styleable.JoystickView_innerCircleColor, Color.BLUE);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制外圆
        Paint outerPaint = new Paint();
        outerPaint.setColor(outerColor);
        canvas.drawCircle(getWidth()/2f, getHeight()/2f, getWidth()/2f, outerPaint);

        // 绘制内圆
        Paint innerPaint = new Paint();
        innerPaint.setColor(innerColor);
        canvas.drawCircle(getWidth()/2f, getHeight()/2f, getWidth()/4f, innerPaint);
    }
}