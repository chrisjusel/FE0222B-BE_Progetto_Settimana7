package it.epicode.data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
/**
 * Questa è una classe entità, definita attraverso l'annotazione entity;
 * @author chris
 *
 */
@Entity
public class NumTelefono implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String numeroTelefonico;
	private Contatto contatto;

	public NumTelefono() {
	}

	public NumTelefono(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	/**
	 * Dichiarazione della chiave primaria dell'entità NumTelefono;
	 * E'stato impostato il nome della tabella del database a "numero_telefonico";
	 * E'stato impostato un metodo di creazione della chiave di tipo AUTO, ciò affida la gestione di tale valore all' ORM sottostante;
	 * E'stato deciso di posizionarla sul getter per garantire un metodo di caricamento di default di tipo lazy;
	 * 
	 * @return
	 */
	@Id
	@Column(name = "numero_telefonico")
	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	/**
	 * Dichiarazione dell'esistenza di una relazione molti-a-uno con l'entità Contatto;
	 * E'stato impostato il nome della tabella che conterrà la chiave esterna il quale valore
	 * corrisponderà all'id del Contatto nella sua relativa tabella
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "id_contatto")
	public Contatto getContatto() {
		return contatto;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public void setContatto(Contatto contatto) {
		this.contatto = contatto;
	}

}
