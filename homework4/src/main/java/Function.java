public class Function {
    public static void main(String[] args) {
        int x = 5;
        numbers(x);

        int width = 3;
        int height = 2;
        drawRectangle(width, height);

        int side = 3;
        drawRectangle(side);

        int a = 2;
        int b = 3;
        System.out.println(getMax(a, b));

        float c = 2;
        float d = 3;
        System.out.println(getMax(c, d));

        int e = 5;
        numbersRecursion(e);

        drawRectangleRecursion(width, height);
    }

    private static void numbers (int x){
        for (int i = 1; i <= x; i++) {
            System.out.println(i);
        }
        System.out.println();
    }
    private static void drawRectangle(int width, int height){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("+ ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static void drawRectangle(int side){
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                System.out.print("+ ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static int getMax(int a, int b){
        return Math.max(a, b);
    }
    private static float getMax(float a, float b){
        return Math.max(a, b);
    }
    private static void numbersRecursion(int x){
        if (x < 1) {
            return;
        } else {
            numbersRecursion(x - 1);
            System.out.println(x);
        }
    }
    private static void drawRectangleStringRecursion(int width) {
        if (width < 1) {
            return;
        } else {
            drawRectangleStringRecursion(width - 1);
            System.out.print("+ ");
        }
    }
    private static void drawRectangleRecursion(int width, int height){
        drawRectangleStringRecursion(width);
        System.out.println();
        if (height <= 1) {
            return;
        } else {
            drawRectangleRecursion(width, height - 1);
        }
    }
}
