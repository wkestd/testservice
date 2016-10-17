package multigold.testservice;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.eig.basic.pojo.Page;
import com.gome.multigold.GoldCardReqAndResEntity;
import com.gome.multigold.cms.service.BannerService;
import com.gome.multigold.domain.StoreDayEndBatch;
import com.gome.multigold.domain.TPoApplyloans;
import com.gome.multigold.quoteprice.pojo.RateLog;
import com.gome.multigold.quoteprice.service.GoldRateService;
import com.gome.multigold.quoteprice.service.QuotePriceService;
import com.gome.multigold.quoteprice.service.RateConfigService;
import com.gome.multigold.quoteprice.service.RateService;
import com.gome.multigold.service.ClUnionCardService;
import com.gome.multigold.service.IBusApplyLoansService;
import com.gome.multigold.service.ICaculateService;
import com.gome.multigold.service.IEcPayOrderDetail;
import com.gome.multigold.service.IGoldCardService;
import com.gome.multigold.service.IOrderFeeService;
import com.gome.multigold.service.IOrderService;
import com.gome.multigold.service.SendTaurusSmsService;
import com.gome.multigold.service.StoreDayEndBatchService;
import com.gome.multigold.service.TPoApprovalRechargeService;
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
			String date = "2016-10-10 10:10:10";
			System.out.println(imService.selectRateListByDate("2016-10-10 01:01:01"));	
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
