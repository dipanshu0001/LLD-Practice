package Model;

public class ButtonPanel {
    private int direction=1; // 1 for up, -1 for down

    public void pressUp() {this.direction = 1;}

    public void pressDown() {this.direction = -1;}

    public int getDirection() {return this.direction;}
}
