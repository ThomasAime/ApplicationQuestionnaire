package beans;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import HibernateUtil.HibernateUtil;
import data.*;

@ManagedBean(name = "Questionnaires")
@ApplicationScoped
public class QuestionnaireBean{

	private Questionnaire questionnaire = new Questionnaire();
	
	private List<Question> listeQuestions;
	
	private List<Theme> listeThemes;
	
	private Question questionCourante;
	
	private ReponseQuestion reponseQuestion= new ReponseQuestion();
	
	private  List<ReponseQuestion> reponsesQuestion= new ArrayList<ReponseQuestion>();	
	
	private String idReponseRadio= new String();
	
	private List<String> idReponseCheck = new ArrayList<String>();
	
	private ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	
	public QuestionnaireBean(){  }
	
	public Questionnaire getQuestionnaire() 
	{
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) 
	{
		this.questionnaire = questionnaire;
	}
	
	public List<Question> getListeQuestions()
	{
		return this.listeQuestions;
	}
	
	public void setListeQuestions(List<Question> list)
	{
		this.listeQuestions=list;
	}
	
	public List<Theme> getListeThemes()
	{
		return this.listeThemes;
	}

	public void setListeThemes(List<Theme> list)
	{
		this.listeThemes=list;
	}
	
	public ReponseQuestion getReponseQuestion() {
		return reponseQuestion;
	}

	public void setReponseQuestion(ReponseQuestion rep) {
		this.reponseQuestion = rep;
	}
	
	public List<ReponseQuestion> getReponsesQuestion() {
		return reponsesQuestion;
	}

	public void setReponsesQuestion(List<ReponseQuestion> repp) {
		this.reponsesQuestion = repp;
	}
	
	public Question getQuestionCourante()
	{
		return this.questionCourante;
	}
	
	public void setQuestionCourante(Question q)
	{
		this.questionCourante=q;
	}
	
	public String getIdReponseRadio() {
		return idReponseRadio;
	}

	public void setIdReponseRadio(String idReponseRadio) {
		this.idReponseRadio = idReponseRadio;
	}

	public List<String> getIdReponseCheck() {
		return idReponseCheck;
	}

	public void setIdReponseCheck(List<String> idReponseCheck) {
		this.idReponseCheck = idReponseCheck;
	}
	
	//mise à jour de la question en cours : question que le candidat est en train de répondre
	//String comme type de retour pour la redirection automatique à la fin du questionnaire
	public String majQuestionCourante()
	{
		String retour="";
		
		//ajout de la reponse du candidat à la liste de réponses du questionnaire selon radiobutton ou checkbox
		if(!this.idReponseRadio.isEmpty() && this.idReponseRadio!=null)
		{
			this.questionnaire.AddReponseQuestionListeReponseCandidat(this.getReponseQuestionById(idReponseRadio));
			this.idReponseRadio=new String();
		}
		
		if(this.idReponseCheck!=null&& this.idReponseCheck.size()>0)
		{
			for(int i = 0;i < idReponseCheck.size();i++)
			{
				this.questionnaire.AddReponseQuestionListeReponseCandidat(this.getReponseQuestionById(idReponseCheck.get(i)));
			}
			this.idReponseCheck=new ArrayList<String>();
		}
		
		if(this.questionnaire.getListeQuestions().size()-1==this.questionnaire.getListeQuestions().indexOf(questionCourante))
		{
			//dernière question posée
			retour = "finQuestionnaire";
			saveQuestionnaire();
		}
		else
		{
			//des questions doivent encore être passées
			if (questionCourante != null) {
				this.questionCourante = this.questionnaire.getListeQuestions().get(this.questionnaire.getListeQuestions().indexOf(questionCourante)+1);
			}
			else
			{
				//recuperation première question
				questionCourante=this.questionnaire.getListeQuestions().get(0);
			}
		}
		return retour;
	}
	
