package it.epicode.business;

import java.util.List;

import it.epicode.data.Contatto;
import it.epicode.data.NumTelefono;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
@LocalBean
public class ContattoEjbImpl implements ContattoEjb {

	@PersistenceContext(unitName = "RubricaPU")
	private EntityManager em;

	public ContattoEjbImpl() {
	}

	/**
	 * Questo metodo recupera tutti i contatti dalla tabella dei contatti;
	 * Viene creata una nuova NamedQuery passando al metodo createNamedQuery
	 * il nome delle query definito nell'entità Contatto, in questo caso "getAllContatti";
	 * Viene istanziata una nuova Lista che conterrà tutti i contatti trovati;
	 * infine viene restituita
	 *
	 */
	@Override
	public List<Contatto> getAllContatti() {
		Query query = em.createNamedQuery("getAllConatti");
		List<Contatto> contatti = query.getResultList();
		return contatti;
	}

	/**
	 * Questo metodo si occupa dell'inserimento di un nuovo contatto;
	 * Viene utilizzato il metodo persist dell'Entity Manager delegando all'ORM sottostante
	 * la logica di inserimento dell'entità e delle sue relazioni all'interno del db
	 */
	@Override
	public void insertContatto(Contatto contatto) {
		em.persist(contatto);

	}

	/**
	 * Questo metodo si occupa della ricerca dei contatti per cognome;
	 * Viene creata una nova NamedQuery riferita alla query getContattoByCognome
	 * definita nell'entità Contatto;
	 * il parametro "cognome" in input definito nella query viene incorporato in una doppia percentuale
	 * per permettere all'utente di poter ricercare anche sottostringhe del cognome oltre ad, ovviamente,
	 * il cognome per intero.
	 * I risultati di tale query vengono inseriti in una lista di contatti che poi viene restituita in output 
	 */
	@Override
	public List<Contatto> getContattoByCognome(String cognome) {
		Query query = em.createNamedQuery("getContattoByCognome");
		query.setParameter("cognome", "%"+cognome+"%");
		List<Contatto> contatti = query.getResultList();
		return contatti;
	}

	/**
	 * Questo metodo si occupa della ricerca del contatto associato ad un numero telefonico;
	 * Il metodo restituisce solo 1 contatto in quanto il numero telefonico è chiave primaria
	 * della sua entità, ciò vuol dire che non possono esserci 2 numeri telefonici uguali,
	 * ed un numero telefonico, nella sua unicità, può essere associato di conseguenza ad un solo contatto
	 * 
	 * Viene creata la NamedQuery relativa alla query descritta nell'entità Contatto;
	 * il parametro in input di questa query viene settato con il parametro in input al metodo, ovvero il numero;
	 * viene istanziato un nuovo oggetto Contatto che conterrà il risultato della ricerca, e verrà restituito in output
	 * 
	 */
	@Override
	public Contatto getContattoByNumero(String numero) {
		Query query = em.createNamedQuery("getContattoByNumero");
		query.setParameter("numero", numero);
		Contatto contatto = (Contatto) query.getSingleResult();
		return contatto;
	}

	/**
	 * Questo metodo si occupa della modifica di un contatto;
	 * Viene utilizzato il metodo merge dell'Entity Manager delegando all'ORM sottostante
	 * la logica di modifica dell'entità e delle sue relazioni all'interno del db
	 */
	@Override
	public void updateContatto(Contatto contatto) {
		em.merge(contatto);
	}

	/**
	 * Questo metodo si occupa dell'eliminazione di un Contatto e, dato il metodo a cascata
	 * utilizzato per tutte le operazioni, anche dei suoi numeri di telefono.
	 * Se il contatto che si vuole eliminare viene trovato:
	 * 		Il metodo find restituisce l'oggetto di tipo Contatto ricercato per chiave primaria;
	 * 		Il metodo remove prende tale oggetto e lo elimina da db
	 */
	@Override
	public boolean deleteContatto(Long id) {
		if(trovaContatto(id) != null) {
			em.remove(em.find(Contatto.class, id));
			return true;
		}
		return false;
	}

	/**
	 * Questo metodo si occcupa della ricerca del contatto attraverso il suo id;
	 * trovato l'oggetto, esso viene restituito
	 */
	@Override
	public Contatto trovaContatto(Long id) {
		return em.find(Contatto.class, id);
	}


}
