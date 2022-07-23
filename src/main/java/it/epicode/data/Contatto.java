package it.epicode.data;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/**
 * Questa è una classe entità, definita attraverso l'annotazione entity;
 * Presenta tre NamedQuery:
 * 1. recupero di tutti i contatti dal database
 * 2. recupero di un contatto ricercandolo in base alla totalità o ad una parte del suo cognome
 * 3. recupero di un contatto ricercandolo in base al suo numero di telefono
 * @author chris
 *
 */
@Entity
@Table(name = "Contatto")
@NamedQuery(name = "getAllConatti", query = "SELECT c FROM Contatto c")
@NamedQuery(name = "getContattoByCognome", query = "SELECT c FROM Contatto c WHERE c.cognome like :cognome")
@NamedQuery(name = "getContattoByNumero", query = "SELECT c FROM Contatto c WHERE c.id = ANY(SELECT n.contatto.id FROM NumTelefono n WHERE n.numeroTelefonico = :numero)")
public class Contatto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private List<NumTelefono> numTelefoni;

	/**
	 * Dichiarazione della chiave primaria dell'entità Contatto;
	 * E' stato impostato il nome della tabella del database a "id_contatto";
	 * E' stato impostato un metodo di creazione della chiave di tipo AUTO, ciò affida la gestione di tale valore all' ORM sottostante;
	 * E'stato deciso di posizionarla sul getter per garantire un metodo di caricamento di default di tipo lazy;
	 * L'attributo id è di tipo Long per fornire un range di id maggiore rispetto all'intero
	 * 
	 * @return
	 */
	@Id
	@Column(name = "id_contatto")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	/**
	 * Dichiarazione dell'esistenza di una relazione uno-a-molti con l'entità NumTelefono;
	 * Mappata tramite il campo "contatto" nella classe NumTelefono;
	 * E' stato impostato un CascadeType All in modo da garantire che le modifiche effettuate
	 * all'entità Contatto si propaghino anche sull'entità NumTelefono
	 * @return
	 */
	@OneToMany(mappedBy = "contatto", cascade = CascadeType.ALL)
	public List<NumTelefono> getNumTelefoni() {
		return numTelefoni;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNumTelefoni(List<NumTelefono> numTelefoni) {
		this.numTelefoni = numTelefoni;
	}
}
