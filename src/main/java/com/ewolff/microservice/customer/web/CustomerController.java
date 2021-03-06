package com.ewolff.microservice.customer.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ewolff.microservice.customer.Customer;
import com.ewolff.microservice.customer.CustomerRepository;

@Controller
public class CustomerController {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@RequestMapping(value = "/{id}.html", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView customer(@PathVariable("id") long id) {
		System.out.println("**** in id.html");
		return new ModelAndView("customer", "customer",
				customerRepository.findOne(id));
	}

	@RequestMapping("/list.html")
	public ModelAndView customerList() {
		System.out.println("**** in id.html-- 2");
		return new ModelAndView("customerlist", "customers",
				customerRepository.findAll());
	}

	@RequestMapping("/ping")
	public String ping() {
		System.out.println("**** in id.html-- 2");
		return "Pinging -- Customer MS ";
	}

	@RequestMapping(value = "/form.html", method = RequestMethod.GET)
	public ModelAndView add() {
		System.out.println("**** in id.html -- 3");
		return new ModelAndView("customer", "customer", new Customer());
	}

	@RequestMapping(value = "/form.html", method = RequestMethod.POST)
	public ModelAndView post(Customer customer, HttpServletRequest httpRequest) {
		System.out.println("**** in id.html -- 4");
		customer = customerRepository.save(customer);
		return new ModelAndView("success");
	}

	@RequestMapping(value = "/{id}.html", method = RequestMethod.PUT)
	public ModelAndView put(@PathVariable("id") long id, Customer customer,
			HttpServletRequest httpRequest) {
		System.out.println("**** in id.html -- 5");
		customer.setId(id);
		customerRepository.save(customer);
		return new ModelAndView("success");
	}

	@RequestMapping(value = "/{id}.html", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") long id) {
		customerRepository.delete(id);
		System.out.println("**** in id.html -- 6");
		return new ModelAndView("success");
	}

}
