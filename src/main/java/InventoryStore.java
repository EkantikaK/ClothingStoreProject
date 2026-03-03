import java.util.ArrayList;
import java.util.List;

public class InventoryStore {

    private List<ClothingItem> inventory;

    public InventoryStore() {
        inventory = new ArrayList<>();
        loadItems();
    }

    public ClothingItem getItemById(String id) {
        for (ClothingItem item : inventory) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    private void loadItems() {

        // ================= TOPS =================
        inventory.add(new ClothingItem("T01", "Adidas Pro Longsleeve", "Tops", 1299.00, 20));
        inventory.add(new ClothingItem("T02", "Adidas L.F.C. Jersey", "Tops", 1499.00, 15));
        inventory.add(new ClothingItem("T03", "Puma Striped Polo", "Tops", 1899.00, 10));
        inventory.add(new ClothingItem("T04", "Puma Asymmetric Crop", "Tops", 999.00, 25));
        inventory.add(new ClothingItem("T05", "Puma Track Jacket", "Tops", 2499.00, 12));

        // ================= BOTTOMS =================
        inventory.add(new ClothingItem("B01", "Puma Track Pants", "Bottoms", 1999.00, 18));
        inventory.add(new ClothingItem("B02", "Puma Graphic Shorts", "Bottoms", 1499.00, 22));
        inventory.add(new ClothingItem("B03", "Puma Motorsport Pants", "Bottoms", 2199.00, 14));
        inventory.add(new ClothingItem("B04", "Puma Longline Shorts", "Bottoms", 1299.00, 30));
        inventory.add(new ClothingItem("B05", "Puma Blue Sweats", "Bottoms", 1799.00, 16));

        // ================= SHOES =================
        inventory.add(new ClothingItem("S01", "Nike Air Max 97", "Shoes", 14999.00, 8));
        inventory.add(new ClothingItem("S02", "Nike Air Max 90 Denim", "Shoes", 12499.00, 10));
        inventory.add(new ClothingItem("S03", "Nike Air Max Dn", "Shoes", 13999.00, 6));
    }

    public List<ClothingItem> getAllItems() {
        return inventory;
    }

    public List<ClothingItem> filterByCategory(String category) {

        if (category.equalsIgnoreCase("All")) {
            return inventory;
        }

        List<ClothingItem> filteredList = new ArrayList<>();

        for (ClothingItem item : inventory) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                filteredList.add(item);
            }
        }

        return filteredList;
    }

    public String[] getCategories() {
        return new String[]{"All", "Tops", "Bottoms", "Shoes"};
    }
}