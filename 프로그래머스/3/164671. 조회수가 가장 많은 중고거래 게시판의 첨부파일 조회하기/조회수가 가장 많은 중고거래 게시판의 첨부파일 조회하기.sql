-- 코드를 입력하세요
SELECT CONCAT('/home/grep/src/', T1.BOARD_ID, '/', T1.FILE_ID, T1.FILE_NAME, T1.FILE_EXT) AS FILE_PATH
FROM USED_GOODS_FILE T1
JOIN (SELECT BOARD_ID FROM USED_GOODS_BOARD
ORDER BY VIEWS DESC LIMIT 1) T2
ON T1.BOARD_ID = T2.BOARD_ID
ORDER BY T1.FILE_ID DESC

