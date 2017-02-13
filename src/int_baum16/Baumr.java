package int_baum16;

public class Baumr {
	static Baumrtest T;
	
   public Knoten wurzel=null;    // Baum ist leer
   private int tiefe;
   int k,m,j=-1,jj=-1,p=0;
   
   
	//Baumrtest b  = new Baumrtest();

// Einfuegen eines Knotens
   /**
 * @param x : is what will be added to the Tree
 */
public void einfuegen( String x ) {
       if (wurzel== null)
           wurzel =new  Knoten(x,0,0);
       else  {
    	   k=300;
    	   m=100;
    	   j=-1;
    	   jj=-1;
    	   einrek (wurzel,x);
       }
   }

   /**
 * @param p is the start node that we will use to loop the tree
 * @param x is the value to be added 
 * 
 * while i'm looping the tree i calculate the right position of the x- and y-achse to be represented on the screen 
 */
private void einrek (Knoten p, String x){
	   
       if (x.compareTo(p.daten) < 0)  {
           if (p.links==null){
        	   k-=50;
        	   m+=50;
        	   if(k*160 == 48000)
        		   k+=30;
        	   
               p.links =new  Knoten(x,k,m);
           }
           else{ 
        	   k-=50;
        	   m+=50;
        	   einrek(p.links, x);
        	   }
       }
       else  if (p.rechts==null){
    	   k+=50;
    	   m+=50;
    	   if(k*160 == 48000)
    		   k-=30;
    	 
                 p.rechts =new  Knoten(x,k,m);
       } else  {
    	   k+=50;
    	   m+=50;
    	   einrek (p.rechts,x);
             }
      
   }
   
   
   
   
   
   
  
  /**
 * @param i is the string to be found and deleted
 * 
 * this method covers the three possible situations for a node to be deleted
 * 1. Node to be removed has no children.
 * 2. Node to be removed has one child.
 * 3. Node to be removed has two children.
 * 
 */
public void del(String i){
	  Knoten k = wurzel;
	  boolean b = false;
	  
	  while(b == false){
		
		  
		  if(k.rechts == null && k.links == null){

			b = true;
		  }
		  
		  //1 Node to be removed has no children.
		  if(k.links != null){
		  while(k.links.links != null){
			  k = k.links;
		  }
		  if(k.links != null){
		  if( k.links.daten.compareTo(i) == 0){
			  k.links = null;
			  b=true;
		  }}
		  if(k.rechts != null){
		  if(k.rechts.daten == i){
			  k.rechts = null;
			  b=true;
		  }}
		  }
		  
		  //2 Node to be removed has no children.
		  k = wurzel;
		 if(k.rechts != null){
		  while(k.rechts.rechts != null){
			  k = k.rechts;
		  }
		  if(k.rechts != null){
		  if( k.rechts.daten.compareTo(i) == 0){
			  k.rechts = null;
			  b=true;
		  }}
		  
		  if(k.links != null){
			  if( k.links.daten.compareTo(i) == 0){
				  k.links = null;
				  b=true;
			  }}
		  
		  
		 }
		 //2 situation : Node to be removed has one child
		 
		 k = wurzel;
		 while(k.rechts != null && b==false){
			 
			 if(k.rechts.daten.compareTo(i) == 0  && k.rechts.links == null){
				 k.rechts = k.rechts.rechts; 
				 b= true;
			 }
			 k = k.rechts;
		 }
		 k=wurzel;
		 while(k.links != null && b==false){
			 
			 if(k.links.daten.compareTo(i) == 0 && k.links.rechts == null){
				 k.links = k.links.links; 
				 b= true;
			 }
			 k = k.links;
		 }
		 
		 //3.situation : Node to be removed has two children
		 boolean f=false;
		 k = wurzel;
		 if(k.rechts != null){
			  while(k.rechts.rechts != null && f==false){
				  k = k.rechts;
				  if(k.daten.compareTo(i) == 0)
					  f=true;
			  }
			 
			  
		 if(k.rechts.links != null && k.rechts.rechts != null){
			 if( k.rechts.links.daten.compareTo(k.rechts.rechts.daten)<0 ){
				 k.daten = k.rechts.links.daten;
				 k.rechts.links = null;
				 b=true;
			 }else{
				 k.daten = k.rechts.rechts.daten;
				k.rechts.rechts = null;
				 b=true;
			 }
		 }
		 b=true;
		 
		 }
		 
		
		 
		 
		  
	  }
		  
	
		 
	  }
	  
			  
  
 
   
   
   
   
  
  
   
   /**
 * print the tree on the console
 */
public void ausgabe() {
		System.out.println("\n --- Baum - Uebersicht --- \n");
		tiefe = 0;
		struktur(wurzel);
	}


	/**
	 * @param k is the start node
	 * this function will loop through the tree and calculate the deep of the tree and then print the informations
	 */
	private void struktur(Knoten k) {
		if (k != null) {
			tiefe++;
			struktur(k.rechts);
			
			tiefe--;
			for (int i = 0; i < tiefe; i++) {
				System.out.print("    ");
			}
			System.out.println(" <- " + k.daten);
			
			
			tiefe++;
			struktur(k.links);
			tiefe--;// rechter Unterbaum
			
		}
	}

	
	
	
	
 }
