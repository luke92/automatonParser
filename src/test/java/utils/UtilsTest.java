package utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class UtilsTest {
	@Test
	public void readFileFound() {
		List<String> lineas = FileUtil.getLinesFromFile("automata.txt");
		assertNotNull(lineas);
	}
	
	@Test
	public void readFileNotFound() {
		List<String> lineas = FileUtil.getLinesFromFile("automatas.txt");
		assertNull(lineas);
	}
	
	@Test
	public void readFileHasLines() {
		List<String> lineas = FileUtil.getLinesFromFile("automata.txt");
		assertFalse(lineas.isEmpty());
	}
	
	@Test
	public void readFileNotHasLines() {
		List<String> lineas = FileUtil.getLinesFromFile("automataSinTexto.txt");
		assertTrue(lineas.isEmpty());
	}
	
	@Test
	public void tryParseIntOk() {
		assertTrue(FormatterUtil.tryParseInt("1"));
	}
	
	@Test
	public void tryParseIntError() {
		assertFalse(FormatterUtil.tryParseInt("a"));
	}
	
	@Test
	public void TripletEqualsOk() {
		Triplet<String,String,String> triplet1 = Triplet.of("a", "b", "c");
		Triplet<String,String,String> triplet2 = Triplet.of("a", "b", "c");
		assertTrue(triplet1.equals(triplet2));
	}
	
	@Test
	public void TripletEqualsError() {
		Triplet<String,String,String> triplet1 = Triplet.of("a", "b", "c");
		Triplet<String,String,String> triplet2 = Triplet.of("b", "a", "c");
		assertFalse(triplet1.equals(triplet2));
	}
	
	@Test
	public void TripletHashError() {
		Triplet<String,String,String> triplet1 = Triplet.of("a", "b", "c");
		Triplet<String,String,String> triplet2 = Triplet.of("b", "a", "c");
		assertFalse(triplet1.hashCode() == triplet2.hashCode());
	}
	
	@Test
	public void TripletHashOk() {
		Triplet<String,String,String> triplet1 = Triplet.of("a", "b", "c");
		Triplet<String,String,String> triplet2 = Triplet.of("a", "b", "c");
		assertTrue(triplet1.hashCode() == triplet2.hashCode());
	}
	
	@Test
	public void TripletToStringOk() {
		Triplet<String,String,String> triplet1 = Triplet.of("a", "b", "c");
		Triplet<String,String,String> triplet2 = Triplet.of("a", "b", "c");
		assertTrue(triplet1.toString().equals(triplet2.toString()));
	}
}
