package it.epicode.presentation;

import java.io.IOException;
import java.io.PrintWriter;
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

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ContattoEjb contattoEjb;

	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// INSERT
		/*Contatto contatto = new Contatto();
		NumTelefono n1 = new NumTelefono("3725964782");
		NumTelefono n2 = new NumTelefono("3256584795");

		n1.setContatto(contatto);
		n2.setContatto(contatto);

		List<NumTelefono> numeri = new ArrayList<NumTelefono>();
		numeri.add(n1);
		numeri.add(n2);

		contatto.setNome("Mario");
		contatto.setCognome("Rossi");
		contatto.setEmail("christian00.vitale@gmail.com");
		contatto.setNumTelefoni(numeri);

		contattoEjb.insertContatto(contatto);*/

		// GET BY COGNOME
		List<Contatto> contatti = contattoEjb.getContattoByCognome("a");
		for (Contatto contatto : contatti) {
			out.println(
					"<h1>" + contatto.getNome() + " " + contatto.getCognome() + " " + contatto.getEmail() + "</h1>");
			out.println("<h1>Numeri:</h1>");
			for (NumTelefono n : contatto.getNumTelefoni()) {
				out.println("<h3>" + n.getNumeroTelefonico() + "</h3>");
			}
		}

		// GET BY NUMERO
		/*
		 * Contatto contatto = contattoEjb.getContattoByNumero("3208293479");
		 * out.println("<h1>"+ contatto.getNome() + " " + contatto.getCognome() + " " +
		 * contatto.getEmail()+ "</h1>"); out.println("<h1>Numeri:</h1>");
		 * for(NumTelefono n : contatto.getNumTelefoni()) {
		 * out.println("<h3>"+n.getNumeroTelefonico()+"</h3>"); }
		 */

		// UPDATE
		/*
		 * Contatto contatto = new Contatto(); NumTelefono n1 = new
		 * NumTelefono("3208293479"); NumTelefono n2 = new NumTelefono("3715926893");
		 * 
		 * n1.setContatto(contatto); n2.setContatto(contatto);
		 * 
		 * List<NumTelefono> numeri = new ArrayList<NumTelefono>(); numeri.add(n1);
		 * numeri.add(n2);
		 * 
		 * contatto.setId(1l); contatto.setNome("Gino"); contatto.setCognome("Vitale");
		 * contatto.setEmail("gino.vitale@gmail.com"); contatto.setNumTelefoni(numeri);
		 * 
		 * contattoEjb.updateContatto(1l,contatto);
		 */
		// contattoEjb.deleteContatto(1l);
	}

}
