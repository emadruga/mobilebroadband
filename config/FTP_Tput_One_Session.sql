SELECT
	Sessions.SessionId  AS 'sessionid',
	ResultsFTPTest.msgTime  AS 'msgtime', 
        NetworkInfo.LAC,
        NetworkInfo.MCC  AS MCC,
        NetworkInfo.MNC  AS MNC,
	NetworkInfo.CID,
	NetworkInfo.Technology,
        ROUND(ResultsFTPTest.throughput*8/1000) AS 'throughput',
	NetworkInfo.BCCH AS NI_BCCH,
	NetworkInfo.SC1 AS NI_SC1,
	NetworkInfo.SC2 AS NI_SC2, 
	NetworkInfo.SC3 AS NI_SC3,
	Position.PosId,
	Sessions.FileId,
	Sessions.SessionId,
	ResultsFTPTest.NetworkId 
FROM  
	Sessions, ResultsFTPTest, Position, TestInfo, NetworkInfo 
WHERE
	Sessions.Valid = 1 AND
	TestInfo.Valid = 1 AND
	ResultsFTPTest.PosId = Position.PosId AND
	Sessions.sessionId = TestInfo.sessionId AND
	ResultsFTPTest.NetworkId = NetworkInfo.NetworkId AND
	TestInfo.TestId = ResultsFTPTest.TestId AND
	ResultsFTPTest.lastBlock = 0 AND
	Sessions.SessionId = 600
ORDER BY
	Sessions.SessionId, ResultsFTPTest.msgTime
