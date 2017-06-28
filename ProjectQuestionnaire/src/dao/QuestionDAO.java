package dao;

import data.Question;
import HibernateUtil.HibernateUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
@ManagedBean(name = "Questions")
@SessionScoped*/
public interface QuestionDAO  {

	
	
	public List<Question> listQuestions();
	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;
	private Question question=new Question();
	
	public void setQuestion(Question q)
	{
		this.question=q;
	}
	
	public Question getQuestion()
	{
		return this.question;
	}
	public QuestionDAO(){}
	
	public void addQuestion(Question quest)
	{
		 Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            session.save(quest);
	            session.getTransaction().commit();
	        } catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
	}
	
	public void deleteQuestion(int questId) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Question qu = (Question) session.load(Question.class, new Integer(questId));
            session.delete(qu);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
 
    public void updateQuestion(Question quest) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(quest);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
 
    public List<Question> getAllCustomers() {
        List<Question> listeQuestions = new ArrayList<Question>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            listeQuestions = session.createQuery("select concat(IdQuestion, ' ', EnonceQuestion) as name from Question").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return listeQuestions;
    }
 
    public List<Question> getQuestionById(String questId) {
        System.out.println(questId);
//        Customer cust = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Question where concat(IdQuestion, ' ', EnonceQuestion) = :id";
            Query query = session.createQuery(queryString);
            query.setString("id", questId);
            //cust = (Customer) query.uniqueResult();
            List<Question> list = query.list();
            if (list.size() > 0) {
                return list;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }
	
	*/
}
