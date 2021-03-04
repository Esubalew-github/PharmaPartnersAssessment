DROP TABLE IF EXISTS CURRENCIES;

CREATE TABLE CURRENCIES(ID INT, ticker JSON, name JSON, number_of_coins JSON, market_cap JSON );

INSERT INTO CURRENCIES VALUES
(1, '"ETH"' FORMAT JSON, '"Ethereum"' FORMAT JSON,'960 ' FORMAT JSON, '6920' FORMAT JSON ),
(2, '"XRP"' FORMAT JSON, '"Ripple"'    FORMAT JSON,'3850' FORMAT JSON, '6470' FORMAT JSON ),
(3, '"BCH"' FORMAT JSON, '"BitcoinCash"' FORMAT JSON,'160' FORMAT JSON, '4690' FORMAT JSON ),
(4, '"BTC"' FORMAT JSON, '"Bitcon"'    FORMAT JSON,'1670' FORMAT JSON, '4890' FORMAT JSON ),
(5, '"BCH"' FORMAT JSON, '"BitcoinCash"' FORMAT JSON,'9670 ' FORMAT JSON, '690' FORMAT JSON ),
(6, '"ETH"' FORMAT JSON, '"Ethereum"' FORMAT JSON,'960 ' FORMAT JSON, '6920' FORMAT JSON ),
(7, '"XRP"' FORMAT JSON, '"Ripple"'    FORMAT JSON,'3850' FORMAT JSON, '6470' FORMAT JSON ),
(8, '"XRP"' FORMAT JSON, '"Ripple"' FORMAT JSON,'160' FORMAT JSON, '2690' FORMAT JSON ),
(9, '"BTC"' FORMAT JSON, '"Bitcon"'    FORMAT JSON,'20000' FORMAT JSON, '1890' FORMAT JSON ),
(10, '"BTC"' FORMAT JSON, '"Bitcon"' FORMAT JSON,'3000 ' FORMAT JSON, '690' FORMAT JSON );

