package data;

public class Proprietaire {

	private int idProprietaire;
	private String ville;
	private String nom;
	
	public int getIdProprietaire()
	{
		return this.idProprietaire;
	}
	
	public String getVille()
	{
		return this.ville;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public void setIdProprietaire(int i)
	{
		this.idProprietaire=i;
	}
	
	public void setVille(String v)
	{
		this.ville=v;
	}
	
	public void setNom(String n)
	{
		this.nom=n;
	}
	
}
