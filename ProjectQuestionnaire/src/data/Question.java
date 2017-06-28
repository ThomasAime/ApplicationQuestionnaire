package data;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dao.QuestionDAO;
import beans.ReponseQuestionBean;

/*
 * version avant integration hibernate
@ManagedBean(name = "Questions")
@SessionScoped*/

@Entity
@Table(name = "question")
@SessionScoped
//@ApplicationScoped
public class Question implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8985762883582831252L;


	private int idQuestion;
	
	
	private int type;
	
	
	private String nomCreateur ;
	
	
	private String enonce;
	
	
	private int poids;
	
	
	private int theme;
	
	
	private List<ReponseQuestion> ListeReponsesQuestion;
	
	@OneToMany(mappedBy="question", fetch = FetchType.EAGER)
	public List<ReponseQuestion> getListeReponsesQuestion()
	{
		return this.ListeReponsesQuestion;
	}
	
	public void setListeReponsesQuestion(List<ReponseQuestion> list)
	{
		this.ListeReponsesQuestion=list;
	}
	
	
	
	public Question(){	}
	
	@Column(name = "EnonceQuestion")
	public String getEnonce()
	{
		return this.enonce;
	}
	
	public void setEnonce(String e)
	{
		this.enonce = e;
	}
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="IdQuestion", nullable=false)
	public int getIdQuestion()
	{
		return this.idQuestion;
	}
	
	public void setIdQuestion(int newid)
	{
		this.idQuestion=newid;
	}
	
	@Column(name = "IdTypeQuestion")
	public int getType()
	{
		return this.type;
	}
	
	public void setType(int t)
	{
		this.type=t;
	}
	
	@Column(name = "NomCreateur")
	public String getNomCreateur()
	{
		return this.nomCreateur;
	}
	
	public void setNomCreateur(String n)
	{
		this.nomCreateur=n;
	}
	@Column(name = "Poids")
	public int getPoids()
	{
		return poids;
	}
	public void setPoids(int p)
	{
		this.poids=p;
	}
	@Column(name = "IdTheme")
	public int getTheme()
	{
		return this.theme;
	}
	
	public void setTheme(int t)
	{
		this.theme=t;
	}
	/*
	 public void saveQuestion() {
	     QuestionDAO dao = new QuestionDAO();
	     dao.addQuestion(this);
	     clearAll();
	    }
	 */
	 
	 public void clearAll()
	 {
		 this.enonce="";
		 this.idQuestion=0;
		 this.nomCreateur="";
		 this.poids=0;
		 this.theme=0;
		 this.type=0;
	 }
}
