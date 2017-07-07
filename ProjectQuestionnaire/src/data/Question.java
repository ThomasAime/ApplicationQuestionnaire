package data;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "question")
@SessionScoped
public class Question implements Serializable{
	
	private int idQuestion;	
	
	private int type;	
	
	private String nomCreateur ;	
	
	private String enonce;	
	
	private int poids;	
	
	private int theme;
	
	private List<ReponseQuestion> ListeReponsesQuestion;
	
	public Question(){	}
	
	@Column(name = "EnonceQuestion")
	public String getEnonce()
	{
		return this.enonce;
	}
	
	public void setEnonce(String e)
	{
		this.enonce = e;
	}
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="IdQuestion", nullable=false)
	public int getIdQuestion()
	{
		return this.idQuestion;
	}
	
	public void setIdQuestion(int newid)
	{
		this.idQuestion=newid;
	}
	
	@Column(name = "IdTypeQuestion")
	public int getType()
	{
		return this.type;
	}
	
	public void setType(int t)
	{
		this.type=t;
	}
	
	@Column(name = "NomCreateur")
	public String getNomCreateur()
	{
		return this.nomCreateur;
	}
	
	public void setNomCreateur(String n)
	{
		this.nomCreateur=n;
	}
	
	@Column(name = "Poids")
	public int getPoids()
	{
		return poids;
	}
	public void setPoids(int p)
	{
		this.poids=p;
	}
	
	@Column(name = "IdTheme")
	public int getTheme()
	{
		return this.theme;
	}
	
	public void setTheme(int t)
	{
		this.theme=t;
	}
	
	@OneToMany(mappedBy="question", fetch = FetchType.EAGER)
	public List<ReponseQuestion> getListeReponsesQuestion()
	{
		return this.ListeReponsesQuestion;
	}
	
	public void setListeReponsesQuestion(List<ReponseQuestion> list)
	{
		this.ListeReponsesQuestion=list;
	}
	
	//pas de get car sinon interprété comme une proprieté reliée à la table question
	//récuperation de la liste de bonnes réponses de la question
	public List<ReponseQuestion> ListeBonneReponseQuestion()
	{
		List<ReponseQuestion> listbonnerep = new ArrayList<ReponseQuestion>();
		
		for(int i = 0;i<this.ListeReponsesQuestion.size();i++)
		{
			if(this.ListeReponsesQuestion.get(i).getBonneReponse()==1)
			{
				listbonnerep.add(this.ListeReponsesQuestion.get(i));
			}
		}
		return listbonnerep;

	}
}
