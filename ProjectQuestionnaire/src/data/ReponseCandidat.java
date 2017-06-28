package data;

import java.sql.Time;

public class ReponseCandidat {

	
	
	private int idReponseCandidat;
	
	private Time dureePasseeQuestion;
	
	public void setIdReponseCandidat( int id)
	{
		this.idReponseCandidat=id;
	}
	
	public int getIdReponseCandidat()
	{
		return this.idReponseCandidat;
	}
	
	public Time getDureePasseeQuestion()
	{
		return this.dureePasseeQuestion;
	}
	
	public void setDureePasseeQuestion(Time t)
	{
		this.dureePasseeQuestion=t;
	}
	
}
