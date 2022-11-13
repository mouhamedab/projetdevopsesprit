package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;

@ExtendWith(MockitoExtension.class)

class SecteurActiviteServiceImplMock {

	@Mock
	SecteurActiviteRepository secteurActiviteRepository;
	@InjectMocks
	SecteurActiviteServiceImpl secteurActiviteServiceImpl;
	//on a initialiser un objet sa pour tester avec
	SecteurActivite sa = new SecteurActivite((long) 2 ,"cinq","secteur",null);
	List<SecteurActivite> secteur = new ArrayList<SecteurActivite>() {
		{
		add (new SecteurActivite((long) 3 ,"quatre","secteur2", null));
		add (new SecteurActivite((long) 4 , "sept","secteur3", null));
		add (new SecteurActivite((long) 5, "vingt","secteur5", null));

		
		}
	};
	
	
	@Test
	void testRetrieveAllSecteurActivite() {

		
		Mockito.doReturn(secteur).when(secteurActiviteRepository).findAll();
        List<SecteurActivite> secteurAc = secteurActiviteServiceImpl.retrieveAllSecteurActivite();
		Assertions.assertNotNull(secteurAc);	


		
	}	

	@Test
	void testAddSecteurActivite() {
		
		SecteurActivite sa = new SecteurActivite();
		Mockito.when(secteurActiviteRepository.save(Mockito.any(SecteurActivite.class))).thenReturn(sa);
	    SecteurActivite sec=secteurActiviteServiceImpl.addSecteurActivite(sa);
		Assertions.assertNotNull(sec);	
	}
	
	@Test
	void testDeleteSecteurActivite() {

		secteurActiviteServiceImpl.deleteSecteurActivite((long)2);
		Mockito.verify(secteurActiviteRepository).deleteById((long)2);

		
	}
	

	@Test
	void testUpdateSecteurActivite() {
		
		
		Mockito.when(secteurActiviteRepository.save(Mockito.any(SecteurActivite.class))).thenReturn(sa);
		sa.setLibelleSecteurActivite("activite");
		SecteurActivite sec=secteurActiviteServiceImpl.updateSecteurActivite(sa);
		assertNotNull(sec);
		assertEquals("activite", sa.getLibelleSecteurActivite());

	}

	@Test
	void testRetrieveSecteurActivite() {
		
		Mockito.when(secteurActiviteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(sa));
		SecteurActivite saa = secteurActiviteServiceImpl.retrieveSecteurActivite((long)2);
		Assertions.assertNotNull(saa);	
		}

}
