package dirty

import grails.transaction.Transactional

@Transactional
class DemoService {
	
	protected static final Set<String> ALL_TEAMS = [ 'acme', 'demo' ]

    protected static void resetUsers() {
		User.where {}.deleteAll()
		[
				[ email: 'user@domain.com', name: 'Simple user', password: 'pwd', teams: ALL_TEAMS.collect { new Team(it) }.toSet() ],
				[ email: 'admin@domain.com', name: 'Admin user', password: 'pwd' ]
		].each {
			new User(it).insert()
			//println "created $it.email"
		}
    }

	protected static List<String> debugBefore(User user) {
		return [ 'USER: ' + user.email ] + debugUser(user, 'BEFORE')
	}
	
	protected static List<String> debugAfter(User user) {
		return [ '\nuser.save()' ] + debugUser(user, 'AFTER')
	}
	
	private static List<String> debugUser(User user, String title = null) {
		List<String> debug = [ '' ]
		if(title)
			debug += "$title:"
		debug += [
				"   user.isDirty() = " + user.isDirty(),
				"   user.getDirtyPropertyNames() = " + user.getDirtyPropertyNames(),
				"   user.isDirty('teams') = " + user.isDirty('teams'),
				"   user.isDirty('password') = " + user.isDirty('password')
		]
		return debug.collect { it.toString() }
	}
	
}
