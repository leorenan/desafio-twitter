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
public class Search_metadata {
	private double completed_in;

	private long max_id;

	private String max_id_str;

	private String next_results;

	private String query;

	private String refresh_url;

	private long count;

	private long since_id;

	private String since_id_str;
}