-- 코드를 입력하세요
SELECT B.CATEGORY AS CATEGORY, SUM(BS.SALES) AS TOTAL_SALES
FROM BOOK_SALES BS
INNER JOIN BOOK B ON BS.BOOK_ID = B.BOOK_ID
WHERE DATE_FORMAT(BS.SALES_DATE, '%Y%m') = '202201'
GROUP BY B.CATEGORY
ORDER BY B.CATEGORY ASC;