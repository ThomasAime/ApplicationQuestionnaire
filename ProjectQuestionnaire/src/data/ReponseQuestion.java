package data;

import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reponse_question")
@SessionScoped
public class ReponseQuestion {

	private int idReponse;
	
	private String enonceReponse;
	
	private Boolean bonneReponse;
	
	private Question question;
	
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
	
}
