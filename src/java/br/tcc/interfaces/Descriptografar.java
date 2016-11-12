
package br.tcc.interfaces;

/**
 *
 * @author joãomarcos
 */
public class Descriptografar implements InterfaceCrip{

    
    public String TipoCrip(String txt) {
       
        String inf = txt.substring(0,2);
        String descrip = "";
        if(inf.equals("99")){
            
            descrip = Descrip1(txt);
            
        }else{
            descrip = Descrip2(txt);
        }
        
        return descrip;
    }

    public String Descrip1(String txt){
        String txtCript = "";
        String pCript = txt.substring(2);
        
       
        
        for (int i = 0; i < pCript.length(); i++)
            {
          
                String letra = String.valueOf(pCript.charAt(i));
          if (letra.equals("*"))
          {
             
              txtCript += letra.replace("*", "a");

          }
            
         else if (letra.equals("#"))
          {
              txtCript += letra.replace("#", "b");
          }
          else if (letra.equals("ê"))
          {

              txtCript += letra.replace("ê", "c");

          }
          else if (letra.equals("ô"))
          {

              txtCript += letra.replace("ô", "d");
          }
          else if (letra.equals("$"))
          {

              txtCript += letra.replace("$", "e"); ;
          }
          else if (letra.equals("!"))
          {

              txtCript += letra.replace("!", "f"); ;
          }
          else if (letra.equals("?"))
          {


              txtCript += letra.replace("?", "g");
          }
          else if (letra.equals("~"))
          {


              txtCript += letra.replace("~", "h"); ;
          }
          else if (letra.equals("@"))
          {


              txtCript += letra.replace("@", "i"); ;
          }
          else if (letra.equals("-"))
          {


              txtCript += letra.replace("-", "j"); ;
          }
          else if (letra.equals("&"))
          {


              txtCript += letra.replace("&", "k"); ;
          }
          else if (letra.equals("+"))
          {


              txtCript += letra.replace("+", "l"); ;
          }
          else if (letra.equals("â"))
          {


              txtCript += letra.replace("â", "m"); ;
          }
          else if (letra.equals("%"))
          {


              txtCript += letra.replace("%", "n"); ;
          }
          else if (letra.equals("ú"))
          {


              txtCript += letra.replace("ú", "o"); ;
          }
          else if (letra.equals("á"))
          {


              txtCript += letra.replace("á", "p"); ;
          }
          else if (letra.equals("í"))
          {


              txtCript += letra.replace("í", "r"); ;
          }
          else if (letra.equals("¬"))
          {


              txtCript += letra.replace("¬", "q"); ;
          }
          
          else if (letra.equals("é"))
          {


              txtCript += letra.replace("é", "s"); ;
          }
          else if (letra.equals("ã"))
          {


              txtCript += letra.replace("ã", "t");
          }
          else if (letra.equals("õ"))
          {


              txtCript += letra.replace("õ", "u"); 
          }
          else if (letra.equals("'"))
          {


              txtCript += letra.replace("'", "v"); 
          }
          else if (letra.equals("("))
          {


              txtCript += letra.replace("(", "w"); 
          }
          else if (letra.equals("{"))
          {


              txtCript += letra.replace("{", "x"); ;
          }
          else if (letra.equals("_"))
          {


              txtCript += letra.replace("_", "y"); ;
          }
          else if (letra.equals("^"))
          {


              txtCript += letra.replace("^", "z"); ;
          }
          else if (letra.equals("8"))
          {


              txtCript += letra.replace("8", "1"); ;
          }
           else if (letra.equals("a"))
          {


              txtCript += letra.replace("a", "2"); ;
          }
           else if (letra.equals("ñ"))
          {


              txtCript += letra.replace("ñ", "3") ;
          }
           else if (letra.equals("º"))
          {


              txtCript += letra.replace("º", "4"); ;
          }
           else if (letra.equals("|"))
          {


              txtCript += letra.replace("|", "5"); ;
          }
           else if (letra.equals(":"))
          {


              txtCript += letra.replace(":", "6"); ;
          }
           else if (letra.equals("ª"))
          {


              txtCript += letra.replace("ª", "7"); ;
          }
           else if (letra.equals(","))
          {


              txtCript += letra.replace(",", "8"); ;
          }
           else if (letra.equals("§"))
          {


              txtCript += letra.replace("§", "9"); ;
          }
           else if (letra.equals("c"))
          {


              txtCript += letra.replace("c", "0"); ;
          }

          
           else if (letra.equals("f"))
          {


              txtCript += letra.replace("f", "@"); ;
          }
           else if (letra.equals("`"))
          {


              txtCript += letra.replace("`", "."); ;
          }
           else if (letra.equals("4"))
          {


              txtCript += letra.replace("4", "_"); ;
          }
           else if (letra.equals("6"))
          {


              txtCript += letra.replace("6", "-"); ;
          }
           else if (letra.equals("è"))
          {


              txtCript += letra.replace("è", " "); ;
          }
                
          }
         
          return txtCript;
    }
    
