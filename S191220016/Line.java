package S191220016;




public class Line {

    public Line(final int length) {
        this.positions = new Position[length];
    }

    protected final Position[] positions;

    public void put(final Linable linable, final int i) {
        if (this.positions[i] == null) {
            this.positions[i] = new Position(null);
        }
        this.positions[i].setLinable(linable);
    }

    public Linable get(final int i) {
        if ((i < 0) || (i > positions.length)) {
            return null;
        } else {
            return positions[i].linable;
        }
    }

class Position {

        Linable linable;

        Position(final Linable linable) {
            this.linable = linable;
        }

        public void setLinable(final Linable linable) {
            this.linable = linable;
            linable.setPosition(this);
        }

    }

    @Override
    public String toString() {
        String lineString = "\t";
        for (final Position p : positions) {
            lineString += p.linable.toString();
        }
        return lineString;
    }

    public Linable[] toArray() {
        final Linable[] linables = new Linable[this.positions.length];

        for (int i = 0; i < linables.length; i++) {
            linables[i] = positions[i].linable;
        }

        return linables;

    }

}
