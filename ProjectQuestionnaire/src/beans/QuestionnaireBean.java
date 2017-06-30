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
//@SessionScoped
@ApplicationScoped
public class QuestionnaireBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7204550524050005481L;

	private Questionnaire questionnaire = new Questionnaire();
	
	//contient la liste de toutes les questions pour le theme choisi
	private List<Question> listeQuestions;
	
	private List<Theme> listeThemes;
	
	private Question questionCourante;
	
	//-----------------------------------------------
	private ReponseCandidat repCand;
	
	public ReponseCandidat getRepCand() {
		return repCand;
	}

	public void setRepCand(ReponseCandidat repCand) {
		this.repCand = repCand;
	}
	
	//-----------------------------------------------	
	
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
	
	//temporaire, pour effectuer tests
	 private String message;
		
	 public String getMessage()
	 {
		 return this.message;
	 }
	 
	 public void SetMessage(String m)
	 {
		 this.message=m;
	 }
	 
	 private String[] messagess;
	 
	public String[] getMessagess()
	{
		return this.messagess;
	}
	
	public void setMessagess(String[] t)
	{
		this.messagess=t;
	}
	
	
	public QuestionnaireBean(){
		
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
		SaveReponseCandidat();
		// verif is null de la question
		if (questionCourante != null) {
			this.questionCourante = this.questionnaire.getListeQuestions().get(this.questionnaire.getListeQuestions().indexOf(questionCourante)+1);
		}
		else
		{
			questionCourante=this.questionnaire.getListeQuestions().get(0);
			
		}
		System.out.println("enonce : "+questionCourante.getEnonce());
	}
	
	public void SaveReponseCandidat()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        ReponseCandidat r = new ReponseCandidat();
        	session.save(r);
        	r.setQuestionnaire(this.questionnaire);
        session.getTransaction().commit();
        session.close();
	}
	
	//eventuellement pour la suite rajouter un parametre pour choisir le nombre de questions
	public String ListeQuestionsAleatoire(int idtheme)
	{
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
	
	
	
	
	public void setListeQuestions(List<Question> list)
	{
		this.listeQuestions=list;
	}
	public List<Question> getListeQuestions()
	{
		return this.listeQuestions;
	}
	
	public void setListeThemes(List<Theme> list)
	{
		this.listeThemes=list;
	}
	public List<Theme> getListeThemes()
	{
		return this.listeThemes;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	

	
	
	
}
