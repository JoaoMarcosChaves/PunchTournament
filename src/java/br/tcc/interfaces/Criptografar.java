
package br.tcc.interfaces;


public class Criptografar implements InterfaceCrip{
//Conteudo conte = new Conteudo();
    @Override
    
    public String TipoCrip(String txt){
    String crip = "";
        int i = (int) (Math.random() * 2);
        if(i == 0){
            crip = CriptoInf1(txt);
        }else{
            crip = CriptoInf2(txt);
        }
        
        return crip;
    }
    
    public String CriptoInf1(String txt) {

        String txtCript = "";
        String pCript = txt.toLowerCase();
        for (int i = 0; i < pCript.length(); i++)
            {
          
                String letra = String.valueOf(pCript.charAt(i));
                if (letra.equals("a"))
          {
             
              txtCript += letra.replace("a", "*");

          }
         else if (letra.equals("b"))
          {
              txtCript += letra.replace("b", "#");
          }
          else if (letra.equals("c"))
          {

              txtCript += letra.replace("c", "ê");

          }
          else if (letra.equals("d"))
          {

              txtCript += letra.replace("d", "ô");
          }
          else if (letra.equals("e"))
          {

              txtCript += letra.replace("e", "$"); ;
          }
          else if (letra.equals("f"))
          {

              txtCript += letra.replace("f", "!"); ;
          }
          else if (letra.equals("g"))
          {


              txtCript += letra.replace("g", "?");
          }
          else if (letra.equals("h"))
          {


              txtCript += letra.replace("h", "~"); ;
          }
          else if (letra.equals("i"))
          {


              txtCript += letra.replace("i", "@"); ;
          }
          else if (letra.equals("j"))
          {


              txtCript += letra.replace("j", "-"); ;
          }
          else if (letra.equals("k"))
          {


              txtCript += letra.replace("k", "&"); ;
          }
          else if (letra.equals("l"))
          {


              txtCript += letra.replace("l", "+"); ;
          }
          else if (letra.equals("m"))
          {


              txtCript += letra.replace("m", "â"); ;
          }
          else if (letra.equals("n"))
          {


              txtCript += letra.replace("n", "%"); ;
          }
          else if (letra.equals("o"))
          {


              txtCript += letra.replace("o", "ú"); ;
          }
          else if (letra.equals("p"))
          {


              txtCript += letra.replace("p", "á"); ;
          }
          else if (letra.equals("q"))
          {


              txtCript += letra.replace("q", "¬"); ;
          }
          
          else if (letra.equals("r"))
          {


              txtCript += letra.replace("r", "í"); ;
          }
          else if (letra.equals("s"))
          {


              txtCript += letra.replace("s", "é"); ;
          }
          else if (letra.equals("t"))
          {


              txtCript += letra.replace("t", "ã");
          }
          else if (letra.equals("u"))
          {


              txtCript += letra.replace("u", "õ"); 
          }
          else if (letra.equals("v"))
          {


              txtCript += letra.replace("v", "'"); 
          }
          else if (letra.equals("w"))
          {


              txtCript += letra.replace("w", "("); 
          }
          else if (letra.equals("x"))
          {


              txtCript += letra.replace("x", "{"); ;
          }
          else if (letra.equals("y"))
          {


              txtCript += letra.replace("y", "_"); ;
          }
          else if (letra.equals("z"))
          {


              txtCript += letra.replace("z", "^"); ;
          }
           else if (letra.equals("1"))
          {


              txtCript += letra.replace("1", "8"); ;
          }
           else if (letra.equals("2"))
          {


              txtCript += letra.replace("2", "a"); ;
          }
           else if (letra.equals("3"))
          {


              txtCript += letra.replace("3", "ñ") ;
          }
           else if (letra.equals("4"))
          {


              txtCript += letra.replace("4", "º"); ;
          }
           else if (letra.equals("5"))
          {


              txtCript += letra.replace("5", "|"); ;
          }
           else if (letra.equals("6"))
          {


              txtCript += letra.replace("6", ":"); ;
          }
           else if (letra.equals("7"))
          {


              txtCript += letra.replace("7", "ª"); ;
          }
           else if (letra.equals("8"))
          {


              txtCript += letra.replace("8", ","); ;
          }
           else if (letra.equals("9"))
          {


              txtCript += letra.replace("9", "§"); ;
          }
           else if (letra.equals("0"))
          {


              txtCript += letra.replace("0", "c"); ;
          }
           else if (letra.equals("@"))
          {


              txtCript += letra.replace("@", "f"); ;
          }
           else if (letra.equals("."))
          {


              txtCript += letra.replace(".", "`"); ;
          }
           else if (letra.equals("_"))
          {


              txtCript += letra.replace("_", "4"); ;
          }
           else if (letra.equals("-"))
          {


              txtCript += letra.replace("-", "6"); ;
          }
           else if (letra.equals(" "))
          {


              txtCript += letra.replace(" ", "è"); ;
          }
                
          }
         
          return "99"+txtCript;
    }
    
