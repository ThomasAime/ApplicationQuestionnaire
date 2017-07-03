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


@Entity
@Table(name = "questionnaire")
@SessionScoped
public class Questionnaire {

	private int idQuestionnaire;
	
	private Date datePassage;
	
	private String nom;	
	
	private String nomCandidat;
	
	private Timestamp dureePrevue;
	
	private Timestamp dureeRealise;
	
	private List<Question> listeQuestions = new ArrayList<Question>();

	private List<ReponseQuestion> listeReponseCandidat = new ArrayList<ReponseQuestion>();
	
	
	public Questionnaire(){	}
	
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
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "reponse_candidat", /*catalog = "mkyongdb",*/ joinColumns = 
		{@JoinColumn(name = "IdQuestionnaire", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "IdReponseQuestion",
					nullable = false, updatable = false) })
	public List<ReponseQuestion> getListeReponseCandidat()
	{
		return this.listeReponseCandidat;
	}
	
	public void setListeReponseCandidat(List<ReponseQuestion> q)
	{
		this.listeReponseCandidat=q;
	}
	
	public void AddReponseQuestionListe(ReponseQuestion rq)
	{
		this.listeReponseCandidat.add(rq);
	}
	
	
	//private String note;
	
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
	
	
}
