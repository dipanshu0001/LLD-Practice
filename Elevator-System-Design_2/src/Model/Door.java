package Model;

public class Door {
    Boolean open;

    public Door() {
        this.open = false;
    }
    public void openDoor() {
        if (!this.open) {
            System.out.println("🚪 Opening doors...");
            this.open = true;
            System.out.println("✅ Doors opened");
        }
    }

    public void closeDoor() {
        if (this.open) {
            System.out.println("🚪 Closing doors...");
            this.open = false;
            System.out.println("✅ Doors closed");
        }
    }
    public boolean isOpen() {return this.open;}
}
