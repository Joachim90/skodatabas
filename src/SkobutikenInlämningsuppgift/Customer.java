package SkobutikenInlämningsuppgift;

public class Customer extends Entity{
    private  String name;


    public Customer(int id, String name) {
        super(id);
        this.name = name;

    }


    public String getName() {
        return name;
    }
}

