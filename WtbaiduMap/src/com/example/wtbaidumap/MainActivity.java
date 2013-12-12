package com.example.wtbaidumap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
/**
 * 
 * @author wt
 * 图层事件这里没有列出,参考百度API
 *
 */
public class MainActivity extends Activity{
	private Toast mToast;
//	private Button button;
	private MKSearch mMKSearch = null;// 地图搜索类
	private BMapManager bMapManager;// 地图管理类
	private MapView mMapView = null;// 地图主控件
	private MapController MapController = null;// 地图控制对象
	private MKMapViewListener mMapListener = null;// 用于处理地图事件回调

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bMapManager = new BMapManager(this);// 初始化地图管理类，需在setContentView之前
		bMapManager.init("wRn9vejTnZIQrhIFzDzbwtKc", new MKGeneralListener() {
			// 初始化地图
			@Override
			public void onGetNetworkState(int arg0) {// 一些网络状态的错误处理回调函数
				// TODO Auto-generated method stub
				if (MKEvent.ERROR_NETWORK_CONNECT == arg0) {
					showToast("网络出错啦！");
				}
			}

			@Override
			public void onGetPermissionState(int arg0) {// 授权错误的时候调用的回调函数
				// TODO Auto-generated method stub
				showToast("API KEY错误, 请检查！");
			}

		});
		// bMapManager.start();
		// LocationManager
		// locationManager=(LocationManager)getSystemService(this.LOCATION_SERVICE);
		// if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
		// showToast("请开启GPS导航...");
		// }
		// Location location
		// =locationManager.getLastKnownLocation(locationManager.getBestProvider(getCriteria(),
		// true));
		setContentView(R.layout.activity_main);
//		mMKSearch = new MKSearch();
//		mMKSearch.init(bMapManager, new MySearchListener());
//		button = (Button) findViewById(R.id.button);
//		button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mMKSearch = new MKSearch();
//				mMKSearch.init(bMapManager, new MySearchListener());
//				// 1:POI搜索
//				// mMKSearch.poiSearchInbounds("kfc", arg1, arg2);//在一个矩形范围内搜索
////				   mMKSearch.poiSearchInCity("成都", "KFC");//城市检索
//				// mMKSearch.poiSearchNearBy("kfc", arg1, "5000");//以某一点为坐标5000M范围内搜索KFC
//				// 2.地址信息查询
////				mMKSearch.reverseGeocode(new GeoPoint(40057031, 116307852));//逆地址解析
////				mMKSearch.geocode("成都", "环球中心");//地址解析  
//				//3：在线建议查询
////				mMKSearch.suggestionSearch("key", "city");
//				//4:短串分享
////				mMKSearch.poiDetailShareURLSearch(new MKPoiInfo().uid);//POI点分享
////				mMKSearch.poiRGCShareURLSearch(new GeoPoint((int)(39.945*1E6),(int)(116.404*1E6)), "天安门", "西城区景山前街4号");//反Geo点分享
//		       //5:驾车线路搜索
////				MKPlanNode start=new MKPlanNode();
////				start.pt=new GeoPoint((int) (39.915 * 1E6), (int) (116.404 * 1E6));
////				MKPlanNode end=new MKPlanNode();
////				end.pt=new GeoPoint(40057031, 116307852);
//////				mMKSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);// 设置驾车路线搜索策略，时间优先、费用最少或距离最短 
//////				mMKSearch.drivingSearch(null, start, null, end);//aro0为起始信息，arg2终点信息
////				//6.步行路线搜索,与驾车线路搜索类似
////				mMKSearch.walkingSearch(null, start, null, end);
//				//7.公交线路搜索
////				mMKSearch.transitSearch("成都", start, end);
//				//8.根据公交id进行搜索
////				mMKSearch.busLineSearch("成都", "221");
//				//导航功能，调用百度地图
////				double mLat1 = 39.915;   
////				double mLon1 = 116.404;   
////				double mLat2 = 32.032;  
////				double mLon2 = 118.799; 
////				int lat = (int) (mLat1 *1E6);  
////				int lon = (int) (mLon1 *1E6);   
////				GeoPoint pt1=new GeoPoint(lat, lon);
////				lat = (int) (mLat2 *1E6);  
////				lon = (int) (mLon2 *1E6);
////				GeoPoint pt2=new GeoPoint(lat, lon);
////				NaviPara para=	new NaviPara();
////				para.startPoint=pt1;
////				para.startName="天府广场";
////				para.endPoint=pt2;
////				para.endName="世纪城";
////				try{
////					BaiduMapNavigation.openBaiduMapNavi(para, MainActivity.this);
////				}catch(BaiduMapAppNotSupportNaviException  e){
////					 e.printStackTrace();  
////				}
//             //自定义添加控件在地图上,此功能在地图上比较有用
////				 Button button2 = new Button(MainActivity.this);  
////				 button.setText("Hello World"); 
////				 GeoPoint pt =new GeoPoint((int) (22.547923 * 1E6),
////							(int) (114.067368 * 1E6)); 
//				 mMapView.addView(button,  new MapView.LayoutParams( MapView.LayoutParams.WRAP_CONTENT,  MapView.LayoutParams.WRAP_CONTENT, pt,  MapView.LayoutParams.BOTTOM));
//			}
//		         new MapView.LayoutParams(width, height, X, y, alignment);
//		});
		mMapView = (MapView) this.findViewById(R.id.bmapView);
		mMapView.setTraffic(true);// 实时交通信息图
		mMapView.setBuiltInZoomControls(true);//地图缩小放大功能
		// mMapView.setSatellite(true);//卫星地图
		MapController = mMapView.getController();// 获取地图控制器
		MapController.enableClick(true);// 可响应点击事件
		MapController.setZoom(12);// 设置缩放级别
		MapController.setZoomGesturesEnabled(true);
		GeoPoint g = new GeoPoint((int) (22.547923 * 1E6),
				(int) (114.067368 * 1E6));// 保存精度和纬度的类
		MapController.setCenter(g);// 设置p地方为中心点
		mMapView.regMapViewListener(bMapManager, new MKMapViewListener() {

			@Override
			public void onClickMapPoi(MapPoi arg0) {// 点击地图上被标记的点回调此方法
				// TODO Auto-generated method stub
				if (arg0 != null) {
					showToast(arg0.strText);
				}
			}

			@Override
			public void onGetCurrentMap(Bitmap arg0) {// 当调用过
														// mMapView.getCurrentMap()后，此回调会被触发
				// * 可在此保存截图至存储设备
				// TODO Auto-generated method stub

			}

			@Override
			public void onMapAnimationFinish() { // 地图完成带动画的操作（如:
													// animationTo()）后，此回调被触发
				// TODO Auto-generated method stub

			}

			@Override
			public void onMapLoadFinish() {// 地图加载完毕回调此接口方法
				// TODO Auto-generated method stub
				showToast("地图载入完毕！");
			}

			@Override
			public void onMapMoveFinish() {// 地图移动完成时会回调此接口 方法
				// TODO Auto-generated method stub
				showToast("地图移动完毕！");
			}
		});
	}

	protected void onResume() {
		// MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		mMapView.onResume();
		if (bMapManager != null) {
			bMapManager.start();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		// MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		mMapView.onPause();
		if (bMapManager != null) {
			bMapManager.stop();
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
		mMapView.destroy();

		// 退出应用调用BMapManager的destroy()方法
		if (bMapManager != null) {
			bMapManager.destroy();
			bMapManager = null;
		}

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showToast(String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	/**
	 * 
	 * @author wt 百度地图搜索服务需要实现MKSearchListener
	 */
	public class MySearchListener implements MKSearchListener {

		@Override
		public void onGetAddrResult(MKAddrInfo res, int error) {
			// TODO Auto-generated method stub
			// 返回地址信息搜索结果------2
		  if(error!=0){
			  showToast(String.format("错误号：%d", error));
			  return;
		  }	
		  mMapView.getController().animateTo(res.geoPt);  //地图移动到该点
		  if(res.type==MKAddrInfo.MK_GEOCODE){ //地理编码：通过地址检索坐标点  
			  showToast(String.format("纬度：%f 经度：%f", res.geoPt.getLatitudeE6()/1e6, res.geoPt.getLongitudeE6()/1e6));
		  }
		  if(res.type==MKAddrInfo.MK_REVERSEGEOCODE){////反地理编码：通过坐标点检索详细地址及周边poi  
			    showToast(res.strAddr);  
		  }
		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult res, int error) {
			// TODO Auto-generated method stub
			// 返回公交车详情信息搜索结果----------8
			   if (error != 0 || res == null) {  
				   showToast("抱歉，未找到结果");
	               return;  
	       }  
			   RouteOverlay routeOverlay = new RouteOverlay(MainActivity.this, mMapView);    // 此处仅展示一个方案作为示例  
		       routeOverlay.setData(res.getBusRoute());  
		       mMapView.getOverlays().clear();  
		       mMapView.getOverlays().add(routeOverlay);  
		       mMapView.refresh();  
		       mMapView.getController().animateTo(res.getBusRoute().getStart());  
		       showToast("公交线路详情");
		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult res, int error) {
			// TODO Auto-generated method stub
			// 返回驾乘路线搜索结果-----5
              if(res==null){
            	  return;
              }
              RouteOverlay routeOverlay=  new RouteOverlay(MainActivity.this, mMapView);
              routeOverlay.setData(res.getPlan(0).getRoute(0));//此处选择第一个方案第一条路线做为演示
              mMapView.getOverlays().clear();
              mMapView.getOverlays().add(routeOverlay);
              mMapView.refresh();
//              mMapView.getController().animateTo(res.);
              showToast(res.getPlan(0).getRoute(0).toString());
			  mMapView.getController().animateTo(res.getStart().pt);// 将给定的位置点以动画形式移动至地图中心
																// 对以给定的点GeoPoint，开始动画显示地图。
		}

		@Override
		public void onGetPoiDetailSearchResult(int arg0, int arg1) {
			// TODO Auto-generated method stub
			// 返回详细poi搜索结果
		}

		@Override
		public void onGetPoiResult(MKPoiResult res, int type, int error) {
			// TODO Auto-generated method stub
			// 返回poi搜索结果-----------1
			if (error == MKEvent.ERROR_RESULT_NOT_FOUND) {
				showToast("抱歉，未找到结果");
				return;
			} else if (error != 0 || res == null) {
				showToast("搜索出错啦..");
				return;
			}
			// 将poi结果显示到地图上
			PoiOverlay poiOverlay = new PoiOverlay(MainActivity.this, mMapView);// 得到poi返回对象
			poiOverlay.setData(res.getAllPoi());
			mMapView.getOverlays().clear();
			mMapView.getOverlays().add(poiOverlay);
			mMapView.refresh();
			// 当ePoiType为2（公交线路）或4（地铁线路）时， poi坐标为空
			for (MKPoiInfo info : res.getAllPoi()) {
				if (info.pt != null) {
					mMapView.getController().animateTo(info.pt);// 将给定的位置点以动画形式移动至地图中心
																// 对以给定的点GeoPoint，开始动画显示地图。
					break;
				}
			}
			// mMKSearch.destory();
		}

		@Override
		public void onGetShareUrlResult(MKShareUrlResult res, int type,
				int error) {
			// TODO Auto-generated method stub---------------4
			// 在此处理短串请求返回结果.分为两种分享。（1）POI分享 （2）反GEO分享
			if(error!=0||res==null){
				showToast("搜索出错啦！");
			return;
			}
			if(type==MKEvent.MKEVENT_POIDETAILSHAREURL){
				 //返回poi详情短串  
				showToast(res.url);
			}
			if(type==MKEvent.MKEVENT_POIRGCSHAREURL){
				  //返回地址信息短串  
				showToast(res.url);
			}
			   mMKSearch.destory();
			   
		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult res, int error) {
			// TODO Auto-generated method stub
			// 返回联想词信息搜索结果----------3
			if (error!= 0 || res == null) {  
	            showToast("抱歉，未找到结果");
	            return;  
	        }  
				int sug=res.getSuggestionNum();//获取建议条数
				String[] suggestion=new String[sug];
				for(int i=0;i<sug;i++){
				String city=res.getSuggestion(i).city;
				String key=res.getSuggestion(i).key;
				suggestion[i]=city+key;
				}
				ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, R.layout.activity_main, suggestion);
		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult res, int error) {
			// TODO Auto-generated method stub
			// 返回公交搜索结果-----------7
			if(res==null){
				return;
			}
			  RouteOverlay routeOverlay=  new RouteOverlay(MainActivity.this, mMapView);
              routeOverlay.setData(res.getPlan(0).getRoute(0));//此处选择第一个方案第一条路线做为演示
              mMapView.getOverlays().clear();
              mMapView.getOverlays().add(routeOverlay);
              mMapView.refresh();
              showToast(res.getPlan(0).getRoute(0).toString());
			  mMapView.getController().animateTo(res.getStart().pt);// 将给定的位置点以动画形式移动至地图中心
																// 对以给定的点GeoPoint，开始动画显示地图。
		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult res, int error) {
			// TODO Auto-generated method stub
			// 返回步行路线搜索结果--------6
			if(res==null){
				return;
			}
			  RouteOverlay routeOverlay=  new RouteOverlay(MainActivity.this, mMapView);
              routeOverlay.setData(res.getPlan(0).getRoute(0));//此处选择第一个方案第一条路线做为演示
              mMapView.getOverlays().clear();
              mMapView.getOverlays().add(routeOverlay);
              mMapView.refresh();
              showToast(res.getPlan(0).getRoute(0).toString());
			  mMapView.getController().animateTo(res.getStart().pt);// 将给定的位置点以动画形式移动至地图中心
																// 对以给定的点GeoPoint，开始动画显示地图。
		}

	}
}
