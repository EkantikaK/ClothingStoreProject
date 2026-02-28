import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainShopWindow {

    // 1. Backend Connections
    private InventoryStore store;
    private ShoppingCart cart;
    private Checkout checkout;

    // 2. GUI Components
    private JFrame frame;
    private JList<ClothingItem> displayList;
    private DefaultListModel<ClothingItem> listModel;
    private JLabel cartLabel;
    private JComboBox<String> categoryDropdown;

    public MainShopWindow() {
        store = new InventoryStore();
        cart = new ShoppingCart();
        checkout = new Checkout();
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Java Online Clothing Store");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        setupTopPanel();
        setupCenterPanel();
        setupBottomPanel();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setupTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Filter by Category: "));

        categoryDropdown = new JComboBox<>(store.getCategories());

        categoryDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplayList((String) categoryDropdown.getSelectedItem());
            }
        });

        topPanel.add(categoryDropdown);
        frame.add(topPanel, BorderLayout.NORTH);
    }

    private void setupCenterPanel() {
        listModel = new DefaultListModel<>();
        displayList = new JList<>(listModel);

        updateDisplayList("All");

        JScrollPane scrollPane = new JScrollPane(displayList);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Fixed to ₹
        cartLabel = new JLabel("Cart: 0 items (₹0.00)  ");
        JButton btnAddToCart = new JButton("Add to Cart");
        JButton btnCheckout = new JButton("Checkout");

        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClothingItem selectedItem = displayList.getSelectedValue();
                if (selectedItem == null) {
                    JOptionPane.showMessageDialog(frame, "Please select an item first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (cart.addItem(selectedItem)) {
                    updateCartLabel();
                } else {
                    JOptionPane.showMessageDialog(frame, "Sorry, " + selectedItem.getName() + " is out of stock.", "Out of Stock", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cart.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String promoCode = JOptionPane.showInputDialog(frame, "Enter Promo Code (or leave blank):", "Checkout", JOptionPane.QUESTION_MESSAGE);

                boolean success = checkout.processCheckout(cart, promoCode);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Checkout Complete! Receipt saved to project folder.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateCartLabel();
                    updateDisplayList((String) categoryDropdown.getSelectedItem());
                }
            }
        });

        bottomPanel.add(cartLabel);
        bottomPanel.add(btnAddToCart);
        bottomPanel.add(btnCheckout);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateDisplayList(String category) {
        listModel.clear();
        List<ClothingItem> items = store.filterByCategory(category);
        for (ClothingItem item : items) {
            listModel.addElement(item);
        }
    }

    private void updateCartLabel() {
        // Fixed to ₹
        cartLabel.setText(String.format("Cart: %d items (₹%.2f)  ", cart.getItemCount(), cart.calculateTotalCost()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainShopWindow();
            }
        });
    }
}
