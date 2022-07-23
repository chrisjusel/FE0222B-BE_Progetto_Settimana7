package it.epicode.business;

import java.util.List;

import it.epicode.data.Contatto;
import it.epicode.data.NumTelefono;
import jakarta.ejb.Local;
/**
 * Interfaccia contentente i metodi CRUD che permetteranno all'ejb di entrare in contatto
 * con il database sottostante
 * @author chris
 *
 */
@Local
public interface ContattoEjb {
	/**
	 * Si occupa di recuperare tutti i contatti presenti nella relativa tabella del db
	 * @return
	 */
	public List<Contatto> getAllContatti();
	
	/**
	 * Si occupa dell'inserimento di un contatto passatogli dall'esterno
	 * @param contatto
	 */
	public void insertContatto(Contatto contatto);
	
	/**
	 * Passatogli un cognome verrà restituita, se presenti, una lista di contatti
	 * aventi quel cognome
	 * @param cognome
	 * @return
	 */
	public List<Contatto> getContattoByCognome(String cognome);
	
	/**
	 * Passatogli un numero di telefono verrà restituito il contatto avente quel numero di telefono
	 * @param numero
	 * @return
	 */
	public Contatto getContattoByNumero(String numero);
	
	/**
	 * Passatogli un contatto, modificherà i suoi dati aggiungendo nuovi numeri telefonici
	 * @param contatto
	 */
	public void updateContatto(Contatto contatto);
	
	/**
	 * Passatogli l'id di un contatto, esso verrà eliminato
	 * @param id
	 * @return
	 */
	public boolean deleteContatto(Long id);
	
	/**
	 * Passatogli l'id di un contatto, esso verrà restituito
	 * @param id
	 * @return
	 */
	public Contatto trovaContatto(Long id);
}