SELECT 
	Position.PosId,
	Sessions.sessionId as 'sessionid', 
	MIN(Position.msgtime)  as 'msgtime',
	HSDPACQI.TestId,
	HSDPACQI.NetworkId,
	NetworkInfo.Technology,
        NetworkInfo.LAC,
        NetworkInfo.MCC  AS MCC,
        NetworkInfo.MNC  AS MNC,
	NetworkInfo.CID,
	NetworkInfo.SC1 as NI_SC1,
	NetworkInfo.SC2 as NI_SC2,
	NetworkInfo.SC3 as NI_SC3,
	Round(Avg(WCDMAActiveSet.AggrEcIo),1) As 'AvgEcIo',
	Round(Avg(WCDMAActiveSet.AggrRSCP),1) As 'AvgRSCP',
	Sum(HSDPACQI.sumCQI) as 'SumCQI',
	Sum(HSDPACQI.numCQI) as 'NumCQI',
	ROUND(Avg(HSDPACQI.sumCQI/HSDPACQI.numCQI),1) as CQI,
 	ROUND(ResultsFTPTest.throughput*8/1000,1) as 'throughput'
FROM 
	Sessions, Position,
	ResultsFTPTest, TestInfo,
	WCDMAActiveSet, NetworkInfo, HSDPACQI
WHERE 
	Sessions.Valid = 1 AND
	TestInfo.Valid = 1 AND
	Sessions.sessionId = TestInfo.sessionId AND
	Sessions.sessionId = WCDMAActiveSet.sessionId AND
	TestInfo.TestId = ResultsFTPTest.TestId AND
	TestInfo.TestId = WCDMAActiveSet.TestId  AND
	ResultsFTPTest.PosId = Position.PosId AND
	WCDMAActiveSet.PosId = Position.PosId AND
	HSDPACQI.PosId = Position.PosId AND
	ResultsFTPTest.NetworkId = NetworkInfo.NetworkId AND
	WCDMAActiveSet.NetworkId = NetworkInfo.NetworkId AND
	HSDPACQI.NetworkId = NetworkInfo.NetworkId AND
	ResultsFTPTest.lastBlock = 0 AND
	HSDPACQI.numCQI>0 AND 
	Position.msgtime > "2012-10-11" AND
	Position.msgtime < "2012-10-13" 
GROUP BY
	Position.PosId,
	Sessions.SessionId,
	HSDPACQI.TestId,
	HSDPACQI.NetworkId
ORDER BY
	Sessions.SessionId,
        Position.msgtime