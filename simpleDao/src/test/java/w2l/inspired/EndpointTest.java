package w2l.inspired;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(locations = "classpath:context.xml")
public class EndpointTest {
    MockMvc mockMvc;
    @BeforeEach
    void setup(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test

    void testGet()throws Exception{
        String url = "/today";
        mockMvc.perform(get(url))
                .andExpect(model().size(2))
                .andExpect(status().isOk());
    }
    @Test
    void testPostOneP()throws Exception{
        String url = "/today";
        mockMvc.perform(post(url).content("1=on&2=on"))
                .andExpect(model().size(5))
                .andExpect(model().attribute("yesterday",0))
                .andExpect(model().attribute("now",1))
                .andExpect(status().isOk());
    }
}
