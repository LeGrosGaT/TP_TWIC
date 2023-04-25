package com.dao;

import java.util.List;

import com.dto.Ville;

public interface VilleDAO {
	public List<Ville> findAllVilles();

	public List<Ville> findVille(String codeCommune);
	
	public boolean updateVille(Ville ville);
	
	public boolean createVille(Ville ville);
	
	public boolean deleteVille(String codeCommune);	
}
