package test;

import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.services.IReglementService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Testreglement {
	@Autowired
	IReglementService ReglementService;

	@Test
	public void testAddReglement() {
		List<Reglement> Reglements = ReglementService.retrieveAllReglements();
		int expected=Reglements.size();
		Reglement s = new Reglement(50,45,false);
		Reglement savedReglement= ReglementService.addReglement(s);

		assertEquals(expected+1, ReglementService.retrieveAllReglements().size());
		assertNotNull(savedReglement.getIdReglement());
		ReglementService.retrieveReglement(savedReglement.getIdReglement());

	}

	@Test
	public void testAddReglementOptimized() {

		Reglement s = new Reglement(50,45,false);
		Reglement savedReglement= ReglementService.addReglement(s);
		assertNotNull(savedReglement.getIdReglement());
		assertSame(false, savedReglement.getPayee());
		assertTrue(savedReglement.getMontantRestant() > 0);
		ReglementService.retrieveReglement(savedReglement.getIdReglement());

	}


}
