package org.LifeGame;

import org.junit.Test;

import junit.framework.Assert;

public class TestLife {

	@Test
	public void test_createLife() {
		Cell living = new Cell();

		Assert.assertEquals(false, living.isAlive());
	}

	@Test
	public void test_createLifeandKill() {
		Cell living = new Cell();

		living.makeAlive();
		living.kill();

		Assert.assertEquals(false, living.isAlive());
	}

	@Test
	public void test_KillAndRessurect() {
		Cell living = new Cell();

		living.makeAlive();

		Assert.assertEquals(true, living.isAlive());
	}

	@Test
	public void test_CreatePlanAndMakeOneLive() {
		Plan plan = new Plan(4, 5);

		plan.get(3, 3).makeAlive();

		Assert.assertEquals(true, plan.get(3, 3).isAlive());
	}

	@Test
	public void test_DiesByLackingNeighboors() {
		Plan plan = new Plan(4, 5);

		plan.get(0, 0).makeAlive();

		plan.tourDeBoucle();

		Assert.assertEquals(false, plan.get(0, 0).isAlive());
	}

	@Test
	public void test_DiesByTooMuch() {
		Plan plan = new Plan(4, 5);

		plan.get(0, 0).makeAlive();
		plan.get(1, 0).makeAlive();
		plan.get(0, 1).makeAlive();
		plan.get(1, 1).makeAlive();
		plan.get(1, 2).makeAlive();

		plan.tourDeBoucle();

		Assert.assertEquals(false, plan.get(1, 1).isAlive());
	}

	@Test
	public void test_Ressurect() {
		Plan plan = new Plan(4, 5);

		plan.get(0, 0).makeAlive();
		plan.get(1, 0).makeAlive();
		plan.get(0, 1).makeAlive();

		plan.tourDeBoucle();

		Assert.assertEquals(true, plan.get(1, 1).isAlive());
	}

}
