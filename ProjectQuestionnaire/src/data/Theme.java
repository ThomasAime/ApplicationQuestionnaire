package data;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "theme")
@SessionScoped
public class Theme implements Serializable{

	private static final long serialVersionUID = 8302924006482752984L;

	private int idTheme;
	
	private String nomTheme;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="IdTheme", nullable=false)
	public int getIdTheme()
	{
		return this.idTheme;
	}
	
	public void setIdTheme(int i)
	{
		this.idTheme=i;
	}
	
	@Column(name = "nomTheme")
	public String getNomTheme()
	{
		return this.nomTheme;
	}
	
	public void setNomTheme(String n)
	{
		this.nomTheme=n;
	}
}
