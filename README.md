# Stock Trading Engine

This project implements a real time stock trading engine for matching buy and sell orders. The engine supports multiple stocks (tickers) and handles concurrent order submissions from multiple threads, simulating real-world trading environment.


## Features

* **Order Matching:** Matches buy and sell orders.

* **Concurrency:** Handles concurrent order submissions from multiple threads using synchronization mechanism to prevent race conditions.

* **Multiple Tickers:** Support fixed number(1024) of tickers (stocks) that can be traded.

* **OrderBook:** Maintains an order book for each ticker, organized using a combination of data structures for efficient order management and matching.


## DataStructures and OOP Principles

The following data structures are used in the implementation, All constructed without using built-in libraries.

* **Array:** An array(`tickerNodes`) is used to store `OrderTreeNode` objects, where each `OrderTreeNode` represents a ticker symbol and its associated orders.

* **Doubly Linked List:** Each `OrderTreeNode` maintains two doubly linked lists: one for buy Orders, and one for sell orders.

* **Custom Tree:** A custom tree structure is used to organize orders for different ticker symbols. The `tickerNodes` array acts as the root of the tree, and each `orderTreeNode` represents a node in the tree, holding the ticker symbol and pointers to the buy and sell order lists.


* **Encapsulation:** The `Order`, `OrderTreeNode`, and `OrderBookImpl` classes encapsulate their respective data and behaviour, providing controlled across through methods and promoting data integrity.

* **Data Abstraction:** The implementation details of data structures and algorithms are hidden from the user.

## Usage

The main class (`OrderBook`) provides the following methods:

*  `addOrder(String orderType, string tickerSymbol, int quantity, double price)`: Adds a new order to the order book.

* `getTickerNodes(int index)`: Retrieves the `OrderTreeNode` for the given index.

* `insertBuyOrder(OrderTreeNode node, Order newOrder)`: inserts elements into the buy order doubly linked list. It follows First in First out principle

* `insertSellOrder(OrderTreeNode node, Order newOrder)`: inserts elements into the sell order doubly linked list. It puts elements in the non decreasing order. orders with lowest sell prices are placed at the front of the list.

* `matchOrders(OrderTreeNode node)`: matches the buy & sell orders given that the buy price of a paticular ticker is greater than or equal to lowest sell price available then.

* `getTickerIndex(String tickerSymbol)`: caluclates the index of the `tickerNodes` array using the tickerSymbol. 

