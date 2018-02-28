package sample;

public class ConverterToUnicode {
    public ConverterToUnicode(){}

    String WIN1251_T0_UNIC0DE=
                    "\u0410\u0411\u0412\u0413\u0414\u0415\u0416\u0417\u0418\u0419\u041a"+
                    "\u041b\u041c\u041d\u041e\u041f\u0420\u0421\u0422\u0423\u0424\u0425\u0426\u0427\u0428\u0429"+
                    "\u042a\u042b\u042c\u042d\u042e\u042f\u0430\u0431\u0432\u0433\u0434\u0435\u0436\u0437\u0438"+
                    "\u0439\u043a\u043b\u043c\u043d\u043e\u043f\u0440\u0441\u0442\u0443\u0444\u0445\u0446\u0447"+
                    "\u0448\u0449\u044a\u044b\u044c\u044d\u044e\u044f";

    public String convert(String str){
        String s_out="";
        char[] bAr=new char[str.length()];
        for (int i=0; i<str.length(); i++){
            bAr[i]=convert(str.charAt(i));
        }
        s_out=String.valueOf(bAr);
        return s_out;
    }

    public char convert (char ch) {
        try
        {
            if (ch < 128){
                return ch;
            }else{
                int i = ch;//'A'=1040
                return WIN1251_T0_UNIC0DE.charAt(ch-1040);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        ConverterToUnicode converterToUnicode = new ConverterToUnicode();
        String str = "\u041d\u0430\u0442\u0430\u043b\u0438\u044f \u0415\u0440\u0435\u043c\u0435\u043d\u043a\u043e";
        System.out.println(converterToUnicode.convert(str));
    }
}
