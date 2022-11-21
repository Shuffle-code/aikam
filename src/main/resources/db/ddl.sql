DROP TABLE BUYER;
DROP TABLE PURCHASE;
DROP TABLE SHIPMENT;

CREATE TABLE BUYER (
    ID BIGSERIAL NOT NULL PRIMARY KEY,
    FIRSTNAME VARCHAR(80) NOT NULL,
    LASTNAME VARCHAR(80) NOT NULL
);

CREATE TABLE PURCHASE(
    ID BIGSERIAL NOT NULL PRIMARY KEY,
    BUYER_ID BIGSERIAL,
    SHIPMENT_ID BIGSERIAL,
    DATA DATE,
    CONSTRAINT BUYER_FOREIGN_KEY FOREIGN KEY (BUYER_ID) REFERENCES BUYER (ID),
    CONSTRAINT SHIPMENT_FOREIGN_KEY FOREIGN KEY (SHIPMENT_ID) REFERENCES SHIPMENT (ID)
);

CREATE TABLE SHIPMENT(
    ID BIGSERIAL NOT NULL PRIMARY KEY,
    TITLE VARCHAR(80) NOT NULL,
    COST DECIMAL(10, 2) NOT NULL
);

CREATE VIEW bad_info AS select
max(buyer_id) as buyer, count(shipment_id) as count
from purchase group by buyer_id order by count;



WITH this_shipment AS (select * from purchase
JOIN shipment on purchase.shipment_id = shipment.id
where shipment.title = 'Минеральная вода'),
amount_buyer_info AS (select count(shipment_id) as count,
buyer_id from this_shipment group by buyer_id)
SELECT buyer_id from amount_buyer_info where count >= 1;


WITH purchese AS (SELECT * FROM purchase),
shipment_without_title AS
(SELECT id, cost FROM shipment),
full_join AS
(SELECT * FROM purchese FULL JOIN shipment_without_title
ON purchese.shipment_id = shipment_without_title.id),
stat AS (SELECT buyer_id, shipment_id, cost FROM full_join),
summ AS (SELECT buyer_id, SUM(cost) AS SUM
FROM stat GROUP BY buyer_id ORDER BY buyer_id)
SELECT * FROM summ WHERE sum >= 112 AND sum <= 4000;