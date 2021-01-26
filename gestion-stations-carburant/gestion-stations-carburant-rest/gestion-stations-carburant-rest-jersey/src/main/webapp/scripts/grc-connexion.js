$( document ).ready(function() {
	
	const CHAMP_NOM_UTILISATEUR = $("#nom-utilisateur");
	
	const CHAMP_MOT_DE_PASSE	= $("#mot-de-passe");
	
	const ID_BOUTON_VALIDER		= "#boutton-valider";
	
	const IDS_CHAMPS			= [CHAMP_NOM_UTILISATEUR, CHAMP_MOT_DE_PASSE];
	
	const CONT_ACTIF			= "activer-container"
		
		

	/**
	 * Cache un element HTML.
	 */
	function afficherSection(nomClasse) {
		$("." + nomClasse).css("display", "block");
	}

	/**
	 * Affiche un element HTML caché.
	 */
	function cacherSection(nomClasse) {
		$("." + nomClasse).css("display", "none");
	}

	function activerChargementPage() {
		
		cacherSection("activer-container");
		afficherSection("chargement");
	}
		   
	function changerEtatBoutonValider(){
		
		for(var id1 in IDS_CHAMPS){
			
			IDS_CHAMPS[id1].keyup(function(event){
				
				var champsOk = true;
				
				for(var id2 in IDS_CHAMPS){
					if(IDS_CHAMPS[id2].val().trim() == "" ){
						champsOk = false;
						break;
					}
				}
				
				if(champsOk){
					activerBouttonValidation();
				}
				else {
					desactiverBouttonValidation();
				}
			});
		}
	}


    /**
     * Cette fonction réactive l'option disabled d'un element HTML
     * @selecteur comme .class ou #id.
     */
    function desactiverChamp(selecteur){
	    $(selecteur)[0].disabled = true;
    }
  
    /**
     * Pour désactiver le bouton valider.
     */
    function desactiverBouttonValidation(){
	    desactiverChamp(ID_BOUTON_VALIDER);
	    $(ID_BOUTON_VALIDER).addClass("disabled");
	    $(ID_BOUTON_VALIDER).change(function(){
	    	;
	    });
    }
  
    /**
     * Pour activer le bouton valider.
     */
    function activerBouttonValidation(){
	    activerChamp(ID_BOUTON_VALIDER);
	    $(ID_BOUTON_VALIDER).removeClass("disabled");
	    $(ID_BOUTON_VALIDER).click(function(){
	    	activerChargementPage();
	    	submit();
	    });
    }
      
  
    /**
     * cette fonction désactive l'option disabled d'un element html
     * @selecteur comme .class ou #id
     */
    function activerChamp(selecteur){
	    $(selecteur)[0].disabled = false;
    }

	  
	  /**
	   * Affiche les tag html caché par css via display:none
	   */
	  function afficherTag(classTag){
		  $("."+classTag).addClass(CONT_ACTIF);
	  }
	  
	  function submit(){
		  document.forms.connexion.submit();
	  }
    
    changerEtatBoutonValider();
    afficherTag("container-form-grc");
    afficherTag("contenu-page");
    
    
});
