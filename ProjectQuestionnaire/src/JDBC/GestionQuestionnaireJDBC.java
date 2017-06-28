package JDBC;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.*;

public class GestionQuestionnaireJDBC extends HttpServlet{

	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Initialisation de l'objet Java et récupération des messages */
       // Questionnaire questionnaire = new Questionnaire();
        
        
        
    //    List<String> messages = test.executerTests( request );

        /* Enregistrement de la liste des messages dans l'objet requête */
      
      //  request.setAttribute( ATT_MESSAGES, messages );
      
        //  System.out.println("Resultat requete : "+ messages);
        /* Transmission vers la page en charge de l'affichage des résultats */
        
    //    this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
   
	}
}