	public void saveQuestionnaire()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(this.questionnaire);
        session.getTransaction().commit();
        session.close();
	}
	
	public int calculScoreQuestionnaire(int idQuestionnaire)
	{
		int score = 0;		
		List<ReponseQuestion> repcandidat = new ArrayList<ReponseQuestion>();
		
		//récupération de toutes les réponses données par le candidat pour le questionnaire donné
		repcandidat= getReponseQuestionCandidatByQuestionnaireId(idQuestionnaire);
		
		List<Question> listQuestionsPosees = new ArrayList<Question>();
		
		//jonglage avec seulement les bonnes reponses : les autres seront à 0
		//remplissage des questions posées lors du questionnaire
		for (int i=0;i<repcandidat.size();i++)
		{
				if (!listQuestionsPosees.contains(repcandidat.get(i).getQuestion()))
				{
					listQuestionsPosees.add(repcandidat.get(i).getQuestion());
				}
			
		}
		for (Question ques : listQuestionsPosees) {
			//comparaison des reponses données avec les reponses à la question, si le meme nombre de bonnes réponse est trouvé : on ajoute le poids	
			if(isBonnesReponsesQuestionDonnee(ListeReponseCandidatByQuestion(repcandidat,ques.getIdQuestion()),ques))
			{
				score += ques.getPoids();
			}
		}
        return score;
	}
	
	public boolean isBonnesReponsesQuestionDonnee( List<ReponseQuestion> reponsesQuestion, Question question)
	{
		boolean valide = true;
		if(reponsesQuestion.size()!=question.ListeBonneReponseQuestion().size())
		{
			valide=false;
		}
		
		for (ReponseQuestion reponse: reponsesQuestion) {
			if(reponse.getBonneReponse()==0)
			{
				valide = false;
			}
		}	
		return valide;
	}
	
	public List<ReponseQuestion> ListeReponseCandidatByQuestion(List<ReponseQuestion> rq, int idQuestion)
	{
		List<ReponseQuestion> listreponse=new ArrayList<ReponseQuestion>();
		
		
		for (ReponseQuestion reponsesquestions : rq) {
			if(reponsesquestions.getQuestion().getIdQuestion()==idQuestion)
			{
				listreponse.add(reponsesquestions);
		    }
		}
		
		return listreponse;
	}
	
	@SuppressWarnings("deprecation")
	public int calculScoreMaximal(int idQuestionnaire)
	{
		int score = 0;		
        Questionnaire q = new Questionnaire();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query quer = session.createSQLQuery("SELECT SUM(q.Poids) FROM question_questionnaire qq, Question q, Questionnaire ques WHERE ques.IdQuestionnaire = :idQuestionnaire AND q.idQuestion=qq.idQuestion AND ques.idQuestionnaire=qq.idQuestionnaire");
            quer.setParameter("idQuestionnaire", idQuestionnaire);
           
            //conversion en BigDecimal (retour renvoyé par le sum) puis conversion en int pour renvoi résultat
           score= ((BigDecimal) quer.list().get(0)).intValue();
           System.out.println("score maximal :  "+ score);
            
        } 
        catch (RuntimeException e) {
            e.printStackTrace();
        } 
        finally {
            session.close();
        }
        return score;
	}	
	
	public List<ReponseQuestion> getReponseQuestionCandidatByQuestionnaireId(int idQuestionnaire)
	{
		List<ReponseQuestion> listquestion = new ArrayList<ReponseQuestion>();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query quer = session.createSQLQuery("SELECT rq.* FROM reponse_question rq, reponse_candidat rc WHERE rq.idReponseQuestion= rc.idReponseQuestion AND rc.idQuestionnaire= :idQuestionnaire ORDER BY rq.idQuestion").addEntity(ReponseQuestion.class);
            quer.setParameter("idQuestionnaire", idQuestionnaire);
            System.out.println("id questionnaire : "+ idQuestionnaire);
            listquestion= (List<ReponseQuestion>) quer.list();     
        } 
        catch (RuntimeException e) {    	
            e.printStackTrace();
            return null;
        } 
        finally {
            session.close();
        }
        return listquestion;
	}
	
	//pas encore utilisée mais fonctionnelle
	//utilisée lors de la gestion des questionnairs (coté recruteur) pour récuperer un questionnaire donné
	public Questionnaire getQuestionnaireById(int idQuestionnaire)
	{
		Questionnaire q = new Questionnaire();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query quer = session.createSQLQuery("SELECT q.* FROM Questionnaire q WHERE q.idQuestionnaire= :idQuestionnaire").addEntity(Questionnaire.class);
            quer.setParameter("idQuestionnaire", idQuestionnaire);
            q= (Questionnaire) quer.list().get(0); 
        } 
        catch (RuntimeException e) {
            e.printStackTrace();
            return null;    
        } 
        finally {
            session.close();
        }
        return q;
	}
	
	public String ListeQuestionsAleatoire(int idtheme)
	{
		//variable pour choisir le nombre de questions que contiendra le questionnaire 
		// mis en brut : 15, possibilité d'évolution:  nombre de questions en parametre choisi par l'utilisateur
		int nbQuestion = 15;
		
		String res;
		res = this.ListeQuestionByTheme(idtheme);
		
		//On recherche un random ssi la recherche des questions par theme a bien retourné un résultat
		if (res=="success")
		{
			List<Question> listeRandom = new ArrayList<Question>();
			Random randomizer = new Random();
			Question random;
			int i=0;
			//si le nombre de questions demandé < nombre de questions disponibles
			if(nbQuestion>this.listeQuestions.size())
				nbQuestion=this.listeQuestions.size();
			
			while(i<nbQuestion)
			{
				random = this.listeQuestions.get(randomizer.nextInt(this.listeQuestions.size()));
				if (!listeRandom.contains(random))
				{
					listeRandom.add(random);
					i++;
				}
			}
			this.questionnaire.setListeQuestions(listeRandom);
			//mise à jour de la question courante
			majQuestionCourante();
		}
		return res;
	}
	
	@SuppressWarnings("deprecation")
	public String ListeQuestionByTheme(int idtheme){
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            
            //dans la query : obligatoire d'avoir les SELECT, FROM etc. en majuscule
            Query quer = session.createSQLQuery("SELECT e.* FROM Question e WHERE e.IdTheme= :theme").addEntity(Question.class);
            quer.setParameter("theme", idtheme);
           this.listeQuestions= quer.list();
        } 
        catch (RuntimeException e) {
            e.printStackTrace();
            return "failed";
        } 
        finally {
            session.close();
        }  
        return "success";
    }
	
	public ReponseQuestion getReponseQuestionById(String idreponse)
	{
		ReponseQuestion res = new ReponseQuestion();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query quer = session.createSQLQuery("SELECT rq.* FROM reponse_question rq WHERE rq.idReponseQuestion= :idrep").addEntity(ReponseQuestion.class);
            quer.setParameter("idrep", idreponse);
           res= (ReponseQuestion) quer.list().get(0);
        } 
        catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        } 
        finally {
            session.close();
        }
        return res;
	}
}
