package com.hospital.spring.mvc.patient;

import java.util.ArrayList;

import javax.validation.Valid;

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
import com.hospital.spring.mvc.data.Symptom;

@Controller
@RequestMapping("/patient")
@SessionAttributes("patientBean")
public class PatientController {

	@ModelAttribute("patientBean")
	public PatientBean createFormBean() {
		return new PatientBean();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView("form");
		ArrayList<Symptom> symptoms;
		if (File.getFileName().equals("")) {
			mav.addObject("isActive", false);
			return mav;
		} else {
			mav.addObject("isActive", true);
			Data data = new Data();
			symptoms = data.getUniqueSymptoms();
			mav.addObject("symptomList", symptoms);
			return mav;
		}
	}	
	
	@RequestMapping(value="/success", method=RequestMethod.POST)
	public String result(@RequestParam(value="symptomNames", required=false) String[] symptoms,
			@Valid PatientBean patient, BindingResult result, Model m) {
		if (result.hasErrors()) {
			Data data = new Data();
			ArrayList<Symptom> allSymptoms = data.getUniqueSymptoms();
			m.addAttribute("isActive", true);
			m.addAttribute("hasFormError", true);
			m.addAttribute("symptomList", allSymptoms);
			return "form";
		}
		ArrayList<Disease> userDiseases = DiagnosePatientUtil.findDiagnose(symptoms);
		m.addAttribute("symptomList", userDiseases);
		return "diagnose";
	}	
}
