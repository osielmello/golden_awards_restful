package com.awards.base;

import com.awards.service.ImportDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public abstract class AbstractTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mvc;
    private MvcResult mvcResult;

    protected void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    protected void post(String uri, String json, Object... value) throws Exception {
        mvcResult = this.mvc.perform(MockMvcRequestBuilders.post(uri, value).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json)).andReturn();
    }

    protected void put(String uri, String json, Object... values) throws Exception {
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri, values).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json)).andReturn();
    }

    protected void get(String uri) throws Exception {
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
    }

    protected void get(String uri, Object... value) throws Exception {
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, value).accept(MediaType.APPLICATION_JSON)).andReturn();
    }

    protected void delete(String uri, Long id) throws Exception {
        mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, id).contentType(MediaType.APPLICATION_JSON)).andReturn();
    }

    protected <T> T getResponseAs(Class<T> clazz) throws Exception {
        if (mvcResult.getResponse().getContentAsString().length() == 0) {
            return null;
        }
        return this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
    }

    protected String toJson(Object o) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(o);
    }

    protected void checkResult(int httpStatus) throws Exception {
        Assert.isTrue(mvcResult.getResponse().getStatus() == httpStatus, "Esperado HTTP status " + httpStatus);
    }

}
