package multigold.testservice;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.eig.basic.pojo.ListPageResult;
import com.eig.basic.pojo.Page;
import com.eig.basic.pojo.PageData;
import com.gome.multigold.account.pojo.TActBaseAccount;
import com.gome.multigold.account.pojo.TActCreditaccountDetail;
import com.gome.multigold.account.pojo.TActLoanaccount;
import com.gome.multigold.account.service.ISelectAccountService;
import com.gome.multigold.account.service.ITransPageService;
import com.gome.multigold.base.pojo.Clientinfo;
import com.gome.multigold.base.pojo.TPromotionClientDetail;
import com.gome.multigold.base.service.ClientinfoService;
import com.gome.multigold.base.service.IActconfigService;
import com.gome.multigold.cms.service.BannerService;
import com.gome.multigold.core.Const.ConstClass;
import com.gome.multigold.core.util.DateUtil;
import com.gome.multigold.goldmanage.service.GoldService;
import com.gome.multigold.quoteprice.pojo.Rate;
import com.gome.multigold.quoteprice.pojo.RateLog;
import com.gome.multigold.quoteprice.service.GoldRateService;
/**
 * dubbo本地测试服务1
 * @author anliguo
 *
 */
public class TestService {
	public static Map<String,String> urlMap = new HashMap<String,String>();
	static{
		urlMap.put("dev", "127.0.0.1:2181");
		urlMap.put("sit", "10.145.200.25:2181");
		urlMap.put("uat", "10.145.200.58:2181,10.145.200.59:2181,10.145.200.60:2181");
		urlMap.put("prd", "10.145.6.16:2181,10.145.6.17:2181,10.145.6.18:2181");
	}
	public static void main(String[] args) {
		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
		// 引用远程服务
		ReferenceConfig<GoldRateService> reference = new ReferenceConfig<GoldRateService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference.setApplication(application);
		reference.setRegistries(getRegistryConfigList("dev"));
		//reference.setInterface(GoldService.class);
		reference.setInterface(GoldRateService.class);
		// 和本地bean一样使用xxxService
		GoldRateService imService = reference.get();
		//GoldService imService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
		try {
			String date = "2015-10-10 10:10:10";
			System.out.println("当前时间：：：：："+date);
			List<RateLog> rates = imService.selectRateListByDate(date);
			System.out.println("rates的数量：：："+rates.size());
			
			System.out.println("现在牌价:::"+imService.getGoldPrice());
			
			//TActBaseAccount acc = imService.selBaseAccInfo("10003146");
		//	System.out.println(acc.getStatus());
			
//			Page page = new Page();
//			page.setPage(0);
//			page.setRows(1);
//			PageData pd = new PageData();
//			pd.put("bizCode", "");
//			pd.put("accountCode", "1000002792");
//			pd.put("userCode", pd.get("accountCode"));
//			page.setPd(pd);
//			//CX0041000002756
//			System.out.println(imService.getableApply().get(0).getGoldName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<RegistryConfig> getRegistryConfigList(String config){
		List<RegistryConfig> registrys = new ArrayList<RegistryConfig>();
		String[] urlArray = urlMap.get(config).split(",");
		if(urlArray!=null && urlArray.length>0){
			for(String u:urlArray){
				RegistryConfig registry = new RegistryConfig();
				//zookper注册中心ip
				registry.setAddress(u);
				registry.setUsername("");
				registry.setPassword("");
				//dubbo协议
				registry.setProtocol("zookeeper");
				registrys.add(registry);
			}
		}
		return registrys;
	}
}
