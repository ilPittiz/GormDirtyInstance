package dirty

class BootStrap {
	
	DemoService demoService

    def init = { servletContext ->
		demoService.resetUsers()
    }
	
    def destroy = {
    }
	
}
