package com.hospital.spring.mvc.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.spring.mvc.data.Data;
import com.hospital.spring.mvc.data.Disease;
import com.hospital.spring.mvc.data.File;
import com.hospital.spring.mvc.data.FileName;
import com.hospital.spring.mvc.data.Symptom;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private Data data;
	
	@RequestMapping("/")
	public String prepare(Model model) {
		
		if (this.data == null) {
			model.addAttribute("isActive", false);
			return "home";
		}
		
		Disease[] topThreeDiseases = this.data.getTopThreeDiseases();
		Symptom[] topThreeSymptoms = this.data.getTopThreeSymptoms();
		int uniqueSymptoms = this.data.getUniqueSymptomsCount();
		
		model.addAttribute("isActive", true);
		model.addAttribute("topDiseases", topThreeDiseases);
		model.addAttribute("diseaseInfo", getDiseasesCount(topThreeDiseases));
		model.addAttribute("topSymptoms", topThreeSymptoms);
		model.addAttribute("symptomInfo", getDiseasesCount(topThreeSymptoms));
		model.addAttribute("uniqueSymptoms", uniqueSymptoms);
		
		return "home";
	}	
	
	private Object getDiseasesCount(Symptom[] topThreeSymptoms) {
		Integer[] info = new Integer[topThreeSymptoms.length];
		for (int i = 0; i < topThreeSymptoms.length; i++) {
			info[i] = topThreeSymptoms[i].diseasesCount();
		}
		return info;
	}

	private Integer[] getDiseasesCount(Disease[] topThreeDiseases) {
		Integer[] info = new Integer[topThreeDiseases.length];
		for (int i = 0; i < topThreeDiseases.length; i++) {
			info[i] = topThreeDiseases[i].countSymptoms();
		}
		return info;
	}
	
	@RequestMapping(value = "/data",method=RequestMethod.POST) 
	public String setFile(@RequestParam("file") String file) {
		if (file.endsWith(".csv") || file.endsWith(".CSV")) {
			File.setFileName(file);
			this.data = new Data();
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}
}
