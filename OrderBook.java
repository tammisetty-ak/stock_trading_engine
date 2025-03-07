package stock_trading_engine;


/**
 * Interface for an OrderBook in stock trading engine
 * 
 */
interface OrderBook {
    
    /**
     * Adds a new order to the order book.
     * 
     * @param orderType            Type of order ("Buy" or "Sell")
     * @param tickerSymbol         Symbol of the stock
     * @param quantity             Number of shares
     * @param price                price per share
     */
    void addOrder(String orderType, String tickerSymbol, int quantity, double price);
    
    /**
     * Retrieves OrderTreeNode for the given index in the tickerNodes Array.
     * 
     * @param index Index of the OrderTreeNode in the tickerNodes Array.
     * @return OrderTreeNode at the given index.
     */
    OrderTreeNode getTickerNodes(int index);
}