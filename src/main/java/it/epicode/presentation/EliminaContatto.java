package it.epicode.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import it.epicode.business.ContattoEjb;


public class EliminaContatto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Dependency injection
	 * Viene dichiarato un oggetto attraverso l'annotazione @EJB;
	 * tale oggetto non ha bisogno di essere istanziato
	 */
	@EJB
	ContattoEjb contattoEjb;
	
    public EliminaContatto() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @deprecated Non capisco veramente perchè ma questo metodo effettua la cancellazione a cascata
     * 			   del Contatto e dei suoi numeri associati SOLO SE tale operazione è la prima ad essere
     * 			   effettuata dopo l'avvio del TomEE. Se effettuo prima altre operazioni e poi provo a 
     * 			   cancellare un contatto ricevo l'errore che sto violando il vincolo di chiave esterna;
     * 			   A questo punto credo che sia un mio problema di configurazione.
     * 				
     * 
     * Questo metodo si occupa della selezione del contatto da eliminare da parte dell'utente;
     * Viene preso da input e salvato l'id del contatto che si desidera eliminare;
     * Viene utilizzato il metodo booleano apposito per l'eliminazione presente nell'EJB;
     * Se viene correttamente trovato ed eliminato:
     * 		si viene reindirizzati alla homepage
     * altrimenti
     * 		viene mostrato un messaggio che indica che non è presente nessun contatto con l'id scelto
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Long id = Long.parseLong(request.getParameter("idContatto"));
		if(contattoEjb.deleteContatto(id)) {
			request.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		} else {
			response.setContentType("text/html");
			out.println("<h1>Nessun contatto trovato con questo id</h1>");
		}
		
	}

}
