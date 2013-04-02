SELECT 
    `resultsftptest`.`MsgTime`, 
    `resultsftptest`.`throughput`, 
    `resultsftptest`.`MsgId`, 
    ROUND(AVG(`hsdpacqi`.`sumCQI` / `hsdpacqi`.`numCQI`)) AS cqi, 
    `wcdmaactiveset`.`AggrEcIo` as ecio,
    `wcdmaactiveset`.`AggrRSCP` as rscp, 
    `position`.`latitude`, 
    `position`.`longitude`, 
    `position`.`speed`, 
    `resultsftptest`.`SessionId`, 
    `resultsftptest`.`errorCode`, 
    `networkinfo`.sc1, 
    `wcdmaactiveset`.`PrimScCode` as primScCode 
FROM 
    `resultsftptest` 
        JOIN 
    `hsdpacqi` ON `resultsftptest`.`MsgTime` = `hsdpacqi`.`MsgTime` 
        JOIN 
    `wcdmaactiveset` ON `hsdpacqi`.`MsgTime` = `wcdmaactiveset`.`MsgTime` 
        JOIN 
    `position` ON `resultsftptest`.`PosId` = `position`.`PosId` 
        JOIN 
    `networkinfo` ON `resultsftptest`.`NetworkId` = `networkinfo`.`NetworkId` 
WHERE 
    `resultsftptest`.SESSIONID between 20 and 60  
GROUP BY msgTime 
ORDER by msgTime 
