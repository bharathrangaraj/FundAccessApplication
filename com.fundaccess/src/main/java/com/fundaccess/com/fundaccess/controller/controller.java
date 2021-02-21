package com.fundaccess.com.fundaccess.controller;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fundaccess.com.fundaccess.model.ExchangeRate;
import com.fundaccess.com.fundaccess.service.ExchangeRateRepository;
import com.fundaccess.com.fundaccess.serviceimpl.ExchangeRateService;
import com.fundaccess.com.fundaccess.xml.HttpDownloadUtility;

@RestController
@RequestMapping("/api/exchangeRate")

public class controller {

	String url = "https://www.bundesbank.de/statistic-rmi/StatisticDownload?tsId=BBEX3.D.AUD.EUR.BB.AC.000&its_fileFormat=sdmx&mode=its";
	@Autowired
	private ExchangeRateRepository exchangeRateRepository;
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<ExchangeRate>> listAllRates() throws IOException {
		
	        exchangeRateService.downloadFile();
      
		List<ExchangeRate> rates = exchangeRateService.getAllRates();

		if (rates == null || rates.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		  
		return new ResponseEntity<List<ExchangeRate>>(rates, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/all1", method = RequestMethod.GET)
	public ResponseEntity<List<ExchangeRate>> getAllByDate(
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		List<ExchangeRate> rates = exchangeRateService.getAllRatesByDate(date);

		if (rates == null || rates.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ExchangeRate>>(rates, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ExchangeRate>> getAllByCurrency(@RequestParam("cur") String currency) {
		List<ExchangeRate> rates = exchangeRateService.getAllRatesByCurrency(currency);

		if (rates == null || rates.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ExchangeRate>>(rates, HttpStatus.OK);
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseBody
	public String  listAddRates() {
		
		RestTemplate restTemplate = new RestTemplate();
		 
		String result = restTemplate.getForObject("https://www.bundesbank.de/statistic-rmi/StatisticDownload?tsId=BBEX3.D.AUD.EUR.BB.AC.000&its_fileFormat=sdmx&mode=its",
        		String.class);
 
        System.out.println("SATHYA"+result);
        
		return result;

		
	}
	
	@PostMapping("/addDetails")  
	private long saveStudent(@RequestBody ExchangeRate exchangeRate)   
	{  
	   exchangeRateService.saveOrUpdate(exchangeRate);  
	   return exchangeRate.getId();  
	}
}
