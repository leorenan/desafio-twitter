package br.com.desafio.leorenan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class PropertiesUtil {

	@Getter private static String TwitterBaseUrl;
	@Getter private static String TwitterConsumerKey;
	@Getter private static String TwitterConsumerSecret;
	
	static {
		try (InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Erro ao carregar o config.properties");
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
