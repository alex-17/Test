package htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;
import com.gargoylesoftware.htmlunit.util.UrlUtils;

/**
 * 360
http://i.360.cn/login/?src=pcw_open_app&destUrl=http%3A%2F%2Fdev.app.360.cn%2Fdev%2Findex
 */
public class Market360Count {
	
	public static int nextPage = 1;

	public void login(String userName,String pwd){
		WebClient webClient = null;
		
		try{
			webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
			
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setTimeout(20000);
			
			webClient.waitForBackgroundJavaScriptStartingBefore(10000);
			webClient.waitForBackgroundJavaScript(10000);

			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getCookieManager().setCookiesEnabled(true);
			
			webClient.getOptions().setUseInsecureSSL(true);
			
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setCssEnabled(false);
			 
			HtmlPage page1 = webClient.getPage("http://i.360.cn/login?destUrl=http%3A%2F%2Fdev.360.cn%2F");
			
			HtmlTextInput userNameText = (HtmlTextInput) page1.getElementById("loginAccount");
			userNameText.setValueAttribute(userName);
			
			HtmlPasswordInput passwordText = (HtmlPasswordInput) page1.getElementById("lpassword");
			passwordText.setValueAttribute(pwd);
			
			HtmlSubmitInput submit = (HtmlSubmitInput) page1.getElementById("loginSubmit");
			
			
			HtmlPage page = submit.click();
			
			
			System.out.println("page.getUrl()=" + page.getUrl());
			
			page = (HtmlPage) webClient.openWindow(UrlUtils.toUrlSafe("http://dev.360.cn/mobile/list"),"signon" + String.valueOf(Math.random()).replaceAll(".", "")).getEnclosedPage();

			do {
				JavaScriptJobManager manager = page.getEnclosingWindow().getJobManager();
				long sleepTime = 0;
				long step = 1000;
				long maxSleep = 20000;
				while (manager.getJobCount() > 0) {
				    Thread.sleep(step);
				    sleepTime += step;
				    if(sleepTime > maxSleep){
				    	System.out.println("JobManager sleep timeout!");
				    	break;
				    }
				}
				
				DomNodeList<DomElement> tables = page.getElementsByTagName("table");
				
				if(tables != null && tables.size() > 0){
					HtmlTable table = null;
					String tableText = null;
					for(DomElement t : tables){
						if(t != null){
							table = (HtmlTable) t;
							tableText = table.asText();
							if(tableText.indexOf("昨日下载") > 0 || tableText.indexOf("应用信息") > 0){
								
								for(int i = 0 ; i < table.getRowCount() ; i++){
									HtmlTableCell cell = table.getCellAt(i, 0);
									cell = table.getCellAt(i, 2);
									System.out.println(cell.asText());
									System.out.println("\n");
								}
								
								break;
							}
							
						}
					}
				}
				
			}while( (page = nextPage(page)) != null );
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(webClient != null){
				webClient.closeAllWindows();
			}
		}
	}
	
	
	private HtmlPage nextPage(HtmlPage page) throws IOException{
		HtmlAnchor a = page.getAnchorByHref("http://dev.360.cn:80/mobile/list?&pageno=" + (++nextPage));
		if(a != null){
			page = a.click();
		} else {
			return null;
		}
		return page;
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
		Market360Count l = new Market360Count();
		l.login(args[0],args[1]);
	}
}
