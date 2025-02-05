package SkobutikenInlämningsuppgift;

public class Customer extends Entity{
    private  String name;
    private  String username;
    private int activeOrderId;

    public Customer(int id, String name, String username) {
        super(id);
        this.name = name;
        this.username = username;
    }

    public int getActiveOrderId() {
        return activeOrderId;
    }
    public void setActiveOrderId(int activeOrderId) {
        this.activeOrderId = activeOrderId;
    }

    public String getName() {
        return name;
    }
}

