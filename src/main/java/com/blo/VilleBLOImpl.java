package com.blo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Coordonnee;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO; 

	public ArrayList<Ville> getInfoVilles(String code) {
		List<Ville> listVille;

		if(code == null) {
			listVille = villeDAO.findAllVilles();
		} else {
			listVille = villeDAO.findVille(code);
		}

		return (ArrayList<Ville>) listVille;
	}	

	public Ville createVille( 	 String codeCommune,
			String nomCommune,
			String codePostal,
			String libelleAcheminement,
			String ligne,
			String latitude,
			String longitude) {
		if(getInfoVilles(codeCommune).isEmpty()) {
			Ville ville = new Ville();
			ville.setCodeCommune(codeCommune);
			ville.setNomCommune(nomCommune);
			ville.setCodePostal(codePostal);
			ville.setLibelleAcheminement(libelleAcheminement);
			ville.setLigne(ligne);
			ville.setCoordonnee(new Coordonnee(Integer.parseInt(latitude), Integer.parseInt(longitude)));

			villeDAO.createVille(ville);

			return ville;
		}
		return null;
	}	

	public Ville modifyVille( 	String codeCommune,
								String nomCommune,
								String codePostal,
								String libelleAcheminement,
								String ligne,
								String latitude,
								String longitude) {
			
		if(getInfoVilles(codeCommune).size() == 1) {
			Ville ville = getInfoVilles(codeCommune).get(0);
			if(nomCommune != null) {
				ville.setNomCommune(nomCommune);
			}
			if(codePostal != null) {
				ville.setCodePostal(codePostal);
			}
			if(libelleAcheminement != null) {
				ville.setLibelleAcheminement(libelleAcheminement);
			}
			if(ligne != null) {
				ville.setLigne(ligne);
			}
			if(latitude != null && longitude != null) {
				ville.setCoordonnee(new Coordonnee(Double.parseDouble(latitude), Double.parseDouble(longitude)));
			}
			else if (latitude != null) {
				ville.setCoordonnee(new Coordonnee(Double.parseDouble(latitude), ville.getCoordonnee().getLongitude()));
			}
			else if (longitude != null) {
				ville.setCoordonnee(new Coordonnee(ville.getCoordonnee().getLatitude(), Double.parseDouble(longitude)));
			}
			
			villeDAO.updateVille(ville);

			return ville;
		}
		return null;
	}

	@Override
	public Ville deleteVille(String code) {
		if(getInfoVilles(code).size() == 1) {
			Ville ville = getInfoVilles(code).get(0);
			villeDAO.deleteVille(code);
			return ville;
		}
		return null;
	}	
	
	@Override
	public double distanceVille(String code1, String code2) {
		Ville ville1 = this.getInfoVilles(code1).get(0);		
		Ville ville2 = this.getInfoVilles(code2).get(0);
		return ville1.getCoordonnee().calculDistance(ville2.getCoordonnee());
	}
}
