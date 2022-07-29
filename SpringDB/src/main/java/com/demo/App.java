package com.demo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		
		CarPartsInventory cpInv = (CarPartsInventory) ctx.getBean("carPartsInv5");
		
		//few classes will still be left, whose object's we will only continue creating
		CarPart cp = new CarPart();
		cp.setPartNo(108);
		cp.setPartName("Seat Cover");
		cp.setCarModel("Maruti 800");
		cp.setPrice(3000);
		cp.setQuantity(50);
		
		cpInv.addNewPart(cp);
		
		/*long ms1 = System.currentTimeMillis();
		cpInv.addNewPart(cp);
		long ms2 = System.currentTimeMillis();
		System.out.println("Total time taken : " + (ms2 - ms1) + " ms");*/

		/*ms1 = System.currentTimeMillis();
		cpInv.addNewPart(cp);
		ms2 = System.currentTimeMillis();
		System.out.println("Total time taken : " + (ms2 - ms1) + " ms");*/

		/*List<CarPart> list = cpInv.getAvailableParts();
		for(CarPart cp : list)
			System.out.println(cp);*/
	}
}
