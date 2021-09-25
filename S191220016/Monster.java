package S191220016;

import S191220016.Line.Position;

import java.util.Random;
import static java.lang.Math.abs;

public class Monster implements Linable {

    private final int r;
    private final int g;
    private final int b;
    private final int rank;


    private Position position;

    public static Monster[] monsters;

    public static void init_monsters(int num){
        monsters=new Monster[num];
        for(int i=0;i<num;i++){
            monsters[i]=new Monster(i,(i>>4)<<4, ((i>>2) & 0xf)<<4 , (i&0xf)<<4);
        }
        Random rnd = new Random();
        for (int i = 0; i <256; i++) {
            int j=abs(rnd.nextInt()%256);
            Monster temp=monsters[i];
            monsters[i]=monsters[j];
            monsters[j]=temp;
        }
    }

    private Monster(int _rank , int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        rank=_rank;
    }


    @Override
    public String toString() {
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;255;255;255m" + String.format("%-4s",Integer.toString(this.rank)) + "\033[0m";
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public static void swapPosition(int a,int b) {
        Position p = monsters[b].position;
        monsters[a].position.setLinable(monsters[b]);
        p.setLinable(monsters[a]);
        Monster temp=monsters[a];
        monsters[a]=monsters[b];
        monsters[b]=temp;
    }

    @Override
    public int getValue() {
        return this.rank;
    }

}