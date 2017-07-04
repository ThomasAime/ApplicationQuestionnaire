package data;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reponse_question")
//@SessionScoped
@ApplicationScoped
public class ReponseQuestion {

	private int idReponse;
	
	private String enonceReponse;
	
	private Boolean bonneReponse;
	
	private Question question;
		
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="IdReponseQuestion", nullable=false)
	public int getIdReponse()
	{
		return this.idReponse;
	}
	
	@Column(name = "EnonceReponse")
	public String getEnonceReponse()
	{
		return this.enonceReponse;
	}
	
	@Column(name = "BonneReponse")
	public Boolean getBonneReponse()
	{
		return this.bonneReponse;
	}
	
	public void setIdReponse(int i)
	{
		this.idReponse=i;
	}
	
	public void setEnonceReponse(String e)
	{
		this.enonceReponse=e;
	}
	
	public void setBonneReponse(Boolean b)
	{
		this.bonneReponse=b;
	}
	
	@ManyToOne
	@JoinColumn(name="IdQuestion", nullable=false)
	public Question getQuestion()
	{
		return this.question;
	}
	
	public void setQuestion(Question q)
	{
		this.question=q;
	}
	
	//surcharge de la méthode toString pour pouvoir afficher les enoncés des reponses de question
	/*public String toString()
	{
		return this.enonceReponse;
	}*/
	
	public boolean Valid()
	{
		return this.idReponse !=0 && this.enonceReponse !=null && this.bonneReponse != null && this.question !=null;
	}
	
	
	/*
	 * Solution utilisée lorsque association ManyToOne avec ReponseCandidat mis en place
	 * 
	private List<ReponseCandidat> listeReponsesCandidat;
	
	@OneToMany(mappedBy="reponseQuestion", fetch = FetchType.EAGER)
	public List<ReponseCandidat> getListeReponsesCandidat()
	{
		return this.listeReponsesCandidat;
	}
	
	public void setListeReponsesCandidat(List<ReponseCandidat> list)
	{
		this.listeReponsesCandidat=list;
	}
	*/
	
}
