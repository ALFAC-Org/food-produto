package br.com.alfac.foodproduto.unit.infra.healthcheck.handler;

import br.com.alfac.foodproduto.infra.config.exception.ApiErrorItem;

import br.com.alfac.foodproduto.infra.healthcheck.handler.HealthCheckHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.mock.mockito.MockBean;

class HealthCheckHandlerTest {

    private MockMvc mockMvc;

    @MockBean
    private ApiErrorItem apiError;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        HealthCheckHandler itemHandler = new HealthCheckHandler();
        mockMvc = MockMvcBuilders.standaloneSetup(itemHandler)
            .addFilter((request, response, chain) -> {
                response.setCharacterEncoding("UTF-8");
                chain.doFilter(request, response);
            }, "/*")
        .build();
    }

    @Test
    void deveRetornarHealthCheckComSucesso() throws Exception {

        // Act & Assert
        mockMvc.perform(get("/api/v1/health-check")
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("Aplicação está funcionando. Veja as versões atuais."));
    }

}