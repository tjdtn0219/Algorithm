-- 코드를 입력하세요
# 서브 쿼리 풀이
# SELECT CAR_ID
# FROM CAR_RENTAL_COMPANY_CAR
# WHERE CAR_TYPE='세단' AND
# CAR_ID IN (
#     SELECT CAR_ID
#     FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
#     WHERE DATE_FORMAT(START_DATE, "%m") = 10
# )
# ORDER BY CAR_ID DESC;

#JOIN 풀이
# SELECT DISTINCT(C.CAR_ID)
# FROM CAR_RENTAL_COMPANY_CAR C
# INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY H
# ON C.CAR_ID = H.CAR_ID
# WHERE DATE_FORMAT(H.START_DATE, '%m') = 10
# AND C.CAR_TYPE LIKE '세단'
# ORDER BY C.CAR_ID DESC;

# SELECT CAR_ID
# FROM CAR_RENTAL_COMPANY_CAR
# WHERE CAR_TYPE = '세단' AND CAR_ID IN
#     (SELECT CAR_ID FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY WHERE MONTH(START_DATE) = 10)
# ORDER BY CAR_ID DESC;







SELECT CAR_ID
FROM CAR_RENTAL_COMPANY_CAR
WHERE CAR_ID IN (
                SELECT DISTINCT(CAR_ID)
                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                WHERE DATE_FORMAT(START_DATE, '%m')=10)
AND CAR_TYPE = '세단'
ORDER BY CAR_ID DESC;










