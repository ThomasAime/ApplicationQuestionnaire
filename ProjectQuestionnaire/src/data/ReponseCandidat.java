package data;

import java.sql.Time;

import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
@Entity
@Table(name = "reponse_candidat")
@SessionScoped*/
public class ReponseCandidat {

	/*
	 * Classe plus utilisée pour le moment : premiere solution choisie : utiliser deux ManyToOne pour representer les reponses_candidat, changé pour utiliser un manyToMany 
	 * faisant que la classe ReponseCandidat n'est plus utile.
	 */
	
	private int idReponseCandidat;
	
	private Time dureePasseeQuestion;
	
	private ReponseQuestion reponseQuestion;
	
	private Questionnaire questionnaire;
	
	public ReponseCandidat(){}
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="IdReponseCandidat", nullable=false)
	public int getIdReponseCandidat()
	{
		return this.idReponseCandidat;
	}
	
	public void setIdReponseCandidat( int id)
	{
		this.idReponseCandidat=id;
	}
	
	@Column (name="DureePasseeQuestion"	)
	public Time getDureePasseeQuestion()
	{
		return this.dureePasseeQuestion;
	}
	
	public void setDureePasseeQuestion(Time t)
	{
		this.dureePasseeQuestion=t;
	}
	
	@ManyToOne
	@JoinColumn(name="idReponseQuestion", nullable=false)
	public ReponseQuestion getReponseQuestion()
	{
		return this.reponseQuestion;
	}
	
	public void setReponseQuestion(ReponseQuestion q)
	{
		this.reponseQuestion=q;
	}
	
	@ManyToOne
	@JoinColumn(name="IdQuestionnaire", nullable=false)
	public Questionnaire getQuestionnaire()
	{
		return this.questionnaire;
	}
	
	public void setQuestionnaire(Questionnaire q)
	{
		this.questionnaire=q;
	}
	
	
}
