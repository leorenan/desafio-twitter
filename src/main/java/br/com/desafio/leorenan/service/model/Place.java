package br.com.desafio.leorenan.service.model;

import java.util.List;

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
public class Place
{
    private String id;

    private String url;

    private String place_type;

    private String name;

    private String full_name;

    private String country_code;

    private String country;

    private List<String> contained_within;

    private Bounding_box bounding_box;

    private Attributes attributes;

}
