package data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import data.Question;

/*
@ManagedBean(name = "Questionnaire")
@SessionScoped*/
@Entity
@Table(name = "questionnaire")
@SessionScoped
public class Questionnaire {

	private int idQuestionnaire;
	
	
	//private String note;
	
	private Date datePassage;
	
	private String nom;	
	
	private String nomCandidat;
	
	private Timestamp dureePrevue;
	
	private Timestamp dureeRealise;
	
	private List<Question> listeQuestions = new ArrayList<Question>();

	/*private Proprietaire proprietaire;
	
	@ManyToOne
	@JoinColumn(name="IdProprietaire", nullable=false)
	public Proprietaire getProprietaire()
	{
		return this.proprietaire;
	}
	
	public void setProprietaire(Proprietaire p)
	{
		this.proprietaire=p;
	}*/
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="IdQuestionnaire", nullable=false)
	public int getId()
	{
		return this.idQuestionnaire;
	}
	
	public void setId(int i)
	{
		this.idQuestionnaire=i;
	}
	/*
	public String getNote()
	{
		return this.note;
	}*/
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "question_questionnaire", /*catalog = "mkyongdb",*/ joinColumns = 
		{@JoinColumn(name = "IdQuestionnaire", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "IdQuestion",
					nullable = false, updatable = false) })
	public List<Question> getListeQuestions()
	{
		return this.listeQuestions;
	}
	
	public void setListeQuestions(List<Question> q)
	{
		this.listeQuestions=q;
	}
	
	
	@Column(name = "DatePassage")
	public Date getDatePassage()
	{
		return this.datePassage;
	}
	
	public void setDatePassage(Date d)
	{
		this.datePassage=d;
	}
	
	@Column(name = "NomQuestionnaire")
	public String getNom()
	{
		return this.nom;
	}
	
	public void setNom(String n)
	{
		this.nom=n;
	}
	
	@Column(name = "NomCandidat")
	public String getNomCandidat()
	{
		return this.nomCandidat;
	}
	
	public void setNomCandidat(String n)
	{
		this.nomCandidat=n;
	}
	
	@Column(name = "DureePrevue")
	public Timestamp getDuree_Prevue()
	{
		return this.dureePrevue;
	}
	
	public void setDuree_Prevue(Timestamp dp)
	{
		this.dureePrevue=dp;
	}
	
	@Column(name = "DureeRealisation")
	public Timestamp getDureeRealise()
	{
		return this.dureeRealise;
	}

	
	public void setDureeRealise(Timestamp dr)
	{
		this.dureeRealise=dr;
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
