package dirty

class DemoController {
	
	DemoService demoService
	
	def index() {}

    def resetUsers() {
		demoService.resetUsers()
		redirect action: 'index'
	}
	
	def add() {
		User user = User.findByEmail(params.user)
		user.addToTeams(new Team(params.team))
		user.save()
		redirect action: 'index'
	}
	
	def remove() {
		User user = User.findByEmail(params.user)
		String team = params.team

		List<String> debug = demoService.debugBefore(user)

		user.teams.removeAll { it.id == team }
		user.save()

		debug += demoService.debugAfter(user)
		flash.debug = debug.join('\n')

		redirect action: 'index'
	}
	
	def removeFromTeams() {
		User user = User.findByEmail(params.user)
		String team = params.team

		List<String> debug = demoService.debugBefore(user)
		
		user.removeFromTeams(user.teams.find { it.id == team })
		user.save()
		
		debug += demoService.debugAfter(user)
		flash.debug = debug.join('\n')
		
		redirect action: 'index'
	}

	def save() {
		User user = User.findByEmail(params.user)

		List<String> debug = demoService.debugBefore(user)
		
		user.save()

		debug += demoService.debugAfter(user)
		flash.debug = debug.join('\n')

		redirect action: 'index'
	}
	
}
