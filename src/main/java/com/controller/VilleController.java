package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleController {
	
	@Autowired
	VilleBLO villeBLOService;
	
	@RequestMapping(value="/ville/chercher", method=RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:8080")
	@ResponseBody
	public List<Ville> get(@RequestParam(required = false, value="code") String code) {
		
		return villeBLOService.getInfoVilles(code);
	}
	
	@RequestMapping(value="/ville/ajoute", method=RequestMethod.POST)
	@ResponseBody
	public Ville create(@RequestParam(required = true, value="code") String code,
						@RequestParam(required = true, value="nom") String nom,
						@RequestParam(required = true, value="postal") String postal,
						@RequestParam(required = true, value="libelle") String libelle,
						@RequestParam(required = true, value="ligne") String ligne,
						@RequestParam(required = true, value="latitude") String latitude,
						@RequestParam(required = true, value="longitude") String longitude) {
		
	    return villeBLOService.createVille(code, nom, postal, libelle, ligne, latitude, longitude);
	}
	
	@RequestMapping(value="/ville/modifier", method=RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:8080")
	@ResponseBody
	public Ville modify(@RequestParam(required = true, value="code") String code,
						@RequestParam(required = false, value="nom") String nom,
						@RequestParam(required = false, value="postal") String postal,
						@RequestParam(required = false, value="libelle") String libelle,
						@RequestParam(required = false, value="ligne") String ligne,
						@RequestParam(required = false, value="latitude") String latitude,
						@RequestParam(required = false, value="longitude") String longitude) {
		
	    return villeBLOService.modifyVille(code, nom, postal, libelle, ligne, latitude, longitude);
	}
	
	@RequestMapping(value="/ville/retire", method=RequestMethod.DELETE)
	@ResponseBody
	public Ville delete(@RequestParam(required = true, value="code") String code) {
		
	    return villeBLOService.deleteVille(code);
	}
	
	@RequestMapping(value="/ville/distance", method=RequestMethod.GET)
	@ResponseBody
	public double distance(@RequestParam(required = true, value="ville1") String ville1,
						@RequestParam(required = true, value="ville2") String ville2) {
		
		return villeBLOService.distanceVille(ville1, ville2);
	}
}
