SELECT T1.ID, IFNULL(T2.CNT, 0) AS CHILD_COUNT
FROM ECOLI_DATA T1
LEFT JOIN(
        SELECT PARENT_ID, COUNT(*) AS CNT
        FROM ECOLI_DATA
        GROUP BY PARENT_ID
    ) T2 ON T1.ID = T2.PARENT_ID
ORDER BY T1.ID
