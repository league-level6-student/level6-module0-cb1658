package _02_cat_facts_API;

import java.util.HashSet;

import _02_cat_facts_API.data_transfer_objects.CatWrapper;

public class CatFactsRunner {

    public static void main(String[] args) {
        CatFactsApi catFactsApi = new CatFactsApi();
        catFactsApi.testRequest();
        CatWrapper catFact = catFactsApi.getCatFact();

        System.out.println(catFactsApi.findCatFact());
        
        HashSet<String> hs = new HashSet<String>();
        
        while(hs.size() < 91) {  // Most likely 91 fat cat facts in the whole entire universe.

        	hs.add(catFactsApi.findCatFact());
        	
        	//System.out.println(hs.size());
        	
        	System.out.println(String.format("%.02f", hs.size()/91.0*100) + "%");

        }
        
        System.out.println("\n\n -------------------------------------------------------------------------\n\n");
        
        for(String s : hs) {
        	System.out.println(s);
        }
    }

}
