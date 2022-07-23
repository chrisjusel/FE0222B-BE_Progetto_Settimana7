package it.epicode.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.epicode.business.ContattoEjb;
import it.epicode.data.Contatto;
import it.epicode.data.NumTelefono;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModificaContatto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Dependency injection
	 * Viene dichiarato un oggetto attraverso l'annotazione @EJB;
	 * tale oggetto non ha bisogno di essere istanziato
	 */
    @EJB
    ContattoEjb contattoEjb;
	
    public ModificaContatto() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Questo metodo si occupa di prendere i dati inseriti dall'utente, di creare un nuovo contatto, di modificare i suoi dati
     * e di aggiungere i numeri di telefono che sono stati inseriti nel form;
     * Vengono presi e salvati i dati inseriti dall'utente;
     * Viene caricato, se esiste, il contatto corrispondente all'id che l'utente ha inserito;
     * Vengono settati i suoi dati con i nuovi dati inseriti dall'utente;
     * Vengono creati due nuovi numeri telefonici e inizializzati con quelli inseriti dall'utente;
     * Tali numeri vengono collegati al contatto sta venendo creato;
     * Viene creata una lista di numeri telefonici ed i numeri precedentemente inizializzati le vengono
     * aggiunti;
     * Viene collegato il contatto alla lista di numeri;
     * Il contatto nel database viene modificato se già esisteva;
     * Ne viene inserito uno nuovo se non era già presente;
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idContatto"));
		String nome = request.getParameter("nomeContatto");
		String cognome = request.getParameter("cognomeContatto");
		String email = request.getParameter("emailContatto");

		String numeroTelefonico = request.getParameter("numeroTelefonico");
		String numeroTelefonicoSecondario = request.getParameter("numeroTelefonicoSecondario");
		
		Contatto contatto = contattoEjb.trovaContatto(id);
		contatto.setId(id);
		contatto.setNome(nome);
		contatto.setCognome(cognome);
		contatto.setEmail(email);
		
		NumTelefono n1 = new NumTelefono(numeroTelefonico);
		NumTelefono n2 = new NumTelefono(numeroTelefonicoSecondario);
		n1.setContatto(contatto);
		n2.setContatto(contatto);
		List<NumTelefono> numeri = new ArrayList<NumTelefono>();
		numeri.add(n1);
		numeri.add(n2);
		
		contatto.setNumTelefoni(numeri);
		
		contattoEjb.updateContatto(contatto);

	}

}
