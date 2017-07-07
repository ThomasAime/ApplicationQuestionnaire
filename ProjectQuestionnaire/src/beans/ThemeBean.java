package beans;
import java.io.Serializable;
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
@ApplicationScoped
public class ThemeBean{

	private List<Theme> listeThemes = new ArrayList<Theme>();
	
	public List<Theme> getListeThemes()
	{
		return this.listeThemes;
	}
	
	public void setListeThemes(List<Theme> list)
	{
		this.listeThemes=list;
	}
	
	public ThemeBean()
	{
		this.getAllThemes();
	}
	
	@SuppressWarnings("deprecation")
	public String getAllThemes() {
	    Transaction trns = null;
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
            trns = session.beginTransaction();        
            Query quer = session.createSQLQuery("SELECT t.* FROM Theme t").addEntity(Theme.class);
            this.listeThemes= quer.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            // a mettre en place : au cas où le chargement a échoué, renvoi vers une page d'erreur
            return "failed";
        } finally {
            session.close();
        }
        return "success";
	}
	
}
