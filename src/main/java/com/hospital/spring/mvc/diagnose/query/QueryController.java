package com.hospital.spring.mvc.diagnose.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.spring.mvc.data.Data;
import com.hospital.spring.mvc.data.Disease;
import com.hospital.spring.mvc.data.Symptom;


@Controller
@SessionAttributes("patientAnswer")
@RequestMapping("/diagnosing")
public class QueryController {
	
	/**
	 * Holds possible diseases what patient could have.
	 */
	private Map<Disease, ArrayList<Symptom>> allPossibleDiseases;
	
	/**
	 * List to remember which symptoms have patient already answered.
	 */
	private Set<Symptom> answeredSymptos;
	
	private int questionNumber;

	@ModelAttribute("patientAnswer")
	public PatientAnswer createFormBean() {
		return new PatientAnswer();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView prepare() {
		ModelAndView mav = new ModelAndView("ask-patient");
		mav.addObject("isActive", true);
		Data data = new Data();
		ArrayList<Disease> diseases = data.getDiseases();
		allPossibleDiseases = new HashMap<Disease, ArrayList<Symptom>>();
		answeredSymptos = new HashSet<Symptom>();
		questionNumber = 1;
		for (Disease d : diseases) {
			allPossibleDiseases.put(d, d.getAllSymptoms());
		}
		if (allPossibleDiseases != null) {
			Set<Disease> diseaseSet = allPossibleDiseases.keySet();
			Iterator<Disease> it = diseaseSet.iterator();
			it.next();
			Symptom ask = askNewSymptom();
			mav.addObject("symptom", ask);
			mav.addObject("questionNR", questionNumber);
		}
		questionNumber++;
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ModelAndView getQuestion(@ModelAttribute PatientAnswer pa) {
		
		if (pa.isAnswer()) {
			removeAllDiseasesWithoutThisSymptom(pa.getSymptom());
		} else {
			removeAllDiseasesWithThisSymptom(pa.getSymptom());
		}
		
		ModelAndView mav = new ModelAndView("ask-patient");
		
		if (allPossibleDiseases.size() <= 1) {
			for(Iterator<Entry<Disease, ArrayList<Symptom>>> it = allPossibleDiseases.entrySet().iterator(); it.hasNext(); ) {
				Entry<Disease, ArrayList<Symptom>> entry = it.next();
				mav.addObject("diagnose", entry.getKey());
				mav.addObject("symptomList", entry.getValue());
			}
			return mav;
		}
		
		if (allPossibleDiseases != null) {
			Symptom ask;
			ask = askNewSymptom();
			mav.addObject("symptom", ask);
			mav.addObject("questionNR", questionNumber);
		}
		questionNumber++;
		return mav;
	}

	/**
	 * When patient has answered negatively to symptom question then have 
	 *  to remove all diseases which have this symptom
	 * @param symptom
	 */
	private void removeAllDiseasesWithThisSymptom(Symptom symptom) {
		for(Iterator<Entry<Disease, ArrayList<Symptom>>> it = allPossibleDiseases.entrySet().iterator(); it.hasNext(); ) {
		      Entry<Disease, ArrayList<Symptom>> entry = it.next();
		      if(entry.getKey().getAllSymptoms().contains(symptom)) {
		    	  it.remove();
		      }
		}
	}

	/**
	 * When patient has answered positively to symptom question then have 
	 *  to remove all diseases which don't have this symptom
	 * @param symptom
	 */
	private void removeAllDiseasesWithoutThisSymptom(Symptom symptom) {
		for(Iterator<Entry<Disease, ArrayList<Symptom>>> it = allPossibleDiseases.entrySet().iterator(); it.hasNext(); ) {
		      Entry<Disease, ArrayList<Symptom>> entry = it.next();
		      if(!entry.getKey().getAllSymptoms().contains(symptom)) {
		    	  it.remove();
		      } 
		}
	}

	/**
	 * Finds new question to ask next from patient
	 * 
	 * @return
	 */
	private Symptom askNewSymptom() {
		Set<Disease> diseaseSet = allPossibleDiseases.keySet();
		Iterator<Disease> it = diseaseSet.iterator();
		Symptom ask = null;
		while (it.hasNext()) {
			Disease current = it.next();
			ArrayList<Symptom> symptoms = current.getAllSymptoms();
			Collections.sort(symptoms);
			for (Symptom s : symptoms) {
				removeAllSameSymptoms(s);
				if (!answeredSymptos.contains(s)) {
					ask = s;
					break;
				}
			}
			if (ask != null)
				break;
		}
		answeredSymptos.add(ask);
		return ask;
	}
	
	/**
	 * Method to check if there are any symptom which all possible diseases have.
	 * Then is no point to ask these symptoms.
	 * 
	 * @param s current symptom
	 */
	private void removeAllSameSymptoms(Symptom s) {
		Set<Disease> diseaseSet = allPossibleDiseases.keySet();
		Iterator<Disease> it = diseaseSet.iterator();
		int counter = 0;
		while (it.hasNext()) {
			Disease d = it.next();
			if (d.getAllSymptoms().contains(s)) {
				counter++;
			}
		}
		if (counter >= allPossibleDiseases.size()) {
			answeredSymptos.add(s);
		}
	}
}