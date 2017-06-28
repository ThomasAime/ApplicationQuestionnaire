package Servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.QuestionBean;
import data.Question;

public class GestionQuestionBean extends HttpServlet{

//Generation des questions en random
	
	private static final long serialVersionUID = 1L;
		public static final String ATT_MESSAGES = "messages";
	    public static final String VUE = "/WEB-INF/PasserQuestionnaire.xhtml";

	    private List<Question> listeQuestions = new ArrayList<Question>();
	    
	    public void setListeQuestions(List<Question> list)
	    {
	    	this.listeQuestions=list;
	    }
	    
	    public List<Question> getListeQuestions()
	    {
	    	return this.listeQuestions;
	    }
	    
	    /*
	    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	        // Initialisation de l'objet Java et récupération des messages 
	        QuestionBean qb = new QuestionBean();
	        Question q = new Question();
	        List<String> messages = new ArrayList<String>();
	        q.setEnonce("nouvel enonce");
	        System.out.println(q.getEnonce());
	        messages.add(q.getEnonce());
	        messages.add(q.getIdQuestion()+" ");
	        messages.add(q.getNomCreateur());
	   
	        // Enregistrement de la liste des messages dans l'objet requête 
	        request.setAttribute( ATT_MESSAGES, messages );
	      //  System.out.println("Resultat requete : "+ messages);
	        // Transmission vers la page en charge de l'affichage des résultats 
	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

	}
*/
	
}
