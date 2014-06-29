package com.hospital.spring.mvc.diagnose;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.spring.mvc.data.File;
import com.hospital.spring.mvc.diagnose.query.PatientAnswer;

@Controller
@RequestMapping("/diagnose")
public class DiagnoseController {
	
	@ModelAttribute("patientAnswer")
	public PatientAnswer createFormBean() {
		return new PatientAnswer();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView("begin-query");
		if (File.getFileName().equals("")) {
			mav.addObject("isActive", false);
			return mav;
		} else {
			mav.addObject("isActive", true);
			return mav;
		}
	}	
	
	/**
	 * Begin with questions
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String result() {
		return "ask-patient";
	}
}
