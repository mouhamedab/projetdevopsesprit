package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;


import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FournisseurServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FournisseurServiceImplTest {

    @MockBean
    private DetailFournisseurRepository detailFournisseurRepository;

    @MockBean
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private FournisseurServiceImpl fournisseurServiceImpl;

    @MockBean
    private ProduitRepository produitRepository;

    @MockBean
    private SecteurActiviteRepository secteurActiviteRepository;


    @Test
    void testRetrieveFournisseur() {

        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setAdresse("ariana");
        detailFournisseur.setEmail("ayman.aloulou@esprit.tn");
        detailFournisseur.setFournisseur(new Fournisseur());


        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setDetailFournisseur(detailFournisseur);
        fournisseur.setIdFournisseur(1L);
        Optional<Fournisseur> ofResult = Optional.of(fournisseur);
        when(fournisseurRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(fournisseur, fournisseurServiceImpl.retrieveFournisseur(1L));
        verify(fournisseurRepository).findById((Long) any());
    }



    @Test
    void testAddFournisseur() {
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setEmail("ayman.alouou@esprit.tn");
        detailFournisseur.setFournisseur(new Fournisseur());


        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCode("0000");
        fournisseur.setDetailFournisseur(detailFournisseur);
        assertSame(fournisseur, fournisseurServiceImpl.addFournisseur(fournisseur));
        verify(fournisseurRepository).save((Fournisseur) any());
    }


}