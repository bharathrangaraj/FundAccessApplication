package com.fundaccess.com.fundaccess.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.fundaccess.com.fundaccess.model.ExchangeRate;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {

	List<ExchangeRate> findAll();
	List<ExchangeRate> findAllByDate(Date date);
	List<ExchangeRate> findAllByCurrency(String currency);
}

