public class ShoppingItem {
    private double itemPrice;
    private int itemQty;
    private String itemName;

    public ShoppingItem(double itemPrice, int itemQty, String itemName){
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
