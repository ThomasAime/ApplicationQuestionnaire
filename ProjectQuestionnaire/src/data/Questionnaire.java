package data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import data.Question;

@ManagedBean(name = "Questionnaire")
@SessionScoped
public class Questionnaire {

	private int idQuestionnaire;
	
	
	//pas sur
	//private String note;
	
	private Date datePassage;
	
	private String nom;	
	
	private String nomCandidat;
	
	private Timestamp dureePrevue;
	
	private Timestamp dureeRealise;
		
	
	private List<Question> listeQuestions = new ArrayList<Question>();
	
	
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="IdQuestionnaire", nullable=false)
	public int getId()
	{
		return this.idQuestionnaire;
	}
	/*
	public String getNote()
	{
		return this.note;
	}*/
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "stock_category", catalog = "mkyongdb", joinColumns = 
		{@JoinColumn(name = "STOCK_ID", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID",
					nullable = false, updatable = false) })
	public List<Question> getListeQuestions()
	{
		return this.listeQuestions;
	}
	
	
	@Column(name = "DatePassage")
	public Date getDate()
	{
		return this.datePassage;
	}
	
	@Column(name = "NomQuestionnaire")
	public String getNom()
	{
		return this.nom;
	}
	
	@Column(name = "NomCandidat")
	public String getNomUser()
	{
		return this.nomCandidat;
	}
	
	@Column(name = "DureePrevue")
	public Timestamp getDuree_Prevue()
	{
		return this.dureePrevue;
	}
	
	@Column(name = "DureeRealisation")
	public Timestamp getDureeRealise()
	{
		return this.dureeRealise;
	}

	
	
	
	public Questionnaire()
	{
		
	}
	
	/*@PostConstruct
	public void genererQuestionnaire()
	{
		for(int i =1; i<=15;i++)
		{
			Question q = new Question();
		//	q.setId(String.valueOf(i));
		//	q.setReponse("quelle est la valeur de "+i);
			this.questions.add(q);	
		}
	}
	*/
	
}
