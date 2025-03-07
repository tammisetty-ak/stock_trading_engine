package stock_trading_engine;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of OrderBook interface
 * 
 */
class OrderBookImpl implements OrderBook {
    private static final AtomicInteger nextOrderId = new AtomicInteger(1); // counter for generating unique orderId
    private static final int MAX_TICKERS = 1024; // Maximum number of supported tickers
    private final OrderTreeNode[] tickerNodes = new OrderTreeNode[MAX_TICKERS]; // Array to store OrderTreeNodes for each ticker
    private final Object lock = new Object(); // Lock object for synchronization



    /**
     * Constructor for the OrderBook class
     * 
     * Intialises the tickerNodes array
     */
    public OrderBookImpl() {
        for(int i = 0; i < MAX_TICKERS; i++){
            tickerNodes[i] = new OrderTreeNode("Ticker" + (i));
        }
    }

    /**
     * Adds a new order to the order book.
     * 
     * @param orderType            Type of order ("Buy" or "Sell")
     * @param tickerSymbol         Symbol of the stock
     * @param quantity             Number of shares
     * @param price                price per share
     * 
     */
    @Override
    public void addOrder(String orderType, String tickerSymbol, int quantity, double price) {
        synchronized(lock){ // Aquire lock for Thread Safety.
            boolean buy = orderType.equalsIgnoreCase("Buy");

            Order newOrder = new Order(nextOrderId.getAndIncrement(), buy, quantity, price);

            int tickerIndex = getTickerIndex(tickerSymbol);

            OrderTreeNode node = tickerNodes[tickerIndex];

            if(buy) {
                insertBuyOrder(node, newOrder);
            }
            else {
                insertSellOrder(node, newOrder);
            }

            matchOrders(node);
        }
    }

    /**
     * Calculate the index for the given ticker symbol in the `tickerNodes` array.
     * 
     * @param tickerSymbol         Symbol of the stock.
     * 
     * @return                     Index of the ticker in the arrray calculated with help of the symbol.
     */
    private int getTickerIndex(String tickerSymbol) {
        return Integer.parseInt(tickerSymbol.substring(6));
    }
    
    /*
    * Inserts a buy order into the appropriate OrderTreeNode's buy Order List.
    * appends the new order to the end of the list.
    *
    * @param node       OrderTreeNode where the order should be inserted.
    * @param newOrder   The buy Order to be inserted.
    */
    private void insertBuyOrder(OrderTreeNode node, Order newOrder) {
        Order head = node.getBuyOrdersHead();

        if(head == null) {
            node.setBuyOrdersHead(newOrder);
        }
        else {
            Order current = head;
            while(current.getNextOrder() != null) {
                current = current.getNextOrder();
            }
            current.setNextOrder(newOrder);
            newOrder.setPrevOrder(current);
        }
    }

    /**
     * Inserts a sell order into the appropriate OrderTreeNode's sell Order List.
     * Maintains a list in the ascending order of the price (lower price first).
     * 
     * @param node      OrderTreeNode where the node should be inserted.
     * @param newOrder  The sell order to be inserted.
     */
    private void insertSellOrder(OrderTreeNode node, Order newOrder) {
        if(node.getSellOrdersHead() == null || newOrder.getPrice() <= node.getSellOrdersHead().getPrice()) {
            // Insert at the Beginning (lower price first)
            newOrder.setNextOrder(node.getSellOrdersHead());
            if(node.getSellOrdersHead() != null) {
                node.getSellOrdersHead().setPrevOrder(newOrder);
            }
            node.setSellOrdersHead(newOrder);
        }
        else {
            // Insert in the middle or at the end (maintaining ascending order)
            Order current = node.getSellOrdersHead();
            while(current.getNextOrder() != null && newOrder.getPrice() > current.getNextOrder().getPrice()) {
                current = current.getNextOrder();
            }
            newOrder.setNextOrder(current.getNextOrder());

            if(current.getNextOrder() != null) {
                current.getNextOrder().setPrevOrder(newOrder);
            }
            current.setNextOrder(newOrder);
            newOrder.setPrevOrder(current);
        }
    }

    /**
     * Matches buy and sell orders for the given OrderTreeNode based on price-time priority.
     * Removes matched orders from the list.
     * 
     * @param node          OrderTreeNode for which to match orders.
     */
    private void matchOrders(OrderTreeNode node) {
        Order buyOrder = node.getBuyOrdersHead();
        Order sellOrder = node.getSellOrdersHead();

        // if (buyOrder != null) System.out.println("inside match buy head" + buyOrder.getPrice());
        // if (sellOrder != null) System.out.println("inside match sell head" + sellOrder.getPrice());

        while(buyOrder != null && sellOrder != null) {
            if(buyOrder.getPrice() >= sellOrder.getPrice()) {
                int tradedQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

                // Print match details
                System.out.println("Match: " + tradedQuantity + " shares of " + node.getTickerSymbol() + " at " + sellOrder.getPrice() + " per share.");

                

                buyOrder.setQuantity(buyOrder.getQuantity() - tradedQuantity);
                sellOrder.setQuantity(sellOrder.getQuantity() - tradedQuantity);

                if(buyOrder.getQuantity() == 0) {
                    // Remove buyOrder from the list.

                    if(buyOrder.getPrevOrder() != null) {
                        buyOrder.getPrevOrder().setNextOrder(buyOrder.getNextOrder());
                    }
                    else {
                        node.setBuyOrdersHead(buyOrder.getNextOrder());
                    }

                    if(buyOrder.getNextOrder() != null){
                        buyOrder.getNextOrder().setPrevOrder(buyOrder.getPrevOrder());
                    }
                    buyOrder = buyOrder.getNextOrder();
                }

                if(sellOrder.getQuantity() == 0) {
                    // Remove sell Order from the list.
                    if(sellOrder.getPrevOrder() != null) {
                        sellOrder.getPrevOrder().setNextOrder(sellOrder.getNextOrder());
                    }
                    else {
                        node.setSellOrdersHead(sellOrder.getNextOrder());
                    }

                    if(sellOrder.getNextOrder() != null) {
                        sellOrder.getNextOrder().setPrevOrder(sellOrder.getPrevOrder());
                    }
                    sellOrder = sellOrder.getNextOrder();
                }
            }
            else {
                // Move to the next buyOrder if the current buy Price is less than sell price.
                buyOrder = buyOrder.getNextOrder();
            }
        }
    }


    /**
     * Retrieves OrderTreeNode for the given index in the tickerNodes Array.
     * 
     * @param index         Index of the OrderTreeNode in the tickerNodes Array.
     * @return OrderTreeNode at the given index.
     * 
     */
    @Override
    public OrderTreeNode getTickerNodes(int index) {
        return tickerNodes[index];
    }
}