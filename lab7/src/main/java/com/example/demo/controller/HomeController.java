package com.example.demo.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Greeting;
import com.example.demo.model.MyString;
import com.example.demo.model.GetQuestion;
import com.example.demo.model.Count;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.questions.*;

@SessionAttributes("count")
@Controller 
public class HomeController { 

    private static final int TOTAL_QUESTIONS = 5;

    @GetMapping("/") 
    public String home() { 
        return "home";
    } 

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        Count count = new Count();
        count.count = count.count + 1;
        model.addAttribute("greeting", new Greeting());
        model.addAttribute("count", new Count());
        
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, @ModelAttribute Count count, Model model) {
        model.addAttribute("greeting", greeting);
        model.addAttribute("count", count);
        return "result";
    }

	@GetMapping("/retake")
	public String retakeQuiz(org.springframework.web.bind.support.SessionStatus status) {
   	 	status.setComplete();
   	 	return "redirect:/get-question";
	}

	@GetMapping({"/get_question", "/get-question"})
	public String questionForm(Model model) {

		Count count;
		if (!model.containsAttribute("count")) {
			count = new Count();
			count.count = 1;
		} else {
			count = (Count) model.getAttribute("count");
			if (count.count == 0) {
				count.count = 1;
			}
		}
		
		System.out.println("Count is " + count.getCount());  
		model.addAttribute("count", count);

		MyString myStringObject = new MyString();
		GetQuestion getQuestion = new GetQuestion();
		myStringObject.setMyString(getQuestion.nextQuestion(count.getCount()).getQuestion());
		model.addAttribute("myString", myStringObject);

		return "question";
	}

    @PostMapping({"/get_question", "/get-question"})
	public String questionFormPOST(
			@ModelAttribute("count") Count count, 
			@ModelAttribute("myString") MyString myStringObject, 
			@RequestParam(value = "answer", required = false) String answer,
			Model model) {

		if (answer == null) {
			model.addAttribute("errorMessage", "Oops! Need to select an answer first!");
			return "question";
		}

		GetQuestion getQuestion = new GetQuestion();
		QuestionTrueFalse qtf = getQuestion.nextQuestion(count.count);
		model.addAttribute("QuestionTrueFalse", qtf);

		Boolean answerBool = Boolean.valueOf(answer);
		if (answerBool.equals(qtf.getAnswer())) {
			System.out.println("Correct!");
			count.correct = count.correct + 1;
		} else {
			System.out.println("Wrong!");
			count.incorrect = count.incorrect + 1;
		}

		count.count = count.count + 1;
		System.out.println("Count is " + count.getCount());
		model.addAttribute("count", count);

		if (count.count > TOTAL_QUESTIONS) {
			Greeting greeting = new Greeting();
			greeting.setContent("Quiz Complete! You answered all " + TOTAL_QUESTIONS + " questions.");
			model.addAttribute("greeting", greeting);
			model.addAttribute("correct", count.correct);
			model.addAttribute("incorrect", count.incorrect);
			model.addAttribute("total", TOTAL_QUESTIONS);
			return "result";
		}

		myStringObject.setMyString(getQuestion.nextQuestion(count.count).getQuestion());
		model.addAttribute("myString", myStringObject);

		return "question";
	}

}
