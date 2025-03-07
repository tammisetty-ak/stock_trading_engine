# Stock Trading Engine

This project implements a real time stock trading engine for matching buy and sell orders. The engine supports multiple stocks (tickers) and handles concurrent order submissions from multiple threads, simulating real-world trading environment.


## Features

* **Order Matching:** Matches buy and sell orders.

* **Concurrency:** Handles concurrent order submissions from multiple threads using synchronization mechanism to prevent race conditions.

* **Multiple Tickers:** Support fixed number(1024) of tickers (stocks) that can be traded.

* **OrderBook:** Maintains an order book for each ticker, organized using a combination of data structures for efficient order management and matching.


## DataStructures

The following data structures are used in the implementation, All constructed without using built-in libraries.

* **Doubly Linked List:** Each `OrderTreeNode` maintains two doubly linked lists: one for buy Orders, and one for sell orders.

* **Custom Tree:** A custom tree structure is used to organize orders for different ticker symbols. The `tickerNodes` array acts as the root of the tree, and each `orderTreeNode` represents a node in the tree, holding the ticker symbol and pointers to the buy and sell order lists.
