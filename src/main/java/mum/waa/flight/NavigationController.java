package mum.waa.flight;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class NavigationController implements Serializable {
	   @ManagedProperty(value = "#{param.pageId}")
	   private String pageId;

	 
	   
	   public String showPage() {
	      if(pageId == null) {
	         return "index";
	      }
	      
	      if(pageId.equals("back")) {
	         return "index";
	      }else if(pageId.equals("airline")) {
	         return "airline";
	      }else {
	         return "index";
	      }
	   }
}
