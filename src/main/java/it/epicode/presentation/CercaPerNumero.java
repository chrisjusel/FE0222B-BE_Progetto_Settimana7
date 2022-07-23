package it.epicode.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import it.epicode.business.ContattoEjb;
import it.epicode.data.Contatto;

public class CercaPerNumero extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Dependency injection
	 * Viene dichiarato un oggetto attraverso l'annotazione @EJB;
	 * tale oggetto non ha bisogno di essere istanziato
	 */
	@EJB
	ContattoEjb contattoEjb;

	public CercaPerNumero() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Questo metodo si occupa di ricercare e fornire all'interfaccia utente i dati del contatto
     * trovato attraverso il metodo di ricerca per numero telefonico;
     * Viene preso in input e salvato il numero di telefono che l'utente vuole ricercare;
     * Viene dichiarato una nuovo Contatto che conterrà il contatto risultante
     * utilizzando il metodo di ricerca per numero telefonico implementato nell'EJB;
     * se è stato trovato un contatto, 
     * 		tale dato viene passato alla sessione corrente e si viene
     * 		reindirizzati alla pagina che mostrerà i dati all'utente;
     * altrimenti
     * 		Viene mostrato un messaggio che indica l'assenza di contatti con quel numero di telefono
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String numero = request.getParameter("numeroContatto");
		Contatto contatto = contattoEjb.getContattoByNumero(numero);

		if (contatto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("visualizzaContatto", contatto);
			request.getServletContext().getRequestDispatcher("/CercaPerNumero.jsp").forward(request, response);
		} else {
			response.setContentType("text/html");
			out.println("<h1>Nessun contatto trovato</h1>");
		}
	}

}
