package beans;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import HibernateUtil.HibernateUtil;
import data.*;

@ManagedBean(name = "Themes")
//@SessionScoped
@ApplicationScoped
public class ThemeBean {

	private List<Theme> listeThemes = new ArrayList<Theme>();
	
	
	public List<Theme> getListeThemes()
	{
		return this.listeThemes;
	}
	
	public void setListeThemes(List<Theme> list)
	{
		this.listeThemes=list;
	}
	
	/*@PostConstruct
    public void init() {
	 
		this.getAllThemes();
	 
	 //getAllQuestions2();
 }*/
	
	public ThemeBean()
	{
		this.getAllThemes();
	}
	
	//Récuperation de la liste des themes 
	@SuppressWarnings("deprecation")
	public String getAllThemes() {
		List<String> list = new ArrayList<String>();
	    Transaction trns = null;
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    try {
            trns = session.beginTransaction();
            
            Query quer = session.createSQLQuery("SELECT t.* FROM Theme t").addEntity(Theme.class);
            
            
            this.listeThemes= quer.list();
          //  list = quer.list();
         //   this.listeThemes=list;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
           // session.flush();
        	//session.getTransaction().commit();
            session.close();
        }
        
      //  this.messages=list;
        return "success";
   //     return list;
	}
	
}
