package br.com.jackson.pizza.conveter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.util.NumberUtils;

//import org.apache.commons.lang3.math.NumberUtils;


@FacesConverter("bigDecimalConverter")
public class BigDecimalConverter implements Converter {

    private static final BigDecimal UPPER_LIMIT = new BigDecimal(9999);
    private static final BigDecimal LOWER_LIMIT = new BigDecimal(-9999);
    
    private static final String BIG_DECIMAL_FORMAT = "0.000";
    private static final DecimalFormat format = new DecimalFormat(BIG_DECIMAL_FORMAT);
    private static int RE_SCALE = 3;
    private BigDecimal bigDecimal;
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
    	
    	BigDecimal convertedValue = NumberUtils.parseNumber(value, BigDecimal.class);
    	
    	return convertedValue;
    	
    	/*{
            throw new ConverterException(new FacesMessage("not a number"));
        }*/
    	
    	/*try {
     
            final int i = convertedValue.indexOf(".");
            if (i != -1) {
					throw new ParseException(value, i);
            }
            
            bigDecimal = (BigDecimal) format.parse(value);
            if (bigDecimal.scale() > RE_SCALE)
				throw new ParseException(value, i);
	    	} catch (ParseException e) {
				e.printStackTrace();
			}
    	
            return bigDecimal.setScale(RE_SCALE, RoundingMode.DOWN);*/
        }
        
    	
        /*if (value.contains(".")) {
            String decimalPlace = value.substring(value.indexOf("."));
        if (decimalPlace.length() > 3) { // 3 as decimal point is included in the String
                throw new ConverterException(new FacesMessage(
                        "too many numbers after decimal point"));
            }
        }
        */
        //NumberFormat nf = NumberFormat.getInstance(new Locale("pt","BR"));
        //((DecimalFormat) nf).setDecimalSeparatorAlwaysShown(true);
        //DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(new Locale("pt","BR"));
        //DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols(); //new DecimalFormatSymbols();
        //symbols.setGroupingSeparator(' ');
        //formatter.applyPattern("###,###.##", symbols);
        //symbols.setDecimalSeparator(',');
        //String pattern = "#.##0,0#";
        /*DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);*/
        
        /*DecimalFormat df = new DecimalFormat("#,##0.00");
        df.setParseBigDecimal(true);
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("pt","BR")));
        System.out.println(df.format(new BigDecimal(value))); //will output 123.456,78
*/        
        //try {
        	//convertedValue = new BigDecimal(nf.parse(value, ).toString());
        	//convertedValue = new BigDecimal(df.format(new BigDecimal(value)));
			
		/*} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}*/
       
        	
        //BigDecimal convertedValue = new BigDecimal(value).setScale(2,RoundingMode.HALF_UP);
        /*if (convertedValue.compareTo(UPPER_LIMIT) > 0) {
            throw new ConverterException(new FacesMessage(
                    "value may not be greater than " + UPPER_LIMIT));
        }
        if (convertedValue.compareTo(LOWER_LIMIT) < 0) {
            throw new ConverterException(new FacesMessage(
                    "value may not be less than " + LOWER_LIMIT));
        }
        return convertedValue;*/
    //}

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return ((BigDecimal) value).toString();
    }
}