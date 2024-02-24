-- 코드를 입력하세요
SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_INS I
INNER JOIN ANIMAL_OUTS O ON I.ANIMAL_ID = O.ANIMAL_ID
ORDER BY (DATEDIFF(I.DATETIME, O.DATETIME))
LIMIT 2