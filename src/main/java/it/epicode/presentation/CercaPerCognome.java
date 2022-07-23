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

public class CercaPerCognome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Dependency injection
	 * Viene dichiarato un oggetto attraverso l'annotazione @EJB;
	 * tale oggetto non ha bisogno di essere istanziato
	 */
	@EJB
	ContattoEjb contattoEjb;
	
    public CercaPerCognome() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Questo metodo si occupa di ricercare e fornire all'interfaccia utente i dati dei contatti
     * trovati attraverso il metodo di ricerca per cognome;
     * Viene preso in input e salvato il cognome che l'utente vuole ricercare;
     * Viene dichiarata una nuova lista di contatti che conterrà tutti i contatti risultanti
     * utilizzando il metodo di ricerca per cognome implementato nell'EJB;
     * Se nella lista sono presenti elementi,
     * 		i dati vengono passati alla sessione corrente e si viene
     * 		reindirizzati alla pagina di visualizzazione.
     * Altrimenti
     * 		Viene mostrato un messaggio che indica l'assenza di contatti
     * 
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String cognome = request.getParameter("cognomeContatto");
		List<Contatto> contatti = contattoEjb.getContattoByCognome(cognome);
		
		if(contatti.size() != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("visualizzaContattiSession", contatti);
			request.getServletContext().getRequestDispatcher("/VisualizzaContatti.jsp").forward(request, response);
		} else {
			response.setContentType("text/html");
			out.println("<h1>Nessun contatto trovato</h1>");
		}
	}

}
