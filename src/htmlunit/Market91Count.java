package htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;

/**
 * 91助手
http://market.sj.91.com/Default.aspx 
 */
public class Market91Count {

	public void login(String userName,String pwd){
		WebClient webClient = null;
		
		try{
			webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
			
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setTimeout(20000);
			
			webClient.waitForBackgroundJavaScriptStartingBefore(10000);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getCookieManager().setCookiesEnabled(true);
			
			//webClient.getOptions().setUseInsecureSSL(false);
			
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setCssEnabled(false);
			 
			
			HtmlPage page1 = webClient.getPage("http://market.sj.91.com/Users/Login.aspx");
			
			HtmlTextInput userNameText = (HtmlTextInput) page1.getElementById("UserName");
			userNameText.setValueAttribute(userName);
			
			HtmlPasswordInput passwordText = (HtmlPasswordInput) page1.getElementById("Password");
			passwordText.setValueAttribute(pwd);
			
			HtmlSubmitInput submit = (HtmlSubmitInput) page1.getElementById("Submit");
			
			
			HtmlPage page = submit.click();
			System.out.println("page.getUrl()=" + page.getUrl());
			page = (HtmlPage) webClient.openWindow(page.getUrl(),"signon").getEnclosedPage();
			
			//print(page);
			
			HtmlAnchor a = page.getAnchorByHref("Service/RedirectToRes.ashx?url=http://resmgt.sj.91.com/content/software/TopCarriage.aspx");
			page = a.click();
			
			
			JavaScriptJobManager manager = page.getEnclosingWindow().getJobManager();
			while (manager.getJobCount() > 0) {
			    Thread.sleep(1000);
			}
			
	/*		while(page.asText().indexOf("数据载入中") > 0){
				Thread.sleep(10000);
			}*/
			
			//print( page ,null);
			
			HtmlTable dataTable = (HtmlTable) page.getElementById("dataTable");
			
			//System.out.println(dataTable.asXml() + "\n");
			
			
			for(int i = 0 ; i < dataTable.getRowCount() ; i++){
				HtmlTableCell cell = dataTable.getCellAt(i, 9);
				System.out.println(cell.asText());
				System.out.println("\n");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(webClient != null){
				webClient.closeAllWindows();
			}
		}
	}
	
	private void print(HtmlPage page,String beforePrintStr){
		print(page,beforePrintStr);
	}
	
	private void print(Page page,String beforePrintStr){
		System.out.println(beforePrintStr);
		System.out.println("page.getUrl().toString()=" + page.getUrl().toString());
		System.out.println("page.getWebResponse().getContentAsString()=" + page.getWebResponse().getContentAsString());
	}
	
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Market91Count l = new Market91Count();
		l.login(args[0],args[1]);
	}
}
