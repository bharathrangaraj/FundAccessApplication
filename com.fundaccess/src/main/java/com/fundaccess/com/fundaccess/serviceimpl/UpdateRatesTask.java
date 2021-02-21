package com.fundaccess.com.fundaccess.serviceimpl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fundaccess.com.fundaccess.model.ExchangeRate;
import com.fundaccess.com.fundaccess.service.ECBLookupService;


@Component
public class UpdateRatesTask {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ECBLookupService ecbLookupService;

	@Autowired
	private ExchangeRateService exchangeRateService;

	@Scheduled(fixedRateString = "${update.task.rate}")
	public void updateExchangeRates() {
		System.out.print("Bharath");
		try {
			Collection<ExchangeRate> updatedRates = ecbLookupService.getUpdatedRates();
			//exchangeRateService.updateRates(updatedRates);
		} catch (Exception e) {
		}
	}

}
