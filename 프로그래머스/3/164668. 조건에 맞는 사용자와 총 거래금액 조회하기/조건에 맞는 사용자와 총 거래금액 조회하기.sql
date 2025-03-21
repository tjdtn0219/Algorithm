SELECT T1.USER_ID, T1.NICKNAME, T2.SUM_PRICE
FROM USED_GOODS_USER T1
JOIN (
    SELECT WRITER_ID, SUM(PRICE) SUM_PRICE
    FROM USED_GOODS_BOARD
    WHERE STATUS = 'DONE'
    GROUP BY WRITER_ID
    HAVING SUM(PRICE) >= 700000) T2
ON T1.USER_ID = T2.WRITER_ID
ORDER BY T2.SUM_PRICE
