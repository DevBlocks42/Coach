package com.example.coach.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfileTest
{
    private Profile profile = new Profile(67, 35, 165, 0);
    private float img = (float)32.2;
    String message = "Surcharge.";
    @Test public void testGetImg()
    {
        assertEquals(img, profile.getImg(), (float)0.1);
    }

    @Test public void testGetMessage()
    {
        assertEquals(message, profile.getMessage());
    }
}