    public String Descrip2(String txt){
        String txtCript = "";
        String pCript = txt.substring(2);
        
        for (int i = 0; i < pCript.length(); i++)
            {
         String letra = String.valueOf(pCript.charAt(i));
                if (letra.equals("´"))
          {
             
              txtCript += letra.replace("´", "a");

          }
         else if (letra.equals(">"))
          {
              txtCript += letra.replace(">", "b");
          }
          else if (letra.equals("m"))
          {

              txtCript += letra.replace("m", "c");

          }
          else if (letra.equals("í"))
          {

              txtCript += letra.replace("í", "d");
          }
          else if (letra.equals("0"))
          {

              txtCript += letra.replace("0", "e"); ;
          }
          else if (letra.equals("/"))
          {

              txtCript += letra.replace("/", "f"); ;
          }
          else if (letra.equals("+"))
          {


              txtCript += letra.replace("+", "g");
          }
          else if (letra.equals("_"))
          {


              txtCript += letra.replace("_", "h"); ;
          }
          else if (letra.equals("9"))
          {


              txtCript += letra.replace("9", "i"); ;
          }
          else if (letra.equals("ç"))
          {


              txtCript += letra.replace("ç", "j"); ;
          }
          else if (letra.equals("?"))
          {


              txtCript += letra.replace("?", "k"); ;
          }
          else if (letra.equals("="))
          {


              txtCript += letra.replace("=", "l"); ;
          }
          else if (letra.equals("&"))
          {


              txtCript += letra.replace("&", "m"); ;
          }
          else if (letra.equals("~"))
          {


              txtCript += letra.replace("~", "n") ;
          }
          else if (letra.equals(";"))
          {


              txtCript += letra.replace(";", "o"); ;
          }
          else if (letra.equals("-"))
          {


              txtCript += letra.replace("-", "p"); ;
          }
          else if (letra.equals(":"))
          {


              txtCript += letra.replace(":", "q"); ;
          }
          else if (letra.equals("|"))
          {


              txtCript += letra.replace("|", "r"); ;
          }
          else if (letra.equals("á"))
          {


              txtCript += letra.replace("á", "s"); ;
          }
          else if (letra.equals("â"))
          {


              txtCript += letra.replace("â", "t");
          }
          else if (letra.equals("*"))
          {


              txtCript += letra.replace("*", "u"); 
          }
          else if (letra.equals("l"))
          {


              txtCript += letra.replace("l", "v"); 
          }
          else if (letra.equals("["))
          {


              txtCript += letra.replace("[", "w"); 
          }
          else if (letra.equals("#"))
          {


              txtCript += letra.replace("#", "x"); ;
          }
          else if (letra.equals("ú"))
          {


              txtCript += letra.replace("ú", "y"); ;
          }
          else if (letra.equals("º"))
          {


              txtCript += letra.replace("º", "z"); ;
          }
          else if (letra.equals("c"))
          {


              txtCript += letra.replace("c", "1"); ;
          }
           else if (letra.equals("è"))
          {


              txtCript += letra.replace("è", "2"); ;
          }
           else if (letra.equals("7"))
          {


              txtCript += letra.replace("7", "3") ;
          }
           else if (letra.equals("ª"))
          {


              txtCript += letra.replace("ª", "4"); ;
          }
           else if (letra.equals("$"))
          {


              txtCript += letra.replace("$", "5"); ;
          }
           else if (letra.equals(","))
          {


              txtCript += letra.replace(",", "6"); ;
          }
           else if (letra.equals("`"))
          {


              txtCript += letra.replace("`", "7"); ;
          }
           else if (letra.equals("4"))
          {


              txtCript += letra.replace("4", "8"); ;
          }
           else if (letra.equals("¬"))
          {


              txtCript += letra.replace("¬", "9"); ;
          }
           else if (letra.equals("1"))
          {


              txtCript += letra.replace("1", "0"); ;
          }
           else if (letra.equals("<"))
          {


              txtCript += letra.replace("<", "@"); ;
          }
           else if (letra.equals("p"))
          {


              txtCript += letra.replace("p", "."); ;
          }
           else if (letra.equals("b"))
          {


              txtCript += letra.replace("b", "_"); ;
          }
           else if (letra.equals("5"))
          {


              txtCript += letra.replace("5", "-"); ;
          }
           else if (letra.equals("ì"))
          {


              txtCript += letra.replace("ì", " "); ;
          }

          }
         
          return txtCript;
        
    }
    
}
