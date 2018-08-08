grails {
	mongodb {
		databaseName = 'dirty'
		options {
			connectionsPerHost = 1000
			threadsAllowedToBlockForConnectionMultiplier = 5
			maxWaitTime = 4000
			socketTimeout = 2000
			sslEnabled = false
			sslInvalidHostNameAllowed = false
		}
	}
}