package pl.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;

@Controller
public class HomeController {



	@RequestMapping("/")
	public String welcome() {

		return ViewNames.MAIN;
	}
}
