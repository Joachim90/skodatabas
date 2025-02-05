package SkobutikenInlämningsuppgift;

public class ShoeCategory extends Entity {

    private String categoryName;

    public ShoeCategory(int id, String categoryName) {
        super(id);
        this.categoryName = categoryName;
    }

    public int getShoeCategoryId() {
        return getId();
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return categoryName;
    }

}
