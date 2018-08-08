package dirty

import groovy.transform.ToString

@ToString(includes='email', includeNames=true, includePackage=false)
class User implements Serializable {
	
	private static final long serialVersionUID = 1
	
	String email
	String name
	String password
	Set<Team> teams = []
	
	static constraints = {
		email		blank: false, unique: true, email: true
		name		blank: false
		password	blank: false
	}
	
	static mapping = {
		collection 'User'
		
		id name: 'email', generator: 'assigned'
		sort 'name'
		
		version false
	}

	static embedded = [ 'teams' ]
	
	def beforeInsert() {
		encodePassword()
	}
	
	def beforeUpdate() {
		if(isDirty('password')) {
			encodePassword()
		}
	}
	
	protected void encodePassword() {
		password = "_${password}_"	// naive encoding
	}

}


@ToString(includes='id', includeNames=true, includePackage=false)
class Team {

	String id
	
	static constraints = {
		id     	blank: false, unique: true
	}

	static mapping = {
		version false
	}
	
	Team(String id) {
		this.id = id
	}

}