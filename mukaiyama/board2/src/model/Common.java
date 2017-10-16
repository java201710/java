package model;

public class Common {
	public String Sanitize( String str ) {
        if(str==null) {
            return str;
        }
        str = str.replaceAll("&" , "&amp;" );
        str = str.replaceAll("<" , "&lt;"  );
        str = str.replaceAll(">" , "&gt;"  );
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("'" , "&#39;" );
        return str;
     }

}
