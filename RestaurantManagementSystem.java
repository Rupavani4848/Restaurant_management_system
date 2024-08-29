import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
class MenuItem {
    private final String name;
    private final double price;
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public double getBillAmount() {
        return price; 
    }
}
class Dish extends MenuItem {
    public Dish(String name,double price) {
        super(name, price);
    }
    public double getBillAmount() {
        return getPrice(); 
    }
}
class Drink extends MenuItem {
    public Drink(String name,double price) {
        super(name, price);
    }
    public double getBillAmount() {
        return getPrice() * 1.1; 
    }
}
class Menu {
    private final List<MenuItem> items;
    public Menu() {
        items = new ArrayList<>();
        items.add(new Dish("Biryani", 10.99));
        items.add(new Dish("Pizza", 5.99));
        items.add(new Drink("Pulav", 2.99));
        items.add(new Drink("Cola", 3.99));
    }
    public List<MenuItem> getItems() {
        return items;
    }
}
class Order {
    private final List<MenuItem>orderedItems;
    public Order() {
        orderedItems = new ArrayList<>();
    }
    public void addItem(MenuItem item) {
        orderedItems.add(item);
    }
    public List<MenuItem> getOrderedItems() {
        return orderedItems;
    }
    public double calculateTotal() {
        double total=0;
        for (MenuItem item:orderedItems) {
            total+=item.getBillAmount();
        }
        return total;
    }
}
public class RestaurantManagementSystem extends JFrame implements ActionListener {
    private final Menu menu;
    private final Order order;
    private final JTextArea orderDisplay;
    private final JComboBox<String> menuComboBox;
    private final JButton addItemButton,viewOrderButton,generateBillButton;
    public RestaurantManagementSystem() {
        menu=new Menu();
        order=new Order();
        setTitle("Restaurant Management System");
        setSize(500, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuComboBox=new JComboBox<>();
        orderDisplay=new JTextArea(15, 40);
        addItemButton=new JButton("Add Item");
        viewOrderButton=new JButton("View Order");
        generateBillButton=new JButton("Generate Bill");
        setupGUI();
        setVisible(true);
    }
    private void setupGUI() {
        for (MenuItem item : menu.getItems()) {
            menuComboBox.addItem(item.getName()+" - $"+ item.getPrice());
        }
        add(menuComboBox);
        add(addItemButton);
        add(viewOrderButton);
        add(generateBillButton);
        addItemButton.addActionListener(this);
        viewOrderButton.addActionListener(this);
        generateBillButton.addActionListener(this);
        add(orderDisplay);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addItemButton) {
            int selectedIndex = menuComboBox.getSelectedIndex();
            MenuItem selectedItem = menu.getItems().get(selectedIndex);
            order.addItem(selectedItem);
            orderDisplay.append("Added: " + selectedItem.getName()+ " - $" + selectedItem.getPrice() + "\n");
        } else if (e.getSource() == viewOrderButton) {
            orderDisplay.append("\n--- Current Order ---\n");
            for (MenuItem item : order.getOrderedItems()) {
                orderDisplay.append(item.getName() + " - $" + item.getBillAmount() + "\n");
            }
        } else if (e.getSource() == generateBillButton) {
            double total = order.calculateTotal();
            orderDisplay.append("\nTotal Bill: $" + total + "\n");
        }    }    public static void main(String[] args) {
        new RestaurantManagementSystem();
    }
}
