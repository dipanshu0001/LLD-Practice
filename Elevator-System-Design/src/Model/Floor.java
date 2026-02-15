package Model;

public class Floor {
    private int id;
    private ButtonPanel buttonPanel;

    public Floor(int id, ButtonPanel buttonPanel) {
        this.id = id;
        this.buttonPanel = buttonPanel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(ButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", buttonPanel=" + buttonPanel +
                '}';
    }
}
