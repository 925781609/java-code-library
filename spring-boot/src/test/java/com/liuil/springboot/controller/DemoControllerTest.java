package com.liuil.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.liuil.springboot.request.Color;
import com.liuil.springboot.request.DemoRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setupMockMvc() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build(); //初始化MockMvc对象
    }

    @Test
    public void testGetMethod() throws Exception {
        String url = "/get2";

        mvc.perform(MockMvcRequestBuilders.get(url)
                .param("parameter1", "1")
                .param("color", "red"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testPostMethod() throws Exception {

        String url = "/post";

        DemoRequest demoRequest = new DemoRequest("1", Color.RED);
        String json = JSONObject.toJSONString(demoRequest);

        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                //传json参数
                .content(json)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
