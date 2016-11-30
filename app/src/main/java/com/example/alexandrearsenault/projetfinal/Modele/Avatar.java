package com.example.alexandrearsenault.projetfinal.Modele;


public class Avatar extends Modele {
	
	//@Column(name = "Nom", length=50 , nullable = false)
	/**
	 * A description for the avatar 
	 */
    private String name;
    
	/**
	 *  The avatar image in Base64
	 */
    //@Column(name = "Avatar")
    private String avatar;
	
    
    
    /**
     * DO NOT USE it is useless 
     * "Unenhanced classes must have a public or protected no-args constructor"
     */
    public Avatar(){
    }
    
    
    /**
     * Use this constructor instead. 2 params
     * 
     * @param pNom, A description for the avatar 
     * @param pAvatar The avatar image in Base64
     */
    public Avatar(String pNom , String pAvatar ){
		if(pNom.length()<= 50){
			this.name    = pNom;
			this.avatar = pAvatar;
		}
	}


    /**
     * Get the avatar's name ( description ) 
     * @return name
     */
	public String getName() {
		return name;
	}

	/**
	 * Set the name (description) of the avatar. 
	 * @param pName The name to be set.
	 * @return ok If the name has been change.
	 */
	
	public boolean setName(String pName) {
		boolean ok = validateName(pName);
		this.name = (ok?pName  :this.name);
		return ok;
	}
	
	/**
	 * Validate a name.<br>
	 * <p>The policy for an avatar's name is:</p>
	 * <ul>
	 *  <li><p>At least 4 chars</p></li>
	 *  <li><p>No longer than 20 chars</p></li>
	 *	</ul>
	 *@param pName The name to validate.
	 * @return ok
	 */
	public static boolean validateName(String pName) {
		int length = pName.length();
		boolean ok = length>=5 && length<=50;
		if (!ok) {
			System.err.println("Avatar.validateArtist("+pName+") ->INVALIDE");
		}
		return ok;
	}


	/**
	 *  Get the avatar (image in Base64 )
	 * @return avatar a description for the avatar.
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 *  Set the avatar (image in Base64 )
	 * @param pAvatar The avatar to be set
	 */
	public void setAvatar(String pAvatar) {
		this.avatar = pAvatar;
	}
	

	@Override
	public String toString() {
		return "Avatar named ':"+this.name+"' ";
	}

}
