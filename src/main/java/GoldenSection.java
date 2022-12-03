class GoldenSection extends Thread {

    private final double PHI = (1 + Math.sqrt(5)) / 2;
    private final double EPS = 1e-05;
    int from;
    int to;
    double max;
    double min;

    public GoldenSection(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        min = findMin(from, to);
        max = findMax(from, to);
    }

    double func(double x) { // function
        return Math.pow(x + 1, 3) + 5 * Math.pow(x, 2);
    }

    double findMin(double a, double b){
        double x1, x2;
        do {
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (func(x1) >= func(x2))
                a = x1;
            else
                b = x2;
        } while (!(Math.abs(b - a) < EPS));
        return (a + b) / 2;
    }

    double findMax(double a, double b){
        double x1, x2;
        do {
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (func(x1) <= func(x2))
                a = x1;
            else
                b = x2;
        } while (!(Math.abs(b - a) < EPS));
        return (a + b) / 2;
    }

}