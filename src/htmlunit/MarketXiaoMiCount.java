package htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;

/**
 * 小米
http://dev.xiaomi.com/
 */
public class MarketXiaoMiCount {

	public void login(String userName,String pwd){
		WebClient webClient = null;
		
		try{
			webClient = new WebClient(BrowserVersion.FIREFOX_24);
			
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
			 
			
			HtmlPage page1 = webClient.getPage("https://account.xiaomi.com/pass/serviceLogin?callback=http%3A%2F%2Fdev.xiaomi.com%2Fsts%3Ffollowup%3Dhttp%253A%252F%252Fdev.xiaomi.com%252Fmyitems%252F0%26sign%3DBqiHh0MQAzGtKng5cKmj6WkOzv8%253D&sid=developer");
			
			HtmlTextInput userNameText = null;
			HtmlPasswordInput passwordText = null;
			HtmlPage page = null;
			List<FrameWindow> window = page1.getFrames();
			
			if(window != null && window.size() > 0){
				for(FrameWindow fw : window){
					
					page1 = (HtmlPage) fw.getEnclosedPage();
					
					if( (userNameText = (HtmlTextInput) page1.getElementById("miniLogin_username")) != null && (passwordText = (HtmlPasswordInput) page1.getElementById("miniLogin_pwd")) != null){
						
						userNameText.setValueAttribute(userName);
						passwordText.setValueAttribute(pwd);
						
						HtmlSubmitInput submit = (HtmlSubmitInput) page1.getElementById("message_LOGIN_IMMEDIATELY");
						page = submit.click();
						System.out.println("page.getUrl()=" + page.getUrl());
						page = (HtmlPage) webClient.openWindow(page.getUrl(),"signon").getEnclosedPage();
						
						break;
					}
				}
			}
			
			if(userNameText == null || passwordText == null){
				System.out.println("无法输入用户名/密码");
				System.exit(0);
			}
			
			
			
			//print(page);
			
			HtmlAnchor a = page.getAnchorByHref("/datacenter/overview?pageType=5");
			page = a.click();
			
			
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
			
	/*		while(page.asText().indexOf("数据载入中") > 0){
				Thread.sleep(10000);
			}*/
			
			//print( page ,null);
			
			DomNodeList<DomElement> tables = page.getElementsByTagName("table");
			
			if(tables != null && tables.size() > 0){
				HtmlTable table = null;
				String tableText = null;
				for(DomElement t : tables){
					if(t != null){
						table = (HtmlTable) t;
						tableText = table.asText();
						if(tableText.indexOf("应用名称") > 0 || tableText.indexOf("累计下载量") > 0){
							
							for(int i = 0 ; i < table.getRowCount() ; i++){
								HtmlTableCell cell = table.getCellAt(i, 3);
								System.out.println(cell.asText());
								System.out.println("\n");
							}
							
							break;
						}
						
					}
				}
				
				
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
		MarketXiaoMiCount l = new MarketXiaoMiCount();
		l.login(args[0],args[1]);
	}
}
