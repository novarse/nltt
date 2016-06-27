package org.smw.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.smw.view.View;

public class BaseControllerTest {

	@Test
	public void testDefaultHome() {
		BaseController c = new BaseController();
		assertEquals(View.HOME, c.defaultHome());
	}

	@Test
	public void testHome() {
		BaseController c = new BaseController();
		assertEquals(View.HOME, c.home());
	}

	@Test
	public void testHeader() {
		BaseController c = new BaseController();
		assertEquals(View.HEADER, c.header());
	}

	@Test
	public void testError400() {
		BaseController c = new BaseController();
		assertEquals(View.BAD_REQUEST, c.error400());
	}

	@Test
	public void testError404() {
		BaseController c = new BaseController();
		assertEquals(View.PAGE_NOT_FOUND, c.error404());
	}

}