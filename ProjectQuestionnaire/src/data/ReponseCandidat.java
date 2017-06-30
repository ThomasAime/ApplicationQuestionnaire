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

@Entity
@Table(name = "reponse_candidat")
@SessionScoped
public class ReponseCandidat {

	private int idReponseCandidat;
	
	private Time dureePasseeQuestion;
	
	
	
	
	
	public ReponseCandidat(){}
	
	public ReponseCandidat(int id)
	{
		
	}
	
	private ReponseQuestion reponseQuestion;
	
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
	
	
	private Questionnaire questionnaire;
	
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
	
}
