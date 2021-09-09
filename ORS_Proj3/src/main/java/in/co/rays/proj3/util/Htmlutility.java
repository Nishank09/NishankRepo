package in.co.rays.proj3.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.proj3.dto.DropDownList;


/**
 * The Class HTMLUtility.
 * @author Iterator
 * @version 1.0
 */
public class Htmlutility {
 
	
	/**
	 * Create HTML SELECT list from MAP paramters values.
	 *
	 * @param name the name
	 * @param selectedVal the selected val
	 * @param map the map
	 * @return the list
	 */

	public static String getlist(String name, String selectedvalue, HashMap<String , String> map) {
		StringBuffer sb = new StringBuffer("<select style='height:"+32+"px' style='width:"+173+"px' class='form-control' name='" + name + "'>");
		
		Set<String> keys= map.keySet();
		String val=null;
		sb.append("<option selected value=''>--------Select-----------</option>");
		for(String key:keys){
			val= map.get(key);
			if(key.trim().equals(selectedvalue)){
				sb.append("<option selected value='"+key+"'>"+val+"</option>");
			}
			else{
				sb.append("<option value='"+key+"'>"+val+"</option>");
			}
			
		}
		sb.append("<select>");
		return sb.toString();
	}
	
	/**
 	 * Create HTML SELECT List from List parameter.
 	 *
 	 * @param name the name
 	 * @param selectedVal the selected val
 	 * @param list the list
 	 * @return the list
 	 */
	public static String getlist(String name, String selectedvalue, List list) {
		
		Collections.sort(list);
		List <DropDownList> dd = (List<DropDownList>) list;

		StringBuffer sb = new StringBuffer("<select style='height:"+32+"px' style='width:"+173+"px' class='form-control' name='"+name+"'>");
		sb.append("<option selected value=''>----select----</option>");
		String key= null;
		String value= null;
		for(DropDownList obj : dd){
			key= obj.getKey();
			value=obj.getValue();
			if(key.trim().equals(selectedvalue)){
				sb.append("<option selected value='"+key.trim()+"'>"+ value+"</option>");
			}else{
				sb.append("<option value='"+key.trim()+"'>" + value + "</option>");
			}
		}
        sb.append("<select>");
		return sb.toString();
	}	
	
}
