<%@ page import="dirty.DemoService; dirty.User" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<h2>Users</h2>
<g:link controller="demo" action="resetUsers">Reset users</g:link>
<ul>
    <g:each var="user" in="${dirty.User.list()}">
		<g:set var="userTeams" value="${user.teams.id}"/>
		<g:set var="unassignedTeams" value="${DemoService.ALL_TEAMS - userTeams}"/>
		<li>
			<div><b>${user.name}</b></div>
			<div><i>credentials:</i> ${user.email} / ${user.password}</div>
			<div><i>teams:</i>
				<ul>
					<g:each var="team" in="${userTeams.sort()}">
						<li>${team}
							<ul>
								<li><g:link controller="demo" action="remove" params="[ user: user.email, team: team ]">user.teams.removeAll { it.id = '${team}' }</g:link></li>
								<li><g:link controller="demo" action="removeFromTeams" params="[ user: user.email, team: team ]">user.removeFromTeams(user.teams.find { it.id = '${team}' })</g:link></li>
							</ul>
						</li>
					</g:each>
				</ul>
			</div>
			<div>
				<i>actions</i>:
				<ul>
					<g:each var="team" in="${unassignedTeams.sort()}">
						<li><g:link controller="demo" action="add" params="[ user: user.email, team: team ]">add to '${team}'</g:link></li>
					</g:each>
					<li><g:link controller="demo" action="save" params="[ user: user.email ]">dull save</g:link></li>
				</ul>
			</div>
		</li>
	</g:each>
</ul>

<fieldset>
	<legend>debug (remove/save)</legend>
	<pre>${flash.debug}</pre>
</fieldset>

</body>
</html>
