<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="it.epicode.data.*"%>
<%@ page import="java.util.List"%>
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
<title>FE0222-BE Progetto Settimana 7</title>
</head>
<body>
	<%
	List<Contatto> cont = (List<Contatto>) session.getAttribute("visualizzaContattiSession");
	%>

	<div class="text-center">
		<h1>Contatti:</h1>
	</div>

	<div class="container">
		<div class="row">
			<%
			for (Contatto c : cont) {
			%>
			<div class="col-lg-3">
				<div class="card m-3 text-center" style="width: 18rem">
					<p><%=c.getNome() + " " + c.getCognome()%><br>
					<p><%=c.getEmail()%></p>
					<h3>Numeri:</h3>
					<%
					for (NumTelefono n : c.getNumTelefoni()) {
					%>
					<p><%=n.getNumeroTelefonico()%></p>
					<%
					}
					%>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>