package Model;

public class ElevatorContainer {
    public Door doors;
    public ButtonPanel buttonPanel;

    public ElevatorContainer() {
        this.doors = new Door();
        this.buttonPanel = new ButtonPanel();
    }

    public Door getDoors() {
        return doors;
    }

    public void setDoors(Door doors) {
        this.doors = doors;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(ButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }
}
