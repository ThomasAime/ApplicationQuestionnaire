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
	private ReponseQuestion reponseQuestion= new ReponseQuestion();
	
	//Reponse(s) à une question de type checkBox
	private  List<ReponseQuestion> reponsesQuestion= new ArrayList<ReponseQuestion>();	
	
	
	public QuestionnaireBean(){	
		
	}
	
	
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
	public List<ReponseQuestion> getReponsesQuestion() {
		return reponsesQuestion;
	}

	public void setReponsesQuestion(List<ReponseQuestion> repp) {
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
		
		//enregistrement de la réponse donnée par le candidat soit par le radio boutton(1 reponse) ou par le check( de 1 a X réponses)
		
		System.out.println("Enregistrement reponse Candidat  : ");
		System.out.println("idReponseRadio : " + this.idReponseRadio);
		
		//if(this.reponseQuestion.getEnonceReponse()!=null )
		//if(this.reponseQuestion.Valid())
		if(!this.idReponseRadio.isEmpty() && this.idReponseRadio!=null)
		{
			System.out.println("AddReponseQuestionListe");
			this.questionnaire.AddReponseQuestionListeReponseCandidat(this.getReponseQuestionById(idReponseRadio));
			this.idReponseRadio=new String();
			//this.reponseQuestion=new ReponseQuestion();
		}
		
		//if(this.reponsesQuestion[0].Valid())
		System.out.println("taille tableau : " + this.idReponseCheck.size());
		//if (this.messagess!=null && this.messagess.length>0&& this.messagess[0]!=null)
		//if (this.reponsesQuestion.size()>0)
		if(this.idReponseCheck!=null&& this.idReponseCheck.size()>0)
		{
			for(int i = 0;i < idReponseCheck.size();i++)
			{
				// test pour verifier si passe bien par ici
				System.out.println("AddReponseQuestionListe a partir de la check" + i);
				this.questionnaire.AddReponseQuestionListeReponseCandidat(this.getReponseQuestionById(idReponseCheck.get(i)));
				
			}
			//this.messagess= new String[10];
			this.idReponseCheck=new ArrayList<String>();
			//this.reponsesQuestion=new ArrayList<ReponseQuestion>();
		}
		
		
		if(this.questionnaire.getListeQuestions().size()-1==this.questionnaire.getListeQuestions().indexOf(questionCourante))
		{
			
			System.out.println("test avant sauvegarde questionnaire : ");
			
			for(int i = 0;i< this.questionnaire.getListeReponseCandidat().size();i++)
			{
				System.out.println(" i : "+i + "  , id :  "+ this.questionnaire.getListeReponseCandidat().get(i).getIdReponse() + " , enonce : " + this.questionnaire.getListeReponseCandidat().get(i).getEnonceReponse());
			}
			
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
        
        //enregistrement du questionnaire dans la base
        //session.save(this.questionnaire);
        
        // test avec persist():
        // session.persist(this.questionnaire);
        
        //test avec saveorUpdate
        
        session.saveOrUpdate(this.questionnaire);
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
	private String idReponseRadio= new String();
	private List<String> idReponseCheck = new ArrayList<String>();
	
	public ReponseQuestion getReponseQuestionById(String idreponse)
	{
		ReponseQuestion res = new ReponseQuestion();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            trns = session.beginTransaction();
            //Query quer= session.createQuery("select nomCreateur from Question");
            Query quer = session.createSQLQuery("SELECT rq.* FROM reponse_question rq WHERE rq.idReponseQuestion= :idrep").addEntity(ReponseQuestion.class);
            quer.setParameter("idrep", idreponse);
            //Modification de la liste des questions du questionnaire 
           // this.ques.setListeQuestions(quer.list());
            System.out.println("id reponse : "+ idreponse);
           res= (ReponseQuestion) quer.list().get(0);
           
            
        } catch (RuntimeException e) {
        	
            e.printStackTrace();
            return null;
            
        } finally {
            session.close();
        }
        
        return res;
	}
	
	/*
	 private String message = new String();

	 private List<String> messages;
	 
	 private String[] messagess = new String[10];
	 

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


		public List<String> getMessages() {
			return messages;
		}


		public void setMessages(List<String> messages) {
			this.messages = messages;
		}


*/
	
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

		
	
	


	
	
	
}
