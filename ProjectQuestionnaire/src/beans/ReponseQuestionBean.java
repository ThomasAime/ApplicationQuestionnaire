package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import HibernateUtil.HibernateUtil;
import data.Question;
import data.ReponseQuestion;

@ManagedBean(name = "ReponseQuestions")
//@SessionScoped
@ApplicationScoped
public class ReponseQuestionBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3209009116512200898L;
	
	private List<ReponseQuestion> listeReponse= new ArrayList<ReponseQuestion>();
	
	
	
	
	public List<ReponseQuestion> getReponse()
	{
		return this.listeReponse;
	}
	
	public void setReponse(List<ReponseQuestion> r)
	{
		this.listeReponse=r;
	}
	
	
	public ReponseQuestionBean()
	{
		
	}
	
	@SuppressWarnings("deprecation")
	public String getReponseByQuestionId(int idquestion)
	{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //Query quer= session.createQuery("select nomCreateur from Question");
            Query quer = session.createSQLQuery("SELECT r.* FROM reponse_question r WHERE r.IdQuestion= :question").addEntity(ReponseQuestion.class);
            quer.setParameter("question", idquestion);
            
            this.listeReponse= quer.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return "success";
	}
	
	
	
}
