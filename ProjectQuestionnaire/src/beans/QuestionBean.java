package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.jdbc.Statement;

import HibernateUtil.HibernateUtil;
import data.*;


import beans.ReponseQuestionBean;

@ManagedBean(name = "Questions")
//@SessionScoped
@ApplicationScoped
public class QuestionBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Question questionCourante;
	
	private List<Question> listeQuestions = new ArrayList<Question>();
	
	 private List<String> messages = new ArrayList<String>();
	 
	 
	 //temporaire pour tester sans avoir a dev QuestionCandidat tout de suite
	 private String message= new String();
	
	 public void SetMessage(String m)
	 {
		 this.message=m;
	 }
	 public String getMessage()
	 {
		 return this.message;
	 }
	 
	 
	 public List<String> getMessages()
		{
			return this.messages;
		}
		
		public void setMessages(List<String> q)
		{
			this.messages=q;
		}
	
	
	
	 @PostConstruct
	    public void init() {
		 
		 questionCourante = new Question();
		 //getAllQuestions2();
	 }
	 
	 
	public QuestionBean()
	{
		
	}
	
	public Question getQuestionCourante()
	{
		return this.questionCourante;
	}
	
	public void setQuestionCourante(Question q)
	{
		this.questionCourante=q;
	}
	
	public void majQuestionCourante()
	{
		// a changer par la suite pour que la maj se fasse lors du clic du bouton, init au premier element de la liste
		this.questionCourante=listeQuestions.get(0);
		
		
		// getReponseByQuestionId(this.questionCourante.getIdQuestion());
	
		
		//maj de la question courante et sinon on fait un next sur la liste des questions 
	}
	
	
	public List<Question> getListeQuestions()
	{
		return this.listeQuestions;
	}
	 
	public void setListeQuestion(List<Question> list)
	{
		this.listeQuestions=list;
	}
	
	
	/*
	
	public void addQuestion()
	{
		Question quest = questionCourante;
		 Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            session.save(quest);
	            session.getTransaction().commit();
	        } catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	        //	session.getTransaction().commit();
	            //session.flush();
	            session.close();
	        }
	}
	
	public void deleteQuestion(int questId) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Question qu = (Question) session.load(Question.class, new Integer(questId));
            session.delete(qu);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
 
    public void updateQuestion(Question quest) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(quest);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }*/
 
    private List liste;
    //a retransformer en List<Question> quand aura compris comment fonctinone la recuperatino de la requete
    public /*List<String>*/ String getAllQuestions() {
        List<String> list = new ArrayList<String>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //Query quer= session.createQuery("select nomCreateur from Question");
            @SuppressWarnings("deprecation")
			Query quer = session.createSQLQuery("from Question where IdTheme = 1").addEntity(Question.class);
            List result = quer.list();
            
            list = quer.list();
            this.liste=list;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
           // session.flush();
        	//session.getTransaction().commit();
            session.close();
        }
        
        this.messages=list;
        return "success";
   //     return list;
    }
 
    @SuppressWarnings("deprecation")
	public String getAllQuestions2() {
        List<String> list = new ArrayList<String>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //Query quer= session.createQuery("select nomCreateur from Question");
            Query quer = session.createSQLQuery("SELECT e.* FROM Question e").addEntity(Question.class);
            
            
            this.listeQuestions= quer.list();
          //  list = quer.list();
            this.liste=list;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
           // session.flush();
        	//session.getTransaction().commit();
            session.close();
        }
        
      //  this.messages=list;
        return "success";
   //     return list;
    }
    
    
    @SuppressWarnings("deprecation")
	public String getQuestionByTheme(int idThem) {
        List<String> list = new ArrayList<String>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //Query quer= session.createQuery("select nomCreateur from Question");
            Query quer = session.createSQLQuery("SELECT e.* FROM Question e WHERE e.IdTheme= :theme").addEntity(Question.class);
            quer.setParameter("theme", idThem);
            
            this.listeQuestions= quer.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        this.majQuestionCourante();
        
        return "success";
    }
    
    
    
    
    
    
    public List<Question> getQuestionById(String questId) {
        System.out.println(questId);
//        Customer cust = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Question where concat(IdQuestion, ' ', EnonceQuestion) = :id";
            Query query = session.createQuery(queryString);
            query.setString("id", questId);
            //cust = (Customer) query.uniqueResult();
            List<Question> list = query.list();
            if (list.size() > 0) {
                return list;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
        	
            session.flush();
            session.close();
        }
        return null;
    }
	/*
	public List<String> executerTests( HttpServletRequest request ) {
	   //  Chargement du driver JDBC pour MySQL 
	    try {
	        messages.add( "Chargement du driver..." );
	        Class.forName( "com.mysql.jdbc.Driver" );
	        messages.add( "Driver chargé !" );
	    } catch ( ClassNotFoundException e ) {
	        messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
	                + e.getMessage() );
	    }
	    
	    // Connexion à la base de données 
	    
	    	
	    String url = "jdbc:mysql://localhost:3306/applicationquestionnaire?autoReconnect=true&useSSL=false";
	    System.out.println("Connexion bd");
	    String utilisateur = "root";
	    String motDePasse = "";
	    Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
	    try {
	        messages.add( "Connexion à la base de données..." );
	        connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
	        messages.add( "Connexion réussie !" );

	        // Création de l'objet gérant les requêtes 
	        statement = (Statement) connexion.createStatement();
	        
	        // Exécution d'une requête d'écriture 
            int statut = statement.executeUpdate( "INSERT INTO REPONSE (idReponse, enonceReponse, idQuestion, BonneReponse) VALUES ('3', 'question?', 3, 3);" );

            // Formatage pour affichage dans la JSP finale. 
            messages.add( "Résultat de la requête d'insertion : " + statut + "." );
	        
	        messages.add( "Objet requête créé !" );

	        
	        //Exécution d'une requête de lecture 
	        resultat = statement.executeQuery( "SELECT idReponse, enonceReponse, idQuestion, BonneReponse FROM Reponse	;" );
	        messages.add( "Requête \"SELECT idReponse, enonceReponse, idQuestion, BonneReponse FROM Reponse	;\" effectuée !" );
	 
	        // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            int idReponse = resultat.getInt( "idReponse" );
	            String enonceReponse = resultat.getString( "enonceReponse" );
	            String idQuestion = resultat.getString( "idQuestion" );
	            int BonneReponse = resultat.getInt( "BonneReponse" );
	            // Formatage des données pour affichage dans la JSP finale. 
	            messages.add( "Données retournées par la requête : id = " + idReponse + ", email = " + enonceReponse
	                    + ", motdepasse = "
	                    + idQuestion + ", nom = " + BonneReponse + "." );          
	            
	            
	            
	        }
	    } catch ( SQLException e ) {
	        messages.add( "Erreur lors de la connexion : <br/>"
	                + e.getMessage() );
	    } finally {
	        messages.add( "Fermeture de l'objet ResultSet." );
	        if ( resultat != null ) {
	            try {
	                resultat.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
	        messages.add( "Fermeture de l'objet Statement." );
	        if ( statement != null ) {
	            try {
	                statement.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
	        messages.add( "Fermeture de l'objet Connection." );
	        if ( connexion != null ) {
	            try {
	                connexion.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
	    }

	    return messages;
	}
	
	*/
}
