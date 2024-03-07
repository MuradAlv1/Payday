# Diagram
#

![alt text](https://github.com/MuradAlv1/Payday/blob/master/paydaydesign.drawio.png)

# Details

## Stock Service 

API for displaying stock prices.

It fetches prices every N seconds from an external API, publishes prices to the "stocks" topic, and updates prices in the database.


## Order Service

API for creating orders.

Listens for events from the 'stocks' topic to check whether the target price of orders for a given stockId has been reached.

If price reached it send event with orderId to 'order-matched' topic

## Order Executor
When an order execution is triggered, it sends an event to the 'reserve-amount-request' topic.

The Account Service listens to this topic and responds to the 'reserve-amount' topic with the result of the reservation.

If the reservation succeeds, the order will be processed, and an event will be sent to the 'order-filled' topic.

If the account does not have enough money, the order will fail.

 ## Account Service
API for creating accounts

Listens 'order-filled' topic and finalizes the transaction by reducing the reserved amount.
