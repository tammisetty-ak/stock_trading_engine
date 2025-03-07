package stock_trading_engine;

class Simulator {

    /**
     * Simulates concurrent access to the orderBook by launching multiple threads that adds orders.
     * After all threads complete, it prints the final state of the order book for a given ticker.
     * 
     * @param orderBook the OrderBook instance to start simulation.
     * @throws InterruptedException if thread is interrupted while waiting.
     * 
     */
    public void simulate(OrderBook orderBook) throws InterruptedException {
        // A runnable task that adds a mix of buy and sell orders.
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                orderBook.addOrder("Buy", "Ticker1", 100, 150);
                orderBook.addOrder("Sell", "Ticker1", 50, 140);
            }
        };

        // Create several threads to simulate concurrent access.
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to finish.
        t1.join();
        t2.join();
        t3.join();

        // // Print the final state of the order book for Ticker1.
        System.out.println("Final state for Ticker1:");
        OrderTreeNode node = orderBook.getTickerNodes(1);  // Assuming "Ticker1" maps to index 1
        System.out.println("Buy Orders:");
        Order order = node.getBuyOrdersHead();
        while (order != null) {
            System.out.println("Buy: " + order.getQuantity() + " at " + order.getPrice());
            order = order.getNextOrder();
        }

        System.out.println("Sell Orders:");
        order = node.getSellOrdersHead();
        while (order != null) {
            System.out.println("Sell: " + order.getQuantity() + " at " + order.getPrice());
            order = order.getNextOrder();
        }
    }
}