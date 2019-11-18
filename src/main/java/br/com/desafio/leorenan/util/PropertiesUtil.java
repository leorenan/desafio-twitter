package br.com.desafio.leorenan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class PropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	@Getter private static String TwitterBaseUrl;
	@Getter private static String TwitterConsumerKey;
	@Getter private static String TwitterConsumerSecret;
	
	static {
		try (InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
            	logger.error("Erro ao carregar o config.properties");
            }

            prop.load(input);

    		TwitterBaseUrl = prop.getProperty("twitter.baseurl");
    		TwitterConsumerKey = prop.getProperty("twitter.consumer.key");
    		TwitterConsumerSecret = prop.getProperty("twitter.consumer.secret");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	
	
}
