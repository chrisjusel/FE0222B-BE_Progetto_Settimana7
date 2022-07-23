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

public class InserisciContatto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Dependency injection
	 * Viene dichiarato un oggetto attraverso l'annotazione @EJB;
	 * tale oggetto non ha bisogno di essere istanziato
	 */
	@EJB
	ContattoEjb contattoEjb;

	public InserisciContatto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Questo metodo si occupa dell'inserimento di un nuovo contatto con i suoi relativi numeri telefonici;
	 * Vengono presi da input e salvati tutti i parametri necessari alla creazione di un nuovo contatto;
	 * Viene creato un nuovo oggetto Contatto;
	 * Vengono utilizzati i suoi setter per impostare i suoi attributi;
	 * Viene creata una lista di numeri telefonici;
	 * Vengono creati due oggetti NumTelefono e gli viene settato il contatto al quale sono associati;
	 * Il primo numero viene subito inizializzato con il parametro del numero telefonico
	 * scelto dall'utente e salvato precedentemente un oggetto String (tale parametro esisterà sempre,
	 * in quanto il campo del primo numero telefonico nel form è obbligatorio);
	 * Il numero telefonico secondario viene inizializzato SOLO SE è stato inserito dall'utente;
	 * Entrambi i numeri vengono aggiunti alla lista di numeri precedentemente creata e tale lista viene
	 * associata al contatto che è stato creato;
	 * Viene scatenato il metodo dell'EJB che preso in input il contatto, ormai già costruito, inserisce i dati
	 * nel database;
	 * Infine si viene reindirizzati alla pagina home;
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nomeContatto");
		String cognome = request.getParameter("cognomeContatto");
		String email = request.getParameter("emailContatto");

		String numeroTelefonico = request.getParameter("numeroTelefonico");
		String numeroTelefonicoSecondario = request.getParameter("numeroTelefonicoSecondario");

		Contatto contatto = new Contatto();
		contatto.setNome(nome);
		contatto.setCognome(cognome);
		contatto.setEmail(email);

		List<NumTelefono> numeri = new ArrayList<NumTelefono>();
		NumTelefono numero1 = new NumTelefono(numeroTelefonico);
		numero1.setContatto(contatto);
		numeri.add(numero1);

		NumTelefono numero2;
		if (!numeroTelefonicoSecondario.equals("")) {
			numero2 = new NumTelefono(numeroTelefonicoSecondario);
			numero2.setContatto(contatto);
			numeri.add(numero2);
		}

		contatto.setNumTelefoni(numeri);

		contattoEjb.insertContatto(contatto);

		request.getServletContext().getRequestDispatcher("/index.html").forward(request, response);

	}

}
