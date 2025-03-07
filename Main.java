package stock_trading_engine;

/**
 * Main class to start the stock trading engine simulation
 */
public class Main {
    public static void main(String[] args) throws InterruptedException{
        // Create an OrderBook instance.
        OrderBook orderBook = new OrderBookImpl();

        // Create a Simulator instance.
        Simulator simulator = new Simulator();

        // Run the simulation
        simulator.simulate(orderBook);
    }
}