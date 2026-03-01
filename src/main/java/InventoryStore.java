import java.util.ArrayList;
import java.util.List;

public class InventoryStore {

    private List<ClothingItem> inventory;

    public InventoryStore() {
        inventory = new ArrayList<>();
        loadItems();
    }

    private void loadItems() {
        // Shirts
        inventory.add(new ClothingItem("S01", "Basic White T-Shirt", "Shirts", 499.00, 50));
        inventory.add(new ClothingItem("S02", "Graphic Hoodie", "Shirts", 899.00, 20));
        inventory.add(new ClothingItem("S03", "Flannel Button-Up", "Shirts", 699.00, 15));

        // Pants
        inventory.add(new ClothingItem("P01", "Classic Blue Jeans", "Pants", 999.00, 30));
        inventory.add(new ClothingItem("P02", "Black Chinos", "Pants", 799.00, 25));
        inventory.add(new ClothingItem("P03", "Sweatpants", "Pants", 599.00, 40));

        // Shoes
        inventory.add(new ClothingItem("F01", "Running Sneakers", "Shoes", 999.00, 15));
        inventory.add(new ClothingItem("F02", "Leather Loafers", "Shoes", 899.00, 10));

        // Accessories
        inventory.add(new ClothingItem("A01", "Winter Beanie", "Accessories", 499.00, 35));
        inventory.add(new ClothingItem("A02", "Leather Belt", "Accessories", 549.00, 20));
    }

    public List<ClothingItem> getAllItems() {
        return inventory;
    }

    public List<ClothingItem> filterByCategory(String category) {
        if (category.equals("All")) {
            return inventory;
        }

        List<ClothingItem> filteredList = new ArrayList<>();
        for (ClothingItem item : inventory) {
            if (item.getCategory().equals(category)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public String[] getCategories() {
        return new String[]{"All", "Shirts", "Pants", "Shoes", "Accessories"};
    }
}
