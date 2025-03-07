package stock_trading_engine;

/**
 * Represents a Node in the binary search tree of order book
 * Each node holds the orders of a specific ticker symbol
 * 
 */
class OrderTreeNode {
    private String tickerSymbol; // ticker symbol associated with the node
    Order buyOrdersHead; // Head of doubly linked list of buy orders
    Order sellOrdersHead; // Head of doubly linked list of sell orders

    /**
     * Constructor
     * 
     * @param tickerSymbol 
     */
    public OrderTreeNode(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
        this.buyOrdersHead = null;
        this.sellOrdersHead = null;
    }

    // getters and setters for all attributes

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public Order getBuyOrdersHead() {
        return buyOrdersHead;
    }

    public void setBuyOrdersHead(Order buyOrdersHead) {
        this.buyOrdersHead = buyOrdersHead;
    }

    public Order getSellOrdersHead() {
        return sellOrdersHead;
    }

    public void setSellOrdersHead(Order sellOrdersHead) {
        this.sellOrdersHead = sellOrdersHead;
    }
    
}