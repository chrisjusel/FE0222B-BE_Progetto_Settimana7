package it.epicode.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import it.epicode.business.ContattoEjb;
import it.epicode.data.Contatto;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class VisualizzaContatti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Dependency injection Viene dichiarato un oggetto attraverso
	 * l'annotazione @EJB; tale oggetto non ha bisogno di essere istanziato
	 */
	@EJB
	ContattoEjb contattoEjb;

	public VisualizzaContatti() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Questo metodo si occupa di inviare all'interfaccia utente i dati dei contatti per mostrarli;
	 * Viene creata una lista di contatti e riempita attraverso il metodo corrispondente nell'EJB;
	 * Se sono presenti elementi in questa lista, 
	 * 		essa viene passata alla sessione corrente e si
	 * 		viene reindirizzati alla pagina che mostrerà a video i dati;
	 * altrimenti
	 * 		viene mostrato un messaggio indicante che non ci sono contatti da mostrare
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<Contatto> contatti = contattoEjb.getAllContatti();
		if (contatti.size() != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("visualizzaContattiSession", contatti);
			request.getServletContext().getRequestDispatcher("/VisualizzaContatti.jsp").forward(request, response);
		} else {
			response.setContentType("text/html");
			out.println("<h1>Nessun contatto da mostrare</h1>");
		}
	}

}
