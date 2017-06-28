package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Statement;

public class TestJDBC {

	
	private List<String> messages = new ArrayList<String>();
	
	public List<String> executerTests( HttpServletRequest request ) {
	    /* Chargement du driver JDBC pour MySQL */
	    try {
	        messages.add( "Chargement du driver..." );
	        Class.forName( "com.mysql.jdbc.Driver" );
	        messages.add( "Driver chargé !" );
	    } catch ( ClassNotFoundException e ) {
	        messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
	                + e.getMessage() );
	    }
	    
	    /* Connexion à la base de données */
	    
	    	
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

	        /* Création de l'objet gérant les requêtes */
	        statement = (Statement) connexion.createStatement();
	        
	        /* Exécution d'une requête d'écriture */
            int statut = statement.executeUpdate( "INSERT INTO REPONSE (idReponse, enonceReponse, idQuestion, BonneReponse) VALUES ('3', 'question?', 3, 3);" );

            /* Formatage pour affichage dans la JSP finale. */
            messages.add( "Résultat de la requête d'insertion : " + statut + "." );
	        
	        messages.add( "Objet requête créé !" );

	        /* Exécution d'une requête de lecture */
	        resultat = statement.executeQuery( "SELECT idReponse, enonceReponse, idQuestion, BonneReponse FROM Reponse	;" );
	        messages.add( "Requête \"SELECT idReponse, enonceReponse, idQuestion, BonneReponse FROM Reponse	;\" effectuée !" );
	 
	        /* Récupération des données du résultat de la requête de lecture */
	        while ( resultat.next() ) {
	            int idReponse = resultat.getInt( "idReponse" );
	            String enonceReponse = resultat.getString( "enonceReponse" );
	            String idQuestion = resultat.getString( "idQuestion" );
	            int BonneReponse = resultat.getInt( "BonneReponse" );
	            /* Formatage des données pour affichage dans la JSP finale. */
	            messages.add( "Données retournées par la requête : id = " + idReponse + ", email = " + enonceReponse
	                    + ", motdepasse = "
	                    + idQuestion + ", nom = " + BonneReponse + "." );
	            
	            /*System.out.println( "Données retournées par la requête : id = " + idReponse + ", email = " + enonceReponse
	                    + ", motdepasse = "
	                    + idQuestion + ", nom = " + BonneReponse + "." );*/
	            
	            
	            
	            
	            
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
	

}
