package br.com.desafio.leorenan.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Statuses {
	private String created_at;

	private long id;

	private String id_str;

	private String text;

	private boolean truncated;

	private Entities entities;

	private Metadata metadata;

	private String source;

	private String in_reply_to_status_id;

	private String in_reply_to_status_id_str;

	private String in_reply_to_user_id;

	private String in_reply_to_user_id_str;

	private String in_reply_to_screen_name;

	private User user;

	private String geo;

	private String coordinates;

	private Place place;

	private String contributors;

	private Retweeted_status retweeted_status;

	private boolean is_quote_status;

	private int retweet_count;

	private int favorite_count;

	private boolean favorited;

	private boolean retweeted;

	private String lang;

}