package com.gobue.blink.demo.demospring.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "user")
public class UserXml {
    private Long id;
    @JacksonXmlProperty(localName = "user_name")
    private String name;
    @JacksonXmlProperty(localName = "user_position")
    private String position;
}

