package com.stockprice;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculateMaxPriceTest {
    @Mock
    BufferedReader reader;

    @Mock
    FileReader file;

    @InjectMocks
    private CalculateMaxPrice cmp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMaxProfit() throws IOException {
        when(reader.readLine()).thenReturn("1/2/2014,19.845715,19.893929,19.715,19.754642,17.296659,234684800/n"+
        "1/3/2014,19.745001,19.775,19.301071,19.320715,16.916723,392467600/n"+
        "1/6/2014,19.194643,19.52857,19.057142,19.426071,17.008968,412610800/n"+
        "1/7/2014,19.440001,19.498571,19.21143,19.287144,16.887329,317209200/n"+
        "1/8/2014,19.243214,19.484285,19.23893,19.409286,16.994274,258529600/n"+
        "1/9/2014,19.52857,19.530714,19.119642,19.161428,16.777252,279148800/n"+
        "1/10/2014,19.279642,19.314285,18.968214,19.033571,16.665304,304976000/n"+
        "1/13/2014,18.925358,19.375,18.924286,19.133215,16.752554,378492800/n"+
        "1/14/2014,19.222143,19.526072,19.202143,19.513929,17.085894,332561600/n"+
        "1/15/2014,19.768572,20.007143,19.702143,19.905714,17.428932,391638800/n"+
        "1/16/2014,19.817858,19.887501,19.702856,19.794643,17.331675,229278000/n");
        ResourceObject rb = cmp.maxProfit("XYZ", "2020");
        assertEquals(0.00, rb.getBuyPrice());
    }

    @Test
    void testMaxProfit_withException() throws IOException {
        when(reader.readLine()).thenThrow(new IOException());
        ResourceObject rb = cmp.maxProfit("XPL", "2014");
        assertEquals(null, rb.getBuyDate());
    }

    @Test
    void testMaxProfit_withNumberFormatException() throws IOException {
        when(reader.readLine()).thenReturn("1/2/2014,a12,19.893929,19.715,19.754642,17.296659,234684800",
                "1/3/2014,19.745001,19.775,19.301071,19.320715,16.916723,392467600", null);
        ResourceObject rb = cmp.maxProfit("XPL", "2014");
        assertEquals(null, rb.getBuyDate());
    }

}