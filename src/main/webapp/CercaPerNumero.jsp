<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="it.epicode.data.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<link rel="stylesheet" href="assets/css/style.css">
<title>Prelievo</title>
</head>
<body>
<% 
	Contatto contatto = (Contatto)session.getAttribute("visualizzaContatto");
%>
	<div class="container text-center">
		<div class="operazioni">
			<div>
				<div class="d-block">
					<h1>Contatto:</h1>
					<h3><%=contatto.getNome() + " " + contatto.getCognome()%></h3>
					<h3><%=contatto.getEmail()%></h3>
					<h2>Numeri:</h2>
					<%for(NumTelefono n : contatto.getNumTelefoni()){
						%>
							<h3><%=n.getNumeroTelefonico()%></h3>
						<%
					}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>