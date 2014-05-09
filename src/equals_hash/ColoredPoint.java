package equals_hash;

public class ColoredPoint extends Point { // 不再违背对称性

    private final Color color;

    public ColoredPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof ColoredPoint) {
            ColoredPoint that = (ColoredPoint) other;
            result = (that.canEqual(this) && this.color.equals(that.color) && super.equals(that));
        }
        return result;
    }

    @Override
    public int hashCode() {
        return (41 * super.hashCode() + color.hashCode());
    }

    @Override
    public boolean canEqual(Object other) {
        return (other instanceof ColoredPoint);
    }
}
