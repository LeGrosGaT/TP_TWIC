package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	public ArrayList<Ville> findAllVilles();

	public ArrayList<Ville> findVille(String codeCommune);
	
	public boolean updateVille(Ville ville);
	
	public boolean createVille(Ville ville);
	
	public boolean deleteVille(String codeCommune);	
}
