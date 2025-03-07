package stock_trading_engine;

/**
 * Represents a single order in the stock trading engine
 */
class Order {
    private int orderId; // unique identifier for the order
    private boolean buyOrSell; // True for buy False for Sell
    private double price; // Price per share
    private int quantity; // Number of shares 
    private Order nextOrder; // Pointer to next order in the list
    private Order prevOrder; // Pointer to previous order in the list

    /**
     * Constructor for the Order class
     * 
     * @param orderId
     * @param buyOrSell
     * @param quantity
     * @param price
     */
    public Order(int orderId, boolean buyOrSell, int quantity, double price) {
        this.buyOrSell = buyOrSell;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.nextOrder = null;
        this.prevOrder = null;
    }

    // Getters and Setters for all attributes
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(boolean buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getPrevOrder() {
        return prevOrder;
    }

    public void setPrevOrder(Order prevOrder) {
        this.prevOrder = prevOrder;
    }

    public Order getNextOrder() {
        return nextOrder;
    }

    public void setNextOrder(Order nextOrder) {
        this.nextOrder = nextOrder;
    }

}