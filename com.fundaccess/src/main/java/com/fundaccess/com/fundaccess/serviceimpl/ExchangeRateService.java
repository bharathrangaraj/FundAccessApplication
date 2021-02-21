package com.fundaccess.com.fundaccess.serviceimpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.io.*; 
import java.net.HttpURLConnection; 
import java.net.MalformedURLException; 
import java.net.URL; 
import java.text.DecimalFormat;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundaccess.com.fundaccess.service.ExchangeRateRepository;
import com.fundaccess.com.fundaccess.model.ExchangeRate;
@Service
public class ExchangeRateService {

	private static final int BUFFER_SIZE = 4096;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	public List<ExchangeRate> getAllRates() {
		return exchangeRateRepository.findAll();
	}
	public List<ExchangeRate> getAllRatesByDate(Date date) {
		return exchangeRateRepository.findAllByDate(date);
	}
	public List<ExchangeRate> getAllRatesByCurrency(String currency) {
		return exchangeRateRepository.findAllByCurrency(currency);
	}
	public void saveOrUpdate(ExchangeRate exchangeRate) {
		
		exchangeRateRepository.save(exchangeRate);
	}

	public static void downloadFile()
            throws IOException {
		StringBuffer stringBuffer = new StringBuffer(); 
        byte[] data = null; 
 
        DecimalFormat decimalFormat = new DecimalFormat("###.###"); 
 
        File file = new File("AUD.csv"); 
 
        try { 
            URL url = new URL("https://www.bundesbank.de/statistic-rmi/StatisticDownload?tsId=BBEX3.D.AUD.EUR.BB.AC.000&its_csvFormat=de&its_fileFormat=csv&mode=its"); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
 
            System.out.println("Connected :)"); 
 
            InputStream inputStream = connection.getInputStream(); 
 
            long read_start = System.nanoTime(); 
 
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); 
 
            int i; 
 
            while ((i = reader.read()) != -1) { 
                char c = (char) i; 
                if(c == '\n') { 
                    stringBuffer.append("\n"); 
                }else { 
                    stringBuffer.append(String.valueOf(c)); 
                } 
            } 
 
            reader.close(); 
 
            long read_end = System.nanoTime(); 
 
            System.out.println("Finished reading response in " + decimalFormat.format((read_end - read_start)/Math.pow(10,6)) + " milliseconds"); 
 
 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
 
        finally { 
            data = stringBuffer.toString().getBytes(); 
        } 
 
        try (FileOutputStream fop = new FileOutputStream(file)) { 
 
            if (!file.exists()) { 
                file.createNewFile(); 
            } 
 
            System.out.println("Initializing write....."); 
 
            long now = System.nanoTime(); 
 
            fop.write(data); 
            fop.flush(); 
            fop.close(); 
 
            System.out.println("Finished writing CSV in " + decimalFormat.format((System.nanoTime() - now)/Math.pow(10,6)) + " milliseconds!"); 
 
 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
 
        System.gc(); 
	}

}
