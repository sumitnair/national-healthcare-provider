package com.healthcare.provider.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CSVAdapter {

	public static final Logger logger = LoggerFactory.getLogger(CSVAdapter.class);


	/**
	 * This method reads CSV file rows and convert it into list of provider. Which will be later indexed into ES
	 * @return List<ProviderDetail>
	 * @throws IOException
	 */
	public List<ProviderDetail> getProviderDetails() throws IOException {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		List<ProviderDetail> providerDetailList = new LinkedList<>();
		Long customKey = 1l;
		String csvRow = "";

		try (InputStream inputStream = classloader.getResourceAsStream("providerDetails.csv");
			InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(streamReader)) {

			/* Skip CSV Header*/			
			br.readLine();

			while ((csvRow = br.readLine()) != null) {
				ProviderDetail providerDetail = getProviderDetail(csvRow,customKey);
				if(null != providerDetail) {
					providerDetailList.add(providerDetail);
					customKey++;
				}				 
			}
		}
		catch (IOException e) {
			logger.error("error occured while parsing CSV file");
			throw e;
		}

		logger.info("number of rows processed : " +providerDetailList.size());
		return providerDetailList;

	}

	/**
	 *  This method is a POJO generator
	 * @param line 
	 * @param customKey 
	 * @param providerDetailList
	 * @param providerDetails
	 * @param pd
	 */
	private ProviderDetail getProviderDetail(String csvRow, Long customKey) {
		ProviderDetail pd = null;
		try {			
			String[] providerDetails = csvRow.split(Constants.SPLIT_BY_COMMA);
			if(providerDetails.length != 12) {
				return null;
			}

			pd = new ProviderDetail(customKey, providerDetails[1],Long.valueOf(providerDetails[1]), providerDetails[2], providerDetails[3],
					providerDetails[4], providerDetails[5], Integer.valueOf(providerDetails[6]), providerDetails[7], 
					Long.valueOf(providerDetails[8]), Double.valueOf(providerDetails[9].substring(1)),
					Double.valueOf(providerDetails[10].substring(1)), Double.valueOf(providerDetails[11].substring(1)));

		} catch (Exception e) {
			logger.error("error occured while parsing one of the rows in CSV with provider id: " + pd.getProviderId());
		}
		return pd;
	}

}

