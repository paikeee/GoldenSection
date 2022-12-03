class GoldenSectionMax extends GoldenSection {

    GoldenSectionMax(int from, int to) {
        super(from, to);
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        max = findMax(from, to);
    }
}