     public String CriptoInf2(String txt) {
         String txtCript = "";
        String pCript = txt.toLowerCase();
        for (int i = 0; i < pCript.length(); i++)
            {
         String letra = String.valueOf(pCript.charAt(i));
                if (letra.equals("a"))
          {
             
              txtCript += letra.replace("a", "´");

          }
         else if (letra.equals("b"))
          {
              txtCript += letra.replace("b", ">");
          }
          else if (letra.equals("c"))
          {

              txtCript += letra.replace("c", "m");

          }
          else if (letra.equals("d"))
          {

              txtCript += letra.replace("d", "í");
          }
          else if (letra.equals("e"))
          {

              txtCript += letra.replace("e", "0"); ;
          }
          else if (letra.equals("f"))
          {

              txtCript += letra.replace("f", "/"); ;
          }
          else if (letra.equals("g"))
          {


              txtCript += letra.replace("g", "+");
          }
          else if (letra.equals("h"))
          {


              txtCript += letra.replace("h", "_"); ;
          }
          else if (letra.equals("i"))
          {


              txtCript += letra.replace("i", "9"); ;
          }
          else if (letra.equals("j"))
          {


              txtCript += letra.replace("j", "ç"); ;
          }
          else if (letra.equals("k"))
          {


              txtCript += letra.replace("k", "?"); ;
          }
          else if (letra.equals("l"))
          {


              txtCript += letra.replace("l", "="); ;
          }
          else if (letra.equals("m"))
          {


              txtCript += letra.replace("m", "&"); ;
          }
          else if (letra.equals("n"))
          {


              txtCript += letra.replace("n", "~") ;
          }
          else if (letra.equals("o"))
          {


              txtCript += letra.replace("o", ";"); ;
          }
          else if (letra.equals("p"))
          {


              txtCript += letra.replace("p", "-"); ;
          }
          else if (letra.equals("q"))
          {


              txtCript += letra.replace("q", ":"); ;
          }
          else if (letra.equals("r"))
          {


              txtCript += letra.replace("r", "|"); ;
          }
          else if (letra.equals("s"))
          {


              txtCript += letra.replace("s", "á"); ;
          }
          else if (letra.equals("t"))
          {


              txtCript += letra.replace("t", "â");
          }
          else if (letra.equals("u"))
          {


              txtCript += letra.replace("u", "*"); 
          }
          else if (letra.equals("v"))
          {


              txtCript += letra.replace("v", "l"); 
          }
          else if (letra.equals("w"))
          {


              txtCript += letra.replace("w", "["); 
          }
          else if (letra.equals("x"))
          {


              txtCript += letra.replace("x", "#"); ;
          }
          else if (letra.equals("y"))
          {


              txtCript += letra.replace("y", "ú"); ;
          }
          else if (letra.equals("z"))
          {


              txtCript += letra.replace("z", "º"); ;
          }
          else if (letra.equals("1"))
          {


              txtCript += letra.replace("1", "c"); ;
          }
           else if (letra.equals("2"))
          {


              txtCript += letra.replace("2", "è"); ;
          }
           else if (letra.equals("3"))
          {


              txtCript += letra.replace("3", "7") ;
          }
           else if (letra.equals("4"))
          {


              txtCript += letra.replace("4", "ª"); ;
          }
           else if (letra.equals("5"))
          {


              txtCript += letra.replace("5", "$"); ;
          }
           else if (letra.equals("6"))
          {


              txtCript += letra.replace("6", ","); ;
          }
           else if (letra.equals("7"))
          {


              txtCript += letra.replace("7", "`"); ;
          }
           else if (letra.equals("8"))
          {


              txtCript += letra.replace("8", "4"); ;
          }
           else if (letra.equals("9"))
          {


              txtCript += letra.replace("9", "¬"); ;
          }
           else if (letra.equals("0"))
          {


              txtCript += letra.replace("0", "1"); ;
          }
           else if (letra.equals("@"))
          {


              txtCript += letra.replace("@", "<"); ;
          }
           else if (letra.equals("."))
          {


              txtCript += letra.replace(".", "p"); ;
          }
           else if (letra.equals("_"))
          {


              txtCript += letra.replace("_", "b"); ;
          }
           else if (letra.equals("-"))
          {


              txtCript += letra.replace("-", "5"); ;
          }
           else if (letra.equals(" "))
          {


              txtCript += letra.replace(" ", "ì"); ;
          }

          }
         
          return "23"+txtCript;
         
     }
}
