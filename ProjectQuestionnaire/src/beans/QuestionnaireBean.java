package beans;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import data.*;

@ManagedBean(name = "Questionnaires")
//@SessionScoped
@ApplicationScoped
public class QuestionnaireBean {

	
	private List<Question> listeQuestions;
	
	private List<Theme> listeThemes;
	
	public void setListeQuestions(List<Question> list)
	{
		this.listeQuestions=list;
	}
	public List<Question> getListeQuestions()
	{
		return this.listeQuestions;
	}
	
	public void setListeThemes(List<Theme> list)
	{
		this.listeThemes=list;
	}
	public List<Theme> getListeTheme()
	{
		return this.listeThemes;
	}
	
	
}
