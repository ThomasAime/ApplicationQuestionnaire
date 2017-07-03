package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import HibernateUtil.HibernateUtil;
import data.*;

@ManagedBean(name = "Questionnaires")
@ApplicationScoped
public class QuestionnaireBean implements Serializable {


	private static final long serialVersionUID = 7204550524050005481L;

	//questionnaire en train d'être passé par le candiadt
	private Questionnaire questionnaire = new Questionnaire();
	
	//liste des questions du questionnaire
	private List<Question> listeQuestions;
	
	//Liste du/des thèmes choisis pour le questionnaire
	private List<Theme> listeThemes;
	
	//question courante du questionnaire, pour se représenter par rapport à la liste
	private Question questionCourante;
	
	//reponse à une question de type Radio
	private ReponseQuestion reponseQuestion;
	
	//Reponse(s) à une question de type checkBox
	private  ReponseQuestion[] reponsesQuestion;	
	
	
	public QuestionnaireBean(){	}
	
	
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
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
	//tableau de réponses(pour les checkboxes)
	public ReponseQuestion[] getReponsesQuestion() {
		return reponsesQuestion;
	}

	public void setReponsesQuestion(ReponseQuestion[] repp) {
		this.reponsesQuestion = repp;
	}
	

	//question courante du questionnaire
	public Question getQuestionCourante()
	{
		return this.questionCourante;
	}
	public void setQuestionCourante(Question q)
	{
		this.questionCourante=q;
	}
	
	//mise à jour de la question en cours : question que le candidat est en train de répondre
	public void majQuestionCourante()
	{
		
		//SaveReponseCandidat();
		
		//enregistrement de la réponse donnée par le candidat
		
		System.out.println("Enregistrement reponse Candidat ");
		
		if(this.reponseQuestion!=null)
		{
			System.out.println("valeur de rep : " + reponseQuestion.getEnonceReponse()+reponseQuestion.getBonneReponse());
			this.questionnaire.AddReponseQuestionListe(this.reponseQuestion);
			this.reponseQuestion=new ReponseQuestion();
		}
		if(reponsesQuestion!=null)
		{
			for(int i = 0;i< reponsesQuestion.length;i++)
			{
				System.out.println("valeur de rep : " + reponsesQuestion[i].getEnonceReponse()+reponsesQuestion[i].getBonneReponse());
				this.questionnaire.AddReponseQuestionListe(reponsesQuestion[i]);
			}
			this.reponsesQuestion=new ReponseQuestion[10];
		}
		
		System.out.println("Enregistrement fini");
		
		if(this.questionnaire.getListeQuestions().size()-1==this.questionnaire.getListeQuestions().indexOf(questionCourante))
		{
			System.out.println("Sauvegarde questionnaire");
			saveQuestionnaire();
		}
		else
		{
			
			// verif is null de la question
			if (questionCourante != null) {
				System.out.println("passage question suivante");
				this.questionCourante = this.questionnaire.getListeQuestions().get(this.questionnaire.getListeQuestions().indexOf(questionCourante)+1);
			}
			else
			{
				System.out.println("recuperation premiere question");
				questionCourante=this.questionnaire.getListeQuestions().get(0);
				
			}
			System.out.println("fini");
		}
	}
	
	//enregistrement de la réponse du candidat pour la question
	public void SaveReponseCandidat()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
     //   ReponseCandidat r = new ReponseCandidat(this.questionnaire,this.listeQuestions.get(0).getListeReponsesQuestion().get(0));
       // r.setQuestionnaire(this.questionnaire);	
  //      session.save(r);
        	
        session.getTransaction().commit();
        session.close();
	}
	
	//Enregistrement du questionnaire dans la bd
	public void saveQuestionnaire()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(this.questionnaire);
        session.getTransaction().commit();
        session.close();
	}
	
	
	//Méthode pour récuperer(par query) la liste des questions aléatoires pour le questionnaire à faire passer
	//eventuellement pour la suite rajouter un parametre pour choisir le nombre de questions
	public String ListeQuestionsAleatoire(int idtheme)
	{
		//variable pour choisir le nombre de questions que contiendra le questionnaire
		int nbQuestion = 5;
		
		String res;
		res = this.ListeQuestionByTheme(idtheme);
		System.out.println("Resultat listeQuestionByTheme : " + res);
		
		//On recherche un random ssi la recherche des questions par theme a bien retourné un résultat
		if (res=="success")
		{
			List<Question> listeRandom = new ArrayList<Question>();
			Random randomizer = new Random();
			Question random;
			int i=0;
			while(i<nbQuestion)
			{
				random = this.listeQuestions.get(randomizer.nextInt(this.listeQuestions.size()));
				if (!listeRandom.contains(random))
				{
					listeRandom.add(random);
					i++;
					System.out.println("Valeur de i : "+i);
				}
				
				System.out.println("passe par ici");
			}
			System.out.println("passe par la");
		
				this.questionnaire.setListeQuestions(listeRandom);
				System.out.println("passe par la aussi");
		}
		
				majQuestionCourante();
		
		return res;
	}
	
	
	//recupération (par query) de la liste de toutes les questions avec le même theme
	//suppressWarning obligatoire 
	@SuppressWarnings("deprecation")
	public String ListeQuestionByTheme(int idtheme){
		
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            trns = session.beginTransaction();
            //Query quer= session.createQuery("select nomCreateur from Question");
            Query quer = session.createSQLQuery("SELECT e.* FROM Question e WHERE e.IdTheme= :theme").addEntity(Question.class);
            quer.setParameter("theme", idtheme);
            //Modification de la liste des questions du questionnaire 
           // this.ques.setListeQuestions(quer.list());
            System.out.println("id Theme : "+ idtheme);
           this.listeQuestions= quer.list();
           
            
        } catch (RuntimeException e) {
        	
            e.printStackTrace();
            return "failed";
            
        } finally {
            session.close();
        }
        
        return "success";
    }
	
	/*
	private List<ReponseCandidat> listeReponsesCandidat;
	
	@OneToMany(mappedBy="questionnaire", fetch = FetchType.EAGER)
	public List<ReponseCandidat> getListeReponsesCandidat() {
		return listeReponsesCandidat;
	}
	
	public void setListeReponsesCandidat(List<ReponseCandidat> listeReponsesCandidat) {
		this.listeReponsesCandidat = listeReponsesCandidat;
	}
	
	public void addReponseCandidat(ReponseCandidat r)
	{
		this.listeReponsesCandidat.add(r);
	}
	*/
	
	
	/* private String message = new String();

	 private String[] messagess;
	 

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String[] getMessagess() {
			return messagess;
		}

		public void setMessagess(String[] messagess) {
			this.messagess = messagess;
		}

		*/
	
	


	
	
	
}
