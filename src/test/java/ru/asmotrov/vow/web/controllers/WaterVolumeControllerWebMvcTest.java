package ru.asmotrov.vow.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.asmotrov.vow.core.WaterVolumeCalculatingService;
import ru.asmotrov.vow.core.impl.DefaultWaterVolumeCalculatingService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Web MVC test for {@link WaterVolumeController}
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class WaterVolumeControllerWebMvcTest {

    private static final String ENDPOINT = "/volume";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnBadRequestIfNoBody() throws Exception {
        mockMvc.perform(postWithDefaultsAndBody(""))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void shouldReturnZeroIfEmptyArray() throws Exception {
        mockMvc.perform(postWithDefaultsAndBody("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void shouldReturnSpecificValueForDefinedArray() throws Exception {
        mockMvc.perform(postWithDefaultsAndBody("[10, 4, 3, 6, 10]"))
                .andExpect(status().isOk())
                .andExpect(content().string("17"));
    }

    private MockHttpServletRequestBuilder postWithDefaultsAndBody(String body) {
        return post(ENDPOINT)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(body);
    }
}
