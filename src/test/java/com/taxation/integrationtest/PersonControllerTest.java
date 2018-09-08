package com.taxation.integrationtest;
/*package com.taxation.integrationtest;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.taxation.entity.Tag;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;
    

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/hello").header("Origin","*").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }
    
    @Test
    public void itShouldReturnSameJsonResponse_Success() throws Exception {
    	Tag tag=new Tag();
    	tag.setName("Harsh");
    	tag.setId(2);
    	tag.setLike(3);
    	mvc.perform(MockMvcRequestBuilders.post("/api/tag/add").header("Origin","*").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(tag)))
    	.andExpect(status().isOk())
    	.andExpect(content().string(equalTo(new Gson().toJson(tag))));

    	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/tag/add").header("Origin","*").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(tag)))
    	.andReturn();
    	String resource = mvcResult.getResponse().getContentAsString();
    	Tag tag1 = new Gson().fromJson(resource, Tag.class);
    	assertEquals(tag.getName(), tag1.getName());

    	}
    
}*/