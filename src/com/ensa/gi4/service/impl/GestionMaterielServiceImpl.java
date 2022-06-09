package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    
    MaterielDao materielDao;
    @Autowired
    public GestionMaterielServiceImpl(MaterielDao materiel ) {
    	this.materielDao=materiel;
    }

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void listerMateriel() {
        System.out.println(materielDao.findAll());
    }

    @Override
    public void ajouterNouveauMateriel(Materiel materiel) {
    	if((materiel != null) && (materielDao.matereielExiste(materiel.getName())==null )) {
    		materielDao.ajouterNvMateriel(materiel);
    		 System.out.println("L'ajout du matriel " + materiel.getName() + " effectu� avec succ�es !");    		
    	}
    	else if((materiel != null) && (materielDao.matereielExiste(materiel.getName())!=null )) {
    		 System.out.println("Le matriel " + materiel.getName() + " d�j� existe dans la base de donn�es !");   
    	}else
    	{
    		System.out.println(" Veuillez remplir les champs correctement!");   
    		
    	}

       
    }
    @Override
	public void supprimerMateriel(Long id) {
    	if((id != null) && (materielDao.findOne(id)!=null )) {
    		materielDao.supprimerMateriel(id);
    		 System.out.println("La suppression du matriel  effectu� avec succ�es !");    		
    	}
    	else if ((id != null) && (materielDao.findOne(id)==null )) {
    		 System.out.println("L'id que vous avez entrer n'exise pas dans la base de donn�es!");  
    	}
    	else {
    		 System.out.println("La suppression n'est pas effectuer, ressayer � nouveau!");  
    	}
		
	}
    
    
	@Override
	public void findMateriel(Long id) {
		if(materielDao.findOne(id)!=null) {
			System.out.println(materielDao.findOne(id));
		}else {
			System.out.println("le mat�riel n'existe pas");
		}


	}

	@Override
	public void modifierInfosMateriel(Long id, String nom, String code) {
		if(materielDao.modifierInfosMateriel(id, nom, code)) {
			System.out.println("La modification a �t� bien effectu�e");
		}else {
			System.out.println("une erreur a �t� survenu veuillez r�essayer � nouveau!");
		}
		
	}

	@Override
	public void indisponibleMateriel(Long id) {
		if(materielDao.indisponibleMateriel(id)) {
			System.out.println("Le materiel qui porte L'id :"+id +" n'est plus disponible");
		}else {
			System.out.println("L'id n'existe pas ,veuillez r�essayer � nouveau!");
		}
		
	}
	@Override
	public void afficherMaterielAllouerParUtilisateur() {
		materielDao.afficherMaterielAlloueParUtilisateur();
	}
	
	@Override
	public boolean listerMaterielAlloue() {

		if (!materielDao.listerMaterielsAlloue().isEmpty()) {
			System.out.println(materielDao.listerMaterielsAlloue());
			return true;
		} else {
			System.out.println("Vous n'avez pas allou�(e) aucun mat�riel");
			return false;
		}

	}

	
}

