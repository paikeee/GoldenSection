class GoldenSectionMin extends GoldenSection {

    GoldenSectionMin(int from, int to) {
        super(from, to);
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        min = findMin(from, to);
    }
}
