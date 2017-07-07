package data;

import java.io.Serializable;

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
public class ReponseQuestion implements Serializable{

	private int idReponse;
	
	private String enonceReponse;
	
	private int bonneReponse;
	
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
	public int getBonneReponse()
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
	
	public void setBonneReponse(int b)
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
}
