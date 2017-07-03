package data;

import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proprietaire")
@SessionScoped
public class Proprietaire {

	private int idProprietaire;
	private String ville;
	private String nomProprietaire;
	private List<Questionnaire> listeQuestionnaires;
	
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="IdProprietaire", nullable=false)
	public int getIdProprietaire()
	{
		return this.idProprietaire;
	}
	
	public void setIdProprietaire(int i)
	{
		this.idProprietaire=i;
	}
	
	@OneToMany
	public List<Questionnaire> getListeQuestionnaires()
	{
		return this.listeQuestionnaires;
	}
	
	public void setListeQuestionnaires(List<Questionnaire> list)
	{
		this.listeQuestionnaires=list;
	}
	
	@Column (name="VilleProprietaire")
	public String getVille()
	{
		return this.ville;
	}
	
	public void setVille(String v)
	{
		this.ville=v;
	}
	
	
	@Column (name="NomProprietaire")
	public String getNomProprietaire()
	{
		return this.nomProprietaire;
	}
	
	public void setNomProprietaire(String n)
	{
		this.nomProprietaire=n;
	}
	
}
