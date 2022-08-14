SELECT product_name
FROM ORDERS
         JOIN CUSTOMERS
              ON orders.customer_id = customers.id
WHERE customers.name = name;