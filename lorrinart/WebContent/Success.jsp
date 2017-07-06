<%@page import="clientvideo.dao.ClientService"%>
<%@page import="clientvideo.util.JsonUtil"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/main/css/main.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/main/js/main.js"></script>

</head>
<body>

	<header>
		<div class="top-wrapper">
			<h3>Login successful.</h3>
			<a href="login.html">Login Page</a>
		</div>

		<div class="tab">
			<button class="tablinks"
				onclick="showTab(event, 'All');showClients();" id="defaultOpen">All</button>
			<button class="tablinks" onclick="showTab(event, 'Add')">Add</button>
			<button class="tablinks" onclick="showTab(event, 'Remove')">Remove</button>
		</div>
		<%
			ClientService allClients = new ClientService();

			String showAllClients = JsonUtil.toJson(allClients.findAll());
			System.out.println(showAllClients);
		%>


		<div id="All" class="tabcontent">
			<h3>All</h3>
			<table id="clients">
				<tr>
					<th><input type="checkbox" id="selectAll"
						onclick="isAllChecked()" /></th>
					<th>Name_ONE</th>
					<th>Name_TWO</th>
					<th>Name_ONE_IMAGE</th>
					<th>Name_TWO_IMAGE</th>
					<th>LINK_ONE</th>
					<th>LINK_TWO</th>
					<th>USERNAME</th>
					<th>PASSWORD</th>
				<tr class="client">

				</tr>
			</table>
			<script type="text/javascript">
				function showClients() {
					var element, fragment, clients, tr, td, tr_clone = "";
					tr = document.getElementById('clients').getElementsByClassName('client');
					element = document.getElementById('clients');
					fragment = document.createDocumentFragment();
					clients =<%=showAllClients%>;

					clients.forEach(function(client) {
								tr_clone = tr[0].cloneNode(false);
							//	tr_clone.setAttribute('selected', "false");
								tr_clone.onclick = function() {isChecked(this);};
								td = document.createElement('td');
								td.innerHTML = '<input type="checkbox" class="select" />';
								tr_clone.appendChild(td);
								for ( var property in client) {
									if (!(property === 'client_id')) {
										td = document.createElement('td');
										td.textContent = client[property] + " ";
										tr_clone.appendChild(td);
									}
								}
								fragment.appendChild(tr_clone);
							});	
				//	while (element.firstChild.nextSibling) {
				//		element.removeChild(element.firstChild.nextSibling);
			//		}
					element.appendChild(fragment);
			
				}
			</script>
			<p id="demo" onclick="myFunction()">DEMO</p>






		</div>

		<div id="Add" class="tabcontent">
			<form action="addclientservlet" method="get">
				<strong>Name One</strong>:<input type="text" name="nameone"><br>
				<strong>Name Two</strong>:<input type="text" name="nametwo"><br>
				<strong>Name One Image</strong>:<input type="text"
					name="nameoneimage"><br> <strong>Name Two
					Image</strong>:<input type="text" name="nametwoimage"><br> <strong>Link
					One</strong>:<input type="text" name="linkone"><br> <strong>Link
					Two</strong>:<input type="text" name="linktwo"><br> <strong>Password</strong>:<input
					type="text" name="password"><br> <strong>Username</strong>:<input
					type="text" name="username"><br> <input type="submit"
					value="submit">
			</form>
		</div>

		<div id="Remove" class="tabcontent">
			<h3>Remove</h3>
			<p>Tokyo is the capital of Japan.</p>
		</div>







	</header>
	<main> 
	 <nav class="navbar">
  <a href="/edit">Edit</a> 
  <a href="/remove">Remove</a> 
</nav> 
	
	
	
	</main>
	<footer></footer>




	<form action="logoutservlet" method="post">
		<input type="submit" value="Logout">
	</form>

</body>
</html>