<%@ page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>iCareer</title>
<script type="text/javascript" src="http://platform.linkedin.com/in.js">
  api_key: szjhpz02hlso
  authorize: true
</script>
</head>
<body>
	<div class="container">
		<div id="header" class="span-22 last prepend-top">
			<div class="logo">
				<a href="/"><img src="/img/career-frontpage.jpg" alt="Career" /></a>
			</div>
		</div>
		<hr />
		<h2>Connect The Dots</h2>
		<div class="span-15">
			<div class="span-13 last">
				<p>Confused between which career path to chose in your career.</p>
				<p>Sign in to access career paths to:</p>
				<ul>
					<li>Select one among hundreds of career paths</li>
					<li>Create custom career path</li>
					<li>Know salaries and aim for them</li>
					<li>Get advice from people who have achieved such</li>
					<li>Give advice to others and be a part of it</li>
				</ul>
			</div>
		</div>
	<!-- 	<script type="IN/Login"> -->
		<form action="/oauth" method="GET">
<%-- 		<p>User Name: <input type="text" name="userName" /></p>
		<p>Your Password: <input type="password" name="password" /></p>
		<p>Your Name: <input type="text" name="name" value="<?js= firstName ?> <?js= lastName ?>" /></p>
		<input type="hidden" name="linkedin-id" value="<?js= id ?>" />
 --%>		<input type="submit" name="submit" value="LinkedIn Sign In"/>
		</form>
		<!-- </script> -->
	</div>
</body>
</html>

