package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class TestController {
	
	@Autowired
	VilleBLO villeBLOService;
	
	@RequestMapping(value="/tests", method=RequestMethod.GET)
	@ResponseBody
	public List<Ville> get(@RequestParam(required = false, value="codePostal") String codePostal) {		
		return villeBLOService.getInfoVilles(codePostal);
	}
}
