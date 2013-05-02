SELECT 
DATE(`msgtime`) AS 'campaignDate', 
MIN(TIME(`msgtime`)) AS 'startTime', 
MAX(TIME(`msgtime`)) AS 'endTime', 
COUNT(DISTINCT(`sessionid`)) AS 'numSessions',  
COUNT(msgtime) AS 'numSamples'  
FROM 
TABLENAME  
WHERE WHERECLAUSULE 
GROUP BY campaignDate 
ORDER BY numSamples DESC